package de.htwg.madn.model.hibernate;

import java.io.Serializable;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import de.htwg.madn.model.IGameSettings;

public final class PersistenceGameSettings implements IGameSettings, Serializable {
	
	private static final long serialVersionUID = 4007513131073987575L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int dbId;
	
	private int minPlayers;
	private int maxPlayers;
	private int figuresPerPlayer;
	private int publicFieldsCount;
	private int diceMin;
	private int diceMax;
	private int minNumberToExitHome;
	private int throwsAllowedInHome;
	private int throwsAllowedInPublic;
	
	public int getMinPlayers() {
		return minPlayers;
	}
	public void setMinPlayers(int minPlayers) {
		this.minPlayers = minPlayers;
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
	public int getMinNumberToExitHome() {
		return minNumberToExitHome;
	}
	public void setMinNumberToExitHome(int minNumberToExitHome) {
		this.minNumberToExitHome = minNumberToExitHome;
	}
	public int getThrowsAllowedInHome() {
		return throwsAllowedInHome;
	}
	public void setThrowsAllowedInHome(int throwsAllowedInHome) {
		this.throwsAllowedInHome = throwsAllowedInHome;
	}
	public int getThrowsAllowedInPublic() {
		return throwsAllowedInPublic;
	}
	public void setThrowsAllowedInPublic(int throwsAllowedInPublic) {
		this.throwsAllowedInPublic = throwsAllowedInPublic;
	}
}
