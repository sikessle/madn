package de.htwg.madn.model;

import java.awt.Color;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

import com.google.inject.Inject;

public final class Board implements IBoard {
	private List<HomeField> homeFields;
	private List<FinishField> finishFields;
	private List<Player> players;
	// without finished players
	private Queue<Player> activePlayersQueue;
	// finished players
	private Queue<Player> finishedPlayersQueue;
	private Player activePlayer;
	private boolean gameIsRunning;
	private String status = "";

	private PublicField publicField;
	private int maxPlayers;
	private int figuresPerPlayer;
	private int publicFieldsCount;
	private int diceMin;
	private int diceMax;
	private Dice dice;
	private IGameSettings settings;
	private GameId gameId;

	@Inject
	public Board(IGameSettings gameSettings) {

		this.maxPlayers = gameSettings.getMaxPlayers();
		this.figuresPerPlayer = gameSettings.getFiguresPerPlayer();
		this.publicFieldsCount = gameSettings.getPublicFieldsCount();
		this.diceMin = gameSettings.getDiceMin();
		this.diceMax = gameSettings.getDiceMax();
		settings = gameSettings;
		gameId = null;

		init();
	}

	private void init() {
		gameId = null;
		dice = new Dice(diceMin, diceMax);
		homeFields = new LinkedList<HomeField>();
		finishFields = new LinkedList<FinishField>();
		activePlayersQueue = new LinkedList<Player>();
		finishedPlayersQueue = new LinkedList<Player>();
		initHomeAndFinishFields();
		players = new LinkedList<Player>();
		publicField = new PublicField(publicFieldsCount);
		activePlayer = null;
		status = "New Game created.";
		gameIsRunning = false;
	}

	@Override
	public int getDiceMax() {
		return diceMax;
	}

	@Override
	public int getDiceMin() {
		return diceMin;
	}

	@Override
	public int getFiguresPerPlayer() {
		return figuresPerPlayer;
	}

	@Override
	public Player getActivePlayer() {
		return activePlayer;
	}

	@Override
	public void setActivePlayer(Player activePlayer) {
		this.activePlayer = activePlayer;
	}

	@Override
	public boolean isGameRunning() {
		return gameIsRunning;
	}

	@Override
	public int getMaxPlayers() {
		return maxPlayers;
	}

	@Override
	public int getPublicFieldsCount() {
		return publicFieldsCount;
	}

	@Override
	public void setGameRunning(boolean gameRunning) {
		this.gameIsRunning = gameRunning;
	}

	@Override
	public void setStatus(String status) {
		this.status = status;
	}

	@Override
	public String getStatus() {
		return status;
	}

	@Override
	public GameId getGameId() {
		return gameId;
	}

	@Override
	public void setGameId(GameId gameId) {
		this.gameId = gameId;
	}

	@Override
	public IGameSettings getSettings() {
		return settings;
	}

	private void initHomeAndFinishFields() {
		for (int i = 0; i < this.maxPlayers; i++) {
			homeFields.add(new HomeField(getExitIndexHome(i),
					this.figuresPerPlayer));
			finishFields.add(new FinishField(getEntryIndexFinish(i),
					this.figuresPerPlayer));
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see de.htwg.madn.model.IBoard#reset()
	 */
	@Override
	public void reset() {
		init();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see de.htwg.madn.model.IBoard#getExitIndexHome(int)
	 */
	@Override
	public int getExitIndexHome(final int playerNumber) {
		return playerNumber * publicFieldsCount / maxPlayers;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see de.htwg.madn.model.IBoard#getEntryIndexFinish(int)
	 */
	@Override
	public int getEntryIndexFinish(final int playerNumber) {
		return (getExitIndexHome(playerNumber) + publicFieldsCount - 1)
				% publicFieldsCount;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see de.htwg.madn.model.IBoard#addPlayer(java.awt.Color,
	 * java.lang.String, boolean)
	 */
	@Override
	public Player addPlayer(final Color color, final String name,
			boolean isHuman) {
		if (players.size() >= maxPlayers) {
			return null;
		}

		int playerId = players.size();

		Player newPlayer = new Player(playerId, color, name,
				homeFields.get(playerId), finishFields.get(playerId),
				figuresPerPlayer, isHuman);
		players.add(newPlayer);
		activePlayersQueue.add(newPlayer);
		return newPlayer;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see de.htwg.madn.model.IBoard#getHomeFields()
	 */
	@Override
	public List<HomeField> getHomeFields() {
		return homeFields;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see de.htwg.madn.model.IBoard#getFinishFields()
	 */
	@Override
	public List<FinishField> getFinishFields() {
		return finishFields;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see de.htwg.madn.model.IBoard#getPublicField()
	 */
	@Override
	public PublicField getPublicField() {
		return publicField;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see de.htwg.madn.model.IBoard#getPlayers()
	 */
	@Override
	public List<Player> getPlayers() {
		return players;
	}

	@Override
	public Queue<Player> getActivePlayersQueue() {
		return activePlayersQueue;
	}

	@Override
	public Queue<Player> getFinishedPlayersQueue() {
		return finishedPlayersQueue;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see de.htwg.madn.model.IBoard#getDice()
	 */
	@Override
	public Dice getDice() {
		return this.dice;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * de.htwg.madn.model.IBoard#getFigureForPlayerByLetter(de.htwg.madn.model
	 * .Player, char)
	 */
	@Override
	public Figure getFigureForPlayerByLetter(Player player, char figureLetter) {
		if (player == null || !Character.isLetter(figureLetter)) {
			throw new IllegalArgumentException("player or char is null");
		}
		for (Figure figure : player.getFigures()) {
			if (figure.getLetter() == figureLetter) {
				return figure;
			}
		}
		return null;
	}

	@Override
	public void setActivePlayersQueue(Queue<Player> transform) {
		// TODO Auto-generated method stub

	}

	@Override
	public void setDice(Dice dice) {
		this.dice = dice;
	}

	@Override
	public void setDiceMax(int diceMax) {
		this.diceMax = diceMax;
	}

	@Override
	public void setDiceMin(int diceMin) {
		this.diceMin = diceMin;
	}

	@Override
	public void setFiguresPerPlayer(int figuresPerPlayer) {
		this.figuresPerPlayer = figuresPerPlayer;
	}

	@Override
	public void setFinishedPlayersQueue(Queue<Player> finishedPlayersQueue) {
		this.finishedPlayersQueue = finishedPlayersQueue;
	}

	@Override
	public void setFinishFields(List<FinishField> finishFields) {
		this.finishFields = finishFields;
	}

	@Override
	public void setHomeFields(List<HomeField> homeFields) {
		this.homeFields = homeFields;
	}

	@Override
	public void setMaxPlayers(int maxPlayers) {
		this.maxPlayers = maxPlayers;
	}

	@Override
	public void setPlayers(List<Player> players) {
		this.players = players;
	}

	@Override
	public void setPublicField(PublicField publicField) {
		this.publicField = publicField;
	}

	@Override
	public void setPublicFieldsCount(int publicFieldsCount) {
		this.publicFieldsCount = publicFieldsCount;
	}

	@Override
	public void setSettings(IGameSettings settings) {
		this.settings = settings;
	}
}
