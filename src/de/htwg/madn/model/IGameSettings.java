package de.htwg.madn.model;

/**
 * Game Settings
 */
public interface IGameSettings {

	/**
	 * Minimum of players needed to start the game.
	 * 
	 * @return number
	 */
	int getMinPlayers();

	/**
	 * Maximum of players allowed to join the game.
	 * 
	 * @return number
	 */
	int getMaxPlayers();

	/**
	 * Number of figures a player has.
	 * 
	 * @return number
	 */
	int getFiguresPerPlayer();

	/**
	 * Number of fields in public.
	 * 
	 * @return number
	 */
	int getPublicFieldsCount();

	/**
	 * Minimum dice number possible.
	 * 
	 * @return number
	 */
	int getDiceMin();

	/**
	 * Maximum dice number possible.
	 * 
	 * @return number
	 */
	int getDiceMax();

	/**
	 * Minium dice number required to leave the home field
	 * 
	 * @return number
	 */
	int getMinNumberToExitHome();

	/**
	 * Number of allowed throws in the home field.
	 * 
	 * @return number
	 */
	int getThrowsAllowedInHome();

	/**
	 * Number of allowed throws in the public field.
	 * 
	 * @return
	 */
	int getThrowsAllowedInPublic();

	public void setMinPlayers(int minPlayers);

	public void setMaxPlayers(int maxPlayers);

	public void setFiguresPerPlayer(int figuresPerPlayer);

	public void setPublicFieldsCount(int publicFieldsCount);

	public void setDiceMin(int diceMin);

	public void setDiceMax(int diceMax);

	public void setMinNumberToExitHome(int minNumberToExitHome);

	public void setThrowsAllowedInHome(int throwsAllowedInHome);

	public void setThrowsAllowedInPublic(int throwsAllowedInPublic);

}