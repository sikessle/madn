package de.htwg.madn.controller;

import com.google.inject.AbstractModule;

public class ControllerModule extends AbstractModule {

	@Override
	protected void configure() {
		bind(IBoardControllerPort.class).to(BoardController.class);
	}

}
