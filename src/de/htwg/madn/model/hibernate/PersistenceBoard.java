package de.htwg.madn.model.hibernate;

import java.io.Serializable;
import java.util.List;
import java.util.Queue;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import de.htwg.madn.model.Dice;
import de.htwg.madn.model.FinishField;
import de.htwg.madn.model.GameId;
import de.htwg.madn.model.HomeField;
import de.htwg.madn.model.IGameSettings;
import de.htwg.madn.model.Player;
import de.htwg.madn.model.PublicField;

@Entity
public final class PersistenceBoard implements Serializable {

	private static final long serialVersionUID = -5770225591504645761L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int dbId;

	private List<HomeField> homeFields;
	private List<FinishField> finishFields;
	private List<Player> players;
	private Queue<Player> activePlayersQueue;
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

	public List<HomeField> getHomeFields() {
		return homeFields;
	}

	public void setHomeFields(List<HomeField> homeFields) {
		this.homeFields = homeFields;
	}

	public List<FinishField> getFinishFields() {
		return finishFields;
	}

	public void setFinishFields(List<FinishField> finishFields) {
		this.finishFields = finishFields;
	}

	public List<Player> getPlayers() {
		return players;
	}

	public void setPlayers(List<Player> players) {
		this.players = players;
	}

	public Queue<Player> getActivePlayersQueue() {
		return activePlayersQueue;
	}

	public void setActivePlayersQueue(Queue<Player> activePlayersQueue) {
		this.activePlayersQueue = activePlayersQueue;
	}

	public Queue<Player> getFinishedPlayersQueue() {
		return finishedPlayersQueue;
	}

	public void setFinishedPlayersQueue(Queue<Player> finishedPlayersQueue) {
		this.finishedPlayersQueue = finishedPlayersQueue;
	}

	public Player getActivePlayer() {
		return activePlayer;
	}

	public void setActivePlayer(Player activePlayer) {
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

	public PublicField getPublicField() {
		return publicField;
	}

	public void setPublicField(PublicField publicField) {
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

	public Dice getDice() {
		return dice;
	}

	public void setDice(Dice dice) {
		this.dice = dice;
	}

	public IGameSettings getSettings() {
		return settings;
	}

	public void setSettings(IGameSettings settings) {
		this.settings = settings;
	}

	public GameId getGameId() {
		return gameId;
	}

	public void setGameId(GameId gameId) {
		this.gameId = gameId;
	}

}
