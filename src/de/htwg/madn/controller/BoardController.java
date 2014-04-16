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

	private String status = "";
	private Player activePlayer;
	// without finished players
	private Queue<Player> activePlayersQueue;
	// finished players
	private Queue<Player> finishedPlayersQueue;
	private boolean gameIsRunning;
	private MovementController movementController;
	private IBoard model;
	private final IModelDao modelDao;

	@Inject
	public BoardController(IModelDao modelDao) {
		this.modelDao = modelDao;
		this.model = modelDao.createModel();
		init();
	}

	private void init() {
		this.activePlayersQueue = new LinkedList<Player>();
		this.finishedPlayersQueue = new LinkedList<Player>();
		this.activePlayer = null;
		this.status = "New Game created.";
		this.gameIsRunning = false;
		this.movementController = new MovementController(model);
		this.movementController.addObserver(this);
	}

	@Override
	public void update() {
		status = movementController.getStatusString();
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
			status = "Maximum amount of players reached: "
					+ model.getSettings().getMaxPlayers();
		} else {
			activePlayersQueue.add(newPlayer);
			status = "Player " + newPlayer.getId() + " \""
					+ newPlayer.getName() + "\" added.";
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

		if (!gameIsRunning) {
			status = "Please start the game first";
		} else {
			setNextPlayer = movementController.rollDice(activePlayer);
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
		status = "Game State reset.";
		notifyObservers();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see de.htwg.madn.controller.IBoardController#moveFigure(char)
	 */
	@Override
	public void moveFigure(char figureLetter) {
		boolean setNext = movementController.moveFigure(activePlayer,
				figureLetter);

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
			finishedPlayersQueue.add(player);
			activePlayersQueue.remove(player);
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see de.htwg.madn.controller.IBoardController#startGame()
	 */
	@Override
	public void startGame() {
		if (gameIsRunning) {
			status = "Spiel laeuft schon!";
		} else if (activePlayersQueue.size() < model.getSettings()
				.getMinPlayers()) {
			status = "Too few players. At least "
					+ model.getSettings().getMinPlayers()
					+ " players required.";
		} else {
			setNextActivePlayer();
			status = "Spiel beginnt.";
			gameIsRunning = true;
		}
		notifyObservers();
	}

	private void setNextActivePlayer() {
		// reset dice
		model.getDice().resetThrowsCount();
		// check and maybe remove a finished player
		handleFinishedPlayer(activePlayer);
		// get from head and remove
		activePlayer = activePlayersQueue.poll();
		// no more Players!
		if (activePlayer == null) {
			return;
		}
		// push to tail of queue
		activePlayersQueue.add(activePlayer);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see de.htwg.madn.controller.IBoardController#getFinishedPlayersQueue()
	 */
	@Override
	public Queue<Player> getFinishedPlayersQueue() {
		return finishedPlayersQueue;
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
		return status;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see de.htwg.madn.controller.IBoardController#getActivePlayer()
	 */
	@Override
	public Player getActivePlayer() {
		return activePlayer;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see de.htwg.madn.controller.IBoardController#getActivePlayerString()
	 */
	@Override
	public String getActivePlayerString() {
		if (activePlayer != null) {
			return activePlayer.getName();
		}
		return null;
	}

	@Override
	public boolean gameIsFinished() {
		return activePlayersQueue.isEmpty() && gameIsRunning;
	}

	@Override
	public boolean gameIsRunning() {
		return this.gameIsRunning;
	}

	@Override
	public GameId saveGame(String comment) {
		GameId savedGameId = modelDao.storeModel(model, comment);
		status = "Game saved under id " + savedGameId;
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
			status = "Game with id " + model.getGameId().getId() + " loaded";
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
