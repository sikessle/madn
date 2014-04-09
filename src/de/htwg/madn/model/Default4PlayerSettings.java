package de.htwg.madn.model;

public class Default4PlayerSettings implements IGameSettings {

	@Override
	public int getMinPlayers() {
		return 1;
	}

	@Override
	public int getMaxPlayers() {
		return 4;
	}

	@Override
	public int getFiguresPerPlayer() {
		return 4;
	}

	@Override
	public int getPublicFieldsCount() {
		return 40;
	}

	@Override
	public int getDiceMin() {
		return 1;
	}

	@Override
	public int getDiceMax() {
		return 6;
	}

	@Override
	public int getMinNumberToExitHome() {
		return 6;
	}

	@Override
	public int getThrowsAllowedInHome() {
		return 3;
	}

	@Override
	public int getThrowsAllowedInPublic() {
		return 1;
	}

}
