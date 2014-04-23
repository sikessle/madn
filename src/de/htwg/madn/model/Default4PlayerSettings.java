package de.htwg.madn.model;

public class Default4PlayerSettings implements IGameSettings {

	private int minPlayers = 1;
	private int maxPlayers = 4;
	private int figuresPerPlayer = 4;
	private int publicFieldsCount = 40;
	private int diceMin = 1;
	private int diceMax = 4;
	private int minNumberToExitHome = 6;
	private int throwsAllowedInHome = 3;
	private int throwsAllowedInPublic = 1;

	@Override
	public int getMinPlayers() {
		return minPlayers;
	}

	@Override
	public int getMaxPlayers() {
		return maxPlayers;
	}

	@Override
	public int getFiguresPerPlayer() {
		return figuresPerPlayer;
	}

	@Override
	public int getPublicFieldsCount() {
		return publicFieldsCount;
	}

	@Override
	public int getDiceMin() {
		return diceMin;
	}

	@Override
	public int getDiceMax() {
		return diceMax;
	}

	@Override
	public int getMinNumberToExitHome() {
		return minNumberToExitHome;
	}

	@Override
	public int getThrowsAllowedInHome() {
		return throwsAllowedInHome;
	}

	@Override
	public int getThrowsAllowedInPublic() {
		return throwsAllowedInPublic;
	}

	@Override
	public void setMinPlayers(int minPlayers) {
		this.minPlayers = minPlayers;
	}

	@Override
	public void setMaxPlayers(int maxPlayers) {
		this.maxPlayers = maxPlayers;
	}

	@Override
	public void setFiguresPerPlayer(int figuresPerPlayer) {
		this.figuresPerPlayer = figuresPerPlayer;
	}

	@Override
	public void setPublicFieldsCount(int publicFieldsCount) {
		this.publicFieldsCount = publicFieldsCount;
	}

	@Override
	public void setDiceMin(int diceMin) {
		this.diceMin = diceMin;
	}

	@Override
	public void setDiceMax(int diceMax) {
		this.diceMax = diceMax;
	}

	@Override
	public void setMinNumberToExitHome(int minNumberToExitHome) {
		this.minNumberToExitHome = minNumberToExitHome;
	}

	@Override
	public void setThrowsAllowedInHome(int throwsAllowedInHome) {
		this.throwsAllowedInHome = throwsAllowedInHome;
	}

	@Override
	public void setThrowsAllowedInPublic(int throwsAllowedInPublic) {
		this.throwsAllowedInPublic = throwsAllowedInPublic;
	}

}
