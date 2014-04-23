package de.htwg.madn.model.hibernate;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

import com.google.inject.Injector;

import de.htwg.madn.model.Dice;
import de.htwg.madn.model.Figure;
import de.htwg.madn.model.FinishField;
import de.htwg.madn.model.GameId;
import de.htwg.madn.model.HomeField;
import de.htwg.madn.model.IBoard;
import de.htwg.madn.model.IGameSettings;
import de.htwg.madn.model.Player;
import de.htwg.madn.model.PublicField;

public class PersistenceToDomainObjectTransformer {

	private final Injector injector;

	public PersistenceToDomainObjectTransformer(Injector injector) {
		this.injector = injector;
	}

	public IBoard transform(PersistenceBoard persistenceBoard) {
		IBoard result = injector.getInstance(IBoard.class);

		result.setActivePlayer(transform(persistenceBoard.getActivePlayer()));
		result.setActivePlayersQueue(transform(persistenceBoard
				.getActivePlayersQueue()));
		result.setDice(transform(persistenceBoard.getDice()));
		result.setDiceMax(persistenceBoard.getDiceMax());
		result.setDiceMin(persistenceBoard.getDiceMin());
		result.setFiguresPerPlayer(persistenceBoard.getFiguresPerPlayer());
		result.setFinishedPlayersQueue(transform(persistenceBoard
				.getFinishedPlayersQueue()));
		result.setFinishFields(transformFinishFields(persistenceBoard
				.getFinishFields()));
		result.setGameId(transform(persistenceBoard.getGameId()));
		result.setGameIsRunning(persistenceBoard.isGameRunning());
		result.setHomeFields(transformHomeFields(persistenceBoard
				.getHomeFields()));
		result.setMaxPlayers(persistenceBoard.getMaxPlayers());
		result.setPlayers(transform(persistenceBoard.getPlayers()));
		result.setPublicField(transform(persistenceBoard.getPublicField()));
		result.setPublicFieldsCount(persistenceBoard.getPublicFieldsCount());
		result.setSettings(transform(persistenceBoard.getSettings()));
		result.setStatus(persistenceBoard.getStatus());

		return result;
	}

	private IGameSettings transform(PersistenceGameSettings persistenceSettings) {
		IGameSettings result = injector.getInstance(IGameSettings.class);

		result.setDiceMax(persistenceSettings.getDiceMax());
		result.setDiceMin(persistenceSettings.getDiceMin());
		result.setFiguresPerPlayer(persistenceSettings.getFiguresPerPlayer());
		result.setMaxPlayers(persistenceSettings.getMaxPlayers());
		result.setMinNumberToExitHome(persistenceSettings
				.getMinNumberToExitHome());
		result.setMinPlayers(persistenceSettings.getMinPlayers());
		result.setPublicFieldsCount(persistenceSettings.getPublicFieldsCount());
		result.setThrowsAllowedInHome(persistenceSettings
				.getThrowsAllowedInHome());
		result.setThrowsAllowedInPublic(persistenceSettings
				.getThrowsAllowedInPublic());

		return result;
	}

	private PublicField transform(PersistencePublicField persistencePublicField) {
		PublicField result = new PublicField(
				persistencePublicField.getFields().length);
		result.setFields(transform(persistencePublicField.getFields()));

		return result;
	}

	private Figure[] transform(PersistenceFigure[] fields) {
		Figure[] result = new Figure[fields.length];
		Player owner = null;

		for (int i = 0; i < fields.length; i++) {
			if (fields[i] != null) {
				owner = transform(fields[i].getOwner());
				result[i] = transform(fields[i], owner);
			}
		}

		return result;
	}

	private List<Player> transform(List<PersistencePlayer> players) {
		List<Player> result = new LinkedList<Player>();

		for (PersistencePlayer p : players) {
			result.add(transform(p));
		}

		return result;
	}

	private List<HomeField> transformHomeFields(
			List<PersistenceHomeField> homeFields) {
		List<HomeField> result = new LinkedList<HomeField>();
		Player owner = null;

		for (PersistenceHomeField hf : homeFields) {
			owner = transform(hf.getOwner());
			result.add(transform(hf, owner));
		}

		return result;
	}

	private List<FinishField> transformFinishFields(
			List<PersistenceFinishField> finishFields) {
		List<FinishField> result = new LinkedList<FinishField>();
		Player owner = null;

		for (PersistenceFinishField ff : finishFields) {
			owner = transform(ff.getOwner());
			result.add(transform(ff, owner));
		}

		return result;
	}

	private Dice transform(PersistenceDice dice) {
		Dice result = new Dice(dice.getMin(), dice.getMax());
		if (dice.getThrowsCount() > 0) {
			result.setLastNumber(dice.getLastNumber());
		}
		result.setLastThrower(transform(dice.getLastThrower()));
		result.setMax(dice.getMax());
		result.setMin(dice.getMin());
		result.setThrowsCount(dice.getThrowsCount());

		return result;
	}

	private Queue<Player> transform(Queue<PersistencePlayer> playerQueue) {
		Queue<Player> result = new LinkedList<Player>();

		for (PersistencePlayer p : playerQueue) {
			result.add(transform(p));
		}

		return result;
	}

	private Player transform(PersistencePlayer player, HomeField homeField,
			FinishField finishField) {
		Player result = new Player(player.getId(), player.getColor(),
				player.getName(), homeField, finishField, player.getFigures()
						.size(), player.isHuman());
		result.setFigures(transform(player.getFigures(), result));

		return result;
	}

	private HomeField transform(PersistenceHomeField homeField, Player owner) {
		HomeField result = new HomeField(homeField.getExitIndex(),
				homeField.getFields().length);

		result.setExitIndex(homeField.getExitIndex());
		result.setFields(transform(homeField.getFields(), owner));
		result.setOwner(owner);

		return result;
	}

	private FinishField transform(PersistenceFinishField finishField,
			Player owner) {
		FinishField result = new FinishField(finishField.getEntryIndex(),
				finishField.getFields().length);

		result.setEntryIndex(finishField.getEntryIndex());
		result.setFields(transform(finishField.getFields(), owner));
		result.setOwner(owner);

		return result;
	}

	private Figure[] transform(PersistenceFigure[] fields, Player owner) {
		return (Figure[]) transform(Arrays.asList(fields), owner).toArray();
	}

	private List<Figure> transform(List<PersistenceFigure> figures, Player owner) {
		List<Figure> result = new LinkedList<Figure>();

		for (PersistenceFigure fig : figures) {
			result.add(transform(fig, owner));
		}

		return result;
	}

	private Figure transform(PersistenceFigure fig, Player owner) {
		Figure result = new Figure(fig.getLetter(), owner);

		result.setAtFinishArea(fig.isAtFinishArea());
		result.setAtHomeArea(fig.isAtHomeArea());
		result.setCurrentFieldIndex(fig.getCurrentFieldIndex());
		result.setFinished(fig.isFinished());

		return result;
	}

	public GameId transform(PersistenceGameId persistenceGameId) {
		GameId gameId = new GameId(persistenceGameId.getId());
		gameId.setComment(persistenceGameId.getComment());
		return gameId;
	}
}
