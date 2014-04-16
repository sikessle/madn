package de.htwg.madn.controller;

import java.awt.Color;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

import com.google.inject.Inject;

import de.htwg.madn.model.GameId;
import de.htwg.madn.model.IBoard;
import de.htwg.madn.model.IGameSettings;
import de.htwg.madn.model.IModelDao;
import de.htwg.madn.model.Player;
import de.htwg.madn.util.observer.Observable;

public final class BoardController extends Observable implements
		IBoardControllerPort {

	private MovementController movementController;
	private IBoard model;
	private final IModelDao modelDao;

	@Inject
	public BoardController(IModelDao modelDao) {
		this.modelDao = modelDao;
		model = modelDao.createModel();
		init();
	}

	private void init() {
		movementController = new MovementController(model);
		movementController.addObserver(this);
		movementController.setStatus(model.getStatus());
	}

	@Override
	public void update() {
		model.setStatus(movementController.getStatusString());
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see de.htwg.madn.controller.IBoardController#getBoard()
	 */
	@Override
	public IBoard getModel() {
		return model;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see de.htwg.madn.controller.IBoardController#getSettings()
	 */
	@Override
	public IGameSettings getSettings() {
		return model.getSettings();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see de.htwg.madn.controller.IBoardController#addPlayer(java.lang.String,
	 * java.awt.Color, boolean)
	 */
	@Override
	public void addPlayer(final String name, final Color col, boolean isHuman) {
		Player newPlayer = model.addPlayer(col, name, isHuman);

		if (newPlayer == null) {
			model.setStatus("Maximum amount of players reached: "
					+ model.getSettings().getMaxPlayers());
		} else {
			model.setStatus("Player " + newPlayer.getId() + " \""
					+ newPlayer.getName() + "\" added.");
		}

		notifyObservers();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see de.htwg.madn.controller.IBoardController#throwDice()
	 */
	@Override
	public void rollDice() {
		boolean setNextPlayer = false;

		if (!model.isGameRunning()) {
			model.setStatus("Please start the game first");
		} else {
			setNextPlayer = movementController
					.rollDice(model.getActivePlayer());
		}

		if (setNextPlayer) {
			setNextActivePlayer();
		}
		notifyObservers();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see de.htwg.madn.controller.IBoardController#reset()
	 */
	@Override
	public void reset() {
		model = modelDao.createModel();
		init();
		model.setStatus("Game State reset.");
		notifyObservers();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see de.htwg.madn.controller.IBoardController#moveFigure(char)
	 */
	@Override
	public void moveFigure(char figureLetter) {
		boolean setNext = movementController.moveFigure(
				model.getActivePlayer(), figureLetter);

		if (setNext) {
			setNextActivePlayer();
		}
		notifyObservers();
	}

	private void handleFinishedPlayer(Player player) {
		if (player == null) {
			return;
		}
		if (!movementController.hasActiveFigures(player)) {
			model.getFinishedPlayersQueue().add(player);
			model.getActivePlayersQueue().remove(player);
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see de.htwg.madn.controller.IBoardController#startGame()
	 */
	@Override
	public void startGame() {
		if (model.isGameRunning()) {
			model.setStatus("Spiel laeuft schon!");
		} else if (model.getActivePlayersQueue().size() < model.getSettings()
				.getMinPlayers()) {
			model.setStatus("Too few players. At least "
					+ model.getSettings().getMinPlayers()
					+ " players required.");
		} else {
			setNextActivePlayer();
			model.setStatus("Spiel beginnt.");
			model.setGameRunning(true);
		}
		notifyObservers();
	}

	private void setNextActivePlayer() {
		// reset dice
		model.getDice().resetThrowsCount();
		// check and maybe remove a finished player
		handleFinishedPlayer(model.getActivePlayer());
		// get from head and remove
		Player activePlayer = model.getActivePlayersQueue().poll();
		// no more Players!
		if (activePlayer == null) {
			return;
		}
		model.setActivePlayer(activePlayer);
		// push to tail of queue
		model.getActivePlayersQueue().add(activePlayer);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see de.htwg.madn.controller.IBoardController#getFinishedPlayersQueue()
	 */
	@Override
	public Queue<Player> getFinishedPlayersQueue() {
		return model.getFinishedPlayersQueue();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see de.htwg.madn.controller.IBoardController#getPlayers()
	 */
	@Override
	public List<Player> getPlayers() {
		return model.getPlayers();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see de.htwg.madn.controller.IBoardController#getStatusString()
	 */
	@Override
	public String getStatusString() {
		return model.getStatus();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see de.htwg.madn.controller.IBoardController#getActivePlayer()
	 */
	@Override
	public Player getActivePlayer() {
		return model.getActivePlayer();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see de.htwg.madn.controller.IBoardController#getActivePlayerString()
	 */
	@Override
	public String getActivePlayerString() {
		Player activePlayer = model.getActivePlayer();
		if (activePlayer != null) {
			return activePlayer.getName();
		}
		return null;
	}

	@Override
	public boolean gameIsFinished() {
		return model.getActivePlayersQueue().isEmpty() && model.isGameRunning();
	}

	@Override
	public boolean gameIsRunning() {
		return model.isGameRunning();
	}

	@Override
	public GameId saveGame(String comment) {
		GameId savedGameId = modelDao.storeModel(model, comment);
		model.setStatus("Game saved under id " + savedGameId);
		return savedGameId;
	}

	@Override
	public void loadGame(String gameIdString) {
		try {
			int id = Integer.parseInt(gameIdString);
			loadGameFromId(id);
		} catch (NumberFormatException e) {
		}

		notifyObservers();
	}

	private void loadGameFromId(int id) {
		GameId gameId = new GameId(id);
		IBoard loadedModel = modelDao.getModel(gameId);
		if (model == null) {
			reset();
		} else {
			model = loadedModel;
			model.setStatus("Game with id " + model.getGameId().getId()
					+ " loaded");
		}
	}

	@Override
	public List<GameId> getSavedGameIds() {
		List<IBoard> games = modelDao.getAllModels();
		List<GameId> savedGameIds = new LinkedList<GameId>();
		for (IBoard game : games) {
			savedGameIds.add(game.getGameId());
		}
		return savedGameIds;
	}
}
