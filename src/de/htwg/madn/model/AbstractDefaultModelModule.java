package de.htwg.madn.model;

import com.google.inject.AbstractModule;

public class AbstractDefaultModelModule extends AbstractModule {

	@Override
	protected void configure() {
		bind(IGameSettings.class).to(Default4PlayerSettings.class);
		bind(IBoard.class).to(Board.class);
	}

}
