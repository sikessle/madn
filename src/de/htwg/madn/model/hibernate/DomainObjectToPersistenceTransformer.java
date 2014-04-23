package de.htwg.madn.model.hibernate;

import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

import de.htwg.madn.model.Dice;
import de.htwg.madn.model.Figure;
import de.htwg.madn.model.FinishField;
import de.htwg.madn.model.GameId;
import de.htwg.madn.model.HomeField;
import de.htwg.madn.model.IBoard;
import de.htwg.madn.model.IGameSettings;
import de.htwg.madn.model.Player;
import de.htwg.madn.model.PublicField;

public class DomainObjectToPersistenceTransformer {

	public PersistenceBoard transform(IBoard board) {
		PersistenceBoard persistenceBoard = new PersistenceBoard();

		persistenceBoard.setActivePlayer(transform(board.getActivePlayer()));
		persistenceBoard.setActivePlayersQueue(transform(board
				.getActivePlayersQueue()));
		persistenceBoard.setDice(transform(board.getDice()));
		persistenceBoard.setDiceMax(board.getDiceMax());
		persistenceBoard.setDiceMin(board.getDiceMin());
		persistenceBoard.setFiguresPerPlayer(board.getFiguresPerPlayer());
		persistenceBoard.setFinishedPlayersQueue(transform(board
				.getFinishedPlayersQueue()));
		persistenceBoard.setFinishFields(transformFinishFields(board
				.getFinishFields()));
		persistenceBoard.setGameId(transform(board.getGameId()));
		persistenceBoard.setGameIsRunning(board.isGameRunning());
		persistenceBoard.setHomeFields(transformHomeFields(board
				.getHomeFields()));
		persistenceBoard.setMaxPlayers(board.getMaxPlayers());
		persistenceBoard.setPlayers(transform(board.getPlayers()));
		persistenceBoard.setPublicField(transform(board.getPublicField()));
		persistenceBoard.setPublicFieldsCount(board.getPublicFieldsCount());
		persistenceBoard.setSettings(transform(board.getSettings()));
		persistenceBoard.setStatus(board.getStatus());

		return persistenceBoard;
	}

	private PersistenceGameSettings transform(IGameSettings settings) {
		PersistenceGameSettings result = new PersistenceGameSettings();

		result.setDiceMax(settings.getDiceMax());
		result.setDiceMin(settings.getDiceMin());
		result.setFiguresPerPlayer(settings.getFiguresPerPlayer());
		result.setMaxPlayers(settings.getMaxPlayers());
		result.setMinNumberToExitHome(settings.getMinNumberToExitHome());
		result.setMinPlayers(settings.getMinPlayers());
		result.setPublicFieldsCount(settings.getPublicFieldsCount());
		result.setThrowsAllowedInHome(settings.getThrowsAllowedInHome());
		result.setThrowsAllowedInPublic(settings.getThrowsAllowedInPublic());

		return result;
	}

	private PersistencePublicField transform(PublicField publicField) {
		PersistencePublicField result = new PersistencePublicField();
		result.setFields(transform(publicField.getFields()));

		return result;
	}

	private PersistenceFigure[] transform(Figure[] fields) {
		PersistenceFigure[] result = new PersistenceFigure[fields.length];
		PersistencePlayer owner = null;

		for (int i = 0; i < fields.length; i++) {
			if (fields[i] != null) {
				owner = transform(fields[i].getOwner());
				result[i] = transform(fields[i], owner);
			}
		}

		return result;
	}

	private List<PersistencePlayer> transform(List<Player> players) {
		List<PersistencePlayer> result = new LinkedList<PersistencePlayer>();

		for (Player p : players) {
			result.add(transform(p));
		}

		return result;
	}

	private List<PersistenceHomeField> transformHomeFields(
			List<HomeField> homeFields) {
		List<PersistenceHomeField> result = new LinkedList<PersistenceHomeField>();
		PersistencePlayer owner = null;

		for (HomeField hf : homeFields) {
			owner = transform(hf.getOwner());
			result.add(transform(hf, owner));
		}

		return result;
	}

	private List<PersistenceFinishField> transformFinishFields(
			List<FinishField> finishFields) {
		List<PersistenceFinishField> result = new LinkedList<PersistenceFinishField>();
		PersistencePlayer owner = null;

		for (FinishField ff : finishFields) {
			owner = transform(ff.getOwner());
			result.add(transform(ff, owner));
		}

		return result;
	}

	private PersistenceDice transform(Dice dice) {
		PersistenceDice persistenceDice = new PersistenceDice();
		if (dice.getThrowsCount() > 0) {
			persistenceDice.setLastNumber(dice.getLastNumber());
		}
		persistenceDice.setLastThrower(transform(dice.getLastThrower()));
		persistenceDice.setMax(dice.getMax());
		persistenceDice.setMin(dice.getMin());
		persistenceDice.setThrowsCount(dice.getThrowsCount());

		return persistenceDice;
	}

	private Queue<PersistencePlayer> transform(Queue<Player> playerQueue) {
		Queue<PersistencePlayer> result = new LinkedList<PersistencePlayer>();

		for (Player p : playerQueue) {
			result.add(transform(p));
		}

		return result;
	}

	private PersistencePlayer transform(Player player) {
		if (player == null) {
			return null;
		}
		PersistencePlayer persistencePlayer = new PersistencePlayer();
		persistencePlayer.setColor(player.getColor());
		persistencePlayer.setFigures(transform(player.getFigures(),
				persistencePlayer));
		persistencePlayer.setFinishField(transform(player.getFinishField(),
				persistencePlayer));
		persistencePlayer.setHomeField(transform(player.getHomeField(),
				persistencePlayer));
		persistencePlayer.setHuman(player.isHuman());
		persistencePlayer.setId(player.getId());
		persistencePlayer.setName(player.getName());

		return persistencePlayer;
	}

	private PersistenceHomeField transform(HomeField homeField,
			PersistencePlayer owner) {
		PersistenceHomeField result = new PersistenceHomeField();

		result.setExitIndex(homeField.getExitIndex());
		result.setFields(transform(homeField.getFields(), owner));
		result.setOwner(owner);

		return result;
	}

	private PersistenceFinishField transform(FinishField finishField,
			PersistencePlayer owner) {
		PersistenceFinishField result = new PersistenceFinishField();

		result.setEntryIndex(finishField.getEntryIndex());
		result.setFields(transform(finishField.getFields(), owner));
		result.setOwner(owner);

		return result;
	}

	private PersistenceFigure[] transform(Figure[] fields,
			PersistencePlayer owner) {

		PersistenceFigure[] result = new PersistenceFigure[fields.length];

		for (int i = 0; i < fields.length; i++) {
			if (fields[i] != null) {
				result[i] = transform(fields[i], owner);
			}
		}
		return result;
	}

	private List<PersistenceFigure> transform(List<Figure> figures,
			PersistencePlayer owner) {
		List<PersistenceFigure> result = new LinkedList<PersistenceFigure>();

		for (Figure fig : figures) {
			result.add(transform(fig, owner));
		}

		return result;
	}

	private PersistenceFigure transform(Figure fig, PersistencePlayer owner) {
		PersistenceFigure persistenceFigure = new PersistenceFigure();

		if (fig == null) {
			return null;
		}
		persistenceFigure.setAtFinishArea(fig.isAtFinishArea());
		persistenceFigure.setAtHomeArea(fig.isAtHomeArea());
		persistenceFigure.setCurrentFieldIndex(fig.getCurrentFieldIndex());
		persistenceFigure.setFinished(fig.isFinished());
		persistenceFigure.setLetter(fig.getLetter());
		persistenceFigure.setOwner(owner);

		return persistenceFigure;
	}

	public PersistenceGameId transform(GameId gameId) {
		PersistenceGameId persistenceGameId = new PersistenceGameId();
		if (gameId != null) {
			persistenceGameId.setId(gameId.getId());
			persistenceGameId.setComment(gameId.getComment());
		}
		return persistenceGameId;
	}
}
