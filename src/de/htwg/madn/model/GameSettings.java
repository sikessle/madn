package de.htwg.madn.model;

public final class GameSettings implements IGameSettings {
	private int minPlayers;
	private int maxPlayers;
	private int figuresPerPlayer;
	private int publicFieldsCount;
	private int diceMin;
	private int diceMax;
	private int minNumberToExitHome;
	private int throwsAllowedInHome;
	private int throwsAllowedInPublic;

	public GameSettings(int minPlayers, int maxPlayers, int figuresPerPlayer,
			int publicFieldsCount, int diceMin, int diceMax,
			int minNumberToExitHome, int throwsAllowedInHome,
			int throwsAllowedInPublic) {

		this.minPlayers = minPlayers;
		this.maxPlayers = maxPlayers;
		this.figuresPerPlayer = figuresPerPlayer;
		this.publicFieldsCount = publicFieldsCount;
		this.diceMin = diceMin;
		this.diceMax = diceMax;
		this.minNumberToExitHome = minNumberToExitHome;
		this.throwsAllowedInHome = throwsAllowedInHome;
		this.throwsAllowedInPublic = throwsAllowedInPublic;

		verifySettings();
	}

	private void verifySettings() {
		verifyPlayerSettings();
		verifyFiguresSettings();
		verifyPublicFieldsSettings();
		verifyThrowsAllowedSettings();
	}

	private void verifyPlayerSettings() {
		if (minPlayers > maxPlayers || minPlayers < 1) {
			throw new IllegalArgumentException("player settings not valid");
		}
	}

	private void verifyFiguresSettings() {
		if (figuresPerPlayer < 1) {
			throw new IllegalArgumentException("figures settings not valid");
		}
	}

	private void verifyPublicFieldsSettings() {
		if (publicFieldsCount < 1 || publicFieldsCount % maxPlayers != 0) {
			throw new IllegalArgumentException(
					"public fields settings not valid");
		}
	}

	private void verifyThrowsAllowedSettings() {
		if (throwsAllowedInHome < 1 || throwsAllowedInPublic < 1) {
			throw new IllegalArgumentException(
					"throws allowed settings not valid");
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see de.htwg.madn.model.IGameSettings#getMinPlayers()
	 */
	@Override
	public int getMinPlayers() {
		return minPlayers;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see de.htwg.madn.model.IGameSettings#getMaxPlayers()
	 */
	@Override
	public int getMaxPlayers() {
		return maxPlayers;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see de.htwg.madn.model.IGameSettings#getFiguresPerPlayer()
	 */
	@Override
	public int getFiguresPerPlayer() {
		return figuresPerPlayer;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see de.htwg.madn.model.IGameSettings#getPublicFieldsCount()
	 */
	@Override
	public int getPublicFieldsCount() {
		return publicFieldsCount;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see de.htwg.madn.model.IGameSettings#getDiceMin()
	 */
	@Override
	public int getDiceMin() {
		return diceMin;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see de.htwg.madn.model.IGameSettings#getDiceMax()
	 */
	@Override
	public int getDiceMax() {
		return diceMax;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see de.htwg.madn.model.IGameSettings#getMinNumberToExitHome()
	 */
	@Override
	public int getMinNumberToExitHome() {
		return minNumberToExitHome;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see de.htwg.madn.model.IGameSettings#getThrowsAllowedInHome()
	 */
	@Override
	public int getThrowsAllowedInHome() {
		return throwsAllowedInHome;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see de.htwg.madn.model.IGameSettings#getThrowsAllowedInPublic()
	 */
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
