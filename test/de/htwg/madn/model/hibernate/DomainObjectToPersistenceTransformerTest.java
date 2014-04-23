package de.htwg.madn.model.hibernate;

import static org.junit.Assert.*;

import java.awt.Color;

import org.apache.log4j.PropertyConfigurator;
import org.junit.Before;
import org.junit.Test;

import com.google.inject.Guice;
import com.google.inject.Injector;

import de.htwg.madn.controller.ControllerModule;
import de.htwg.madn.controller.IBoardControllerPort;
import de.htwg.madn.view.tui.TUIView;

public class DomainObjectToPersistenceTransformerTest {

	private IBoardControllerPort controller;

	@Before
	public void setUp() throws Exception {
		PropertyConfigurator.configure("log4j.properties");
		Injector injector = Guice.createInjector(new HibernateModelModule(),
				new ControllerModule());
		controller = injector.getInstance(IBoardControllerPort.class);
	}

	@Test
	public void transform() {
		DomainObjectToPersistenceTransformer trans = new DomainObjectToPersistenceTransformer();

		controller.addPlayer("test", Color.BLACK, true);
		controller.addPlayer("test2", Color.BLACK, true);
		controller.startGame();
		PersistenceBoard pBoard = trans.transform(controller.getModel());
	}

}
