package de.htwg.madn.model.hibernate;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import de.htwg.madn.model.Dice;
import de.htwg.madn.model.FinishField;
import de.htwg.madn.model.GameId;
import de.htwg.madn.model.HomeField;
import de.htwg.madn.model.IGameSettings;
import de.htwg.madn.model.Player;
import de.htwg.madn.model.PublicField;

@Entity
@Table(name = "board")
public class PersistentBoard implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	public Integer id;

	@OneToMany(mappedBy = "board")
	private List<HomeField> homeFields;
	@OneToMany(mappedBy = "board")
	private List<FinishField> finishFields;
	@OneToMany(mappedBy = "board")
	private List<Player> players;
	@OneToMany(mappedBy = "board")
	private PublicField publicField;
	private int maxPlayers;
	private int figuresPerPlayer;
	private int publicFieldsCount;
	private int diceMin;
	private int diceMax;
	@OneToOne(mappedBy = "board")
	private Dice dice;
	@OneToOne(mappedBy = "board")
	private IGameSettings settings;
	@OneToOne(mappedBy = "board")
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
