package de.htwg.madn.view.tui;

import java.awt.Color;
import java.util.List;

import org.apache.log4j.Logger;

import de.htwg.madn.controller.IBoardControllerPort;
import de.htwg.madn.model.GameId;
import de.htwg.madn.util.observer.IObserver;

public final class TUIView implements IObserver {

	private final IBoardControllerPort boardController;
	private final Logger log = Logger.getLogger(TUIView.class.getName());
	private final DataToStringConverter stringifyer;

	public TUIView(IBoardControllerPort boardController) {
		this.boardController = boardController;
		// watch the controller with this class
		this.boardController.addObserver(this);
		this.stringifyer = new DataToStringConverter(
				boardController.getSettings());
		draw();
	}

	public boolean handleInput(String line) {
		String[] args;
		String cmd, parm;
		boolean quit = false;

		// split input into a command and an argument part
		args = getCommandAndArgument(line);

		if (args != null) {
			cmd = args[0];
			parm = args[1];
			quit = interpretInput(cmd, parm);
		} else {
			// error empty command!
			log.info("Empty Input!");
			quit = false;
		}
		return quit;
	}

	private boolean interpretInput(String cmd, String parm) {
		if (cmd.equals("q")) {
			quitGame();
			return true;
		} else if (cmd.equals("s")) {
			boardController.startGame();
		} else if (cmd.equals("m") && parm != null) {
			boardController.moveFigure(parm.charAt(0));
			// maybe finished
			if (boardController.gameIsFinished()) {
				return true;
			}
		} else if (cmd.equals("d")) {
			boardController.rollDice();
		} else if (cmd.equals("add") && parm != null) {
			boardController.addPlayer(parm, Color.BLACK, true);
		} else if (cmd.equals("r")) {
			boardController.reset();
		} else if (cmd.equals("l") && parm != null) {
			boardController.loadGame(parm);
		} else if (cmd.equals("o")) {
			List<GameId> savedGames = boardController.getSavedGameIds();
			drawSavedGamesList(savedGames);
			log.info(getCommandsString());
		} else if (cmd.equals("p")) {
			GameId id = boardController.saveGame(parm);
			drawSaveSuccessful(id);
			log.info(getCommandsString());
		} else {
			// error unknown parameter
			log.info("Wrong Input!");
		}

		return false;
	}

	private void drawSavedGamesList(List<GameId> savedGames) {
		StringBuilder sb = new StringBuilder();
		sb.append("Available Games to load:\n");
		for (GameId id : savedGames) {
			sb.append(id);
			sb.append(", ");
		}
		int commaIndex = sb.lastIndexOf(",");
		if (commaIndex > -1) {
			sb.deleteCharAt(commaIndex);
		}
		sb.append('\n');
		log.info(sb);
	}

	private void drawSaveSuccessful(GameId id) {
		String message = "Game saved under id: " + id;
		log.info(message);
	}

	private void quitGame() {
		printWinners();
		log.info("GAME QUIT");
	}

	private void printWinners() {
		String win = stringifyer.getWinnersString(boardController
				.getFinishedPlayersQueue());

		log.info(win);
	}

	private String[] getCommandAndArgument(String line) {
		String[] words = null;
		String[] ret = new String[2];

		if (line == null || line.isEmpty()) {
			return null;
		}

		words = line.split(":");
		if (words.length == 0) {
			return null;
		}
		ret[0] = words[0].toLowerCase();
		// parameter like: add NAME
		if (words.length > 1) {
			ret[1] = words[1];
		}
		return ret;
	}

	@Override
	public void update() {
		draw();
		if (boardController.gameIsFinished()) {
			quitGame();
		}
	}

	private void draw() {
		log.info("\n" + getGameIdString() + "\n" + getPlayerSettingString()
				+ "\n" + getBoardString() + "\n" + "STATUS: "
				+ boardController.getStatusString() + "\n"
				+ getCommandsString());
	}

	private String getGameIdString() {
		GameId id = boardController.getModel().getGameId();
		if (id != null) {
			return "current game id: " + id.toString();
		} else {
			return "current game not yet saved";
		}
	}

	private String getPlayerSettingString() {
		StringBuilder sb = new StringBuilder();
		sb.append("Player-List:\n");
		sb.append(stringifyer.getPlayerSettingString(
				boardController.getPlayers(), boardController.getActivePlayer()));
		return sb.toString();
	}

	private String getCommandsString() {
		return "Commands: 'q' quit | 's' start game | "
				+ "'add:PlayerName' add player\n"
				+ "\t 'm:FigureLetter' move figure | 'd' roll dice | 'r' reset/new game |\n"
				+ "\t 'l:game-number' load game | 'o' show saved games\n"
				+ "\t 'p:comment' save game with comment\n";
	}

	public String getBoardString() {
		StringBuilder sb = new StringBuilder();

		sb.append(stringifyer.getBorderString());
		sb.append("\n");

		sb.append(stringifyer.getHomeFieldsString(boardController.getModel()
				.getHomeFields()));
		sb.append(stringifyer.getPublicFieldsString(boardController.getModel()
				.getPublicField(), boardController.getModel()));
		sb.append(stringifyer.getFinishFieldsString(boardController.getModel()
				.getFinishFields()));

		sb.append(stringifyer.getBorderString());

		return sb.toString();
	}
}
