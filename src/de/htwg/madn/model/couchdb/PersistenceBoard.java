package de.htwg.madn.model.couchdb;

import java.util.List;
import java.util.Queue;

import org.ektorp.support.CouchDbDocument;
import org.ektorp.support.TypeDiscriminator;

public final class PersistenceBoard extends CouchDbDocument {


	/**
	 * 
	 */
	private static final long serialVersionUID = 8782332851554942717L;
	
	/**
	 * @TypeDiscriminator is used to mark properties that makes this class's
	 *                    documents unique in the database.
	 */
	@TypeDiscriminator
	public String id;

	private List<PersistenceHomeField> homeFields;
	private List<PersistenceFinishField> finishFields;
	private List<PersistencePlayer> players;
	private Queue<PersistencePlayer> activePlayersQueue;
	private Queue<PersistencePlayer> finishedPlayersQueue;
	private PersistencePlayer activePlayer;
	private boolean gameIsRunning;
	private String status = "";

	private PersistencePublicField publicField;
	private int maxPlayers;
	private int figuresPerPlayer;
	private int publicFieldsCount;
	private int diceMin;
	private int diceMax;
	private PersistenceDice dice;
	private PersistenceGameSettings settings;
	private PersistenceGameId gameId;

	public List<PersistenceHomeField> getHomeFields() {
		return homeFields;
	}

	public void setHomeFields(List<PersistenceHomeField> homeFields) {
		this.homeFields = homeFields;
	}

	public List<PersistenceFinishField> getFinishFields() {
		return finishFields;
	}

	public void setFinishFields(List<PersistenceFinishField> finishFields) {
		this.finishFields = finishFields;
	}

	public List<PersistencePlayer> getPlayers() {
		return players;
	}

	public void setPlayers(List<PersistencePlayer> players) {
		this.players = players;
	}

	public Queue<PersistencePlayer> getActivePlayersQueue() {
		return activePlayersQueue;
	}

	public void setActivePlayersQueue(
			Queue<PersistencePlayer> activePlayersQueue) {
		this.activePlayersQueue = activePlayersQueue;
	}

	public Queue<PersistencePlayer> getFinishedPlayersQueue() {
		return finishedPlayersQueue;
	}

	public void setFinishedPlayersQueue(
			Queue<PersistencePlayer> finishedPlayersQueue) {
		this.finishedPlayersQueue = finishedPlayersQueue;
	}

	public PersistencePlayer getActivePlayer() {
		return activePlayer;
	}

	public void setActivePlayer(PersistencePlayer activePlayer) {
		this.activePlayer = activePlayer;
	}

	public boolean isGameIsRunning() {
		return gameIsRunning;
	}

	public void setGameIsRunning(boolean gameIsRunning) {
		this.gameIsRunning = gameIsRunning;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public PersistencePublicField getPublicField() {
		return publicField;
	}

	public void setPublicField(PersistencePublicField publicField) {
		this.publicField = publicField;
	}

	public int getMaxPlayers() {
		return maxPlayers;
	}

	public void setMaxPlayers(int maxPlayers) {
		this.maxPlayers = maxPlayers;
	}

	public int getFiguresPerPlayer() {
		return figuresPerPlayer;
	}

	public void setFiguresPerPlayer(int figuresPerPlayer) {
		this.figuresPerPlayer = figuresPerPlayer;
	}

	public int getPublicFieldsCount() {
		return publicFieldsCount;
	}

	public void setPublicFieldsCount(int publicFieldsCount) {
		this.publicFieldsCount = publicFieldsCount;
	}

	public int getDiceMin() {
		return diceMin;
	}

	public void setDiceMin(int diceMin) {
		this.diceMin = diceMin;
	}

	public int getDiceMax() {
		return diceMax;
	}

	public void setDiceMax(int diceMax) {
		this.diceMax = diceMax;
	}

	public PersistenceDice getDice() {
		return dice;
	}

	public void setDice(PersistenceDice dice) {
		this.dice = dice;
	}

	public PersistenceGameSettings getSettings() {
		return settings;
	}

	public void setSettings(PersistenceGameSettings settings) {
		this.settings = settings;
	}

	public PersistenceGameId getGameId() {
		return gameId;
	}

	public void setGameId(PersistenceGameId gameId) {
		this.gameId = gameId;
	}

}
