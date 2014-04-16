package de.htwg.madn;

import java.util.Scanner;

import org.apache.log4j.PropertyConfigurator;

import com.google.inject.Guice;
import com.google.inject.Injector;

import de.htwg.madn.controller.ControllerModule;
import de.htwg.madn.controller.IBoardControllerPort;
import de.htwg.madn.model.hibernate.HibernateModelModule;
import de.htwg.madn.view.tui.TUIView;

public final class Application {

	private static final Scanner SCANNER = new Scanner(System.in);

	private Application() {

	}

	public static void main(String[] args) {
		PropertyConfigurator.configure("log4j.properties");
		IBoardControllerPort boardController = getBoardController();
		startViews(boardController);
	}

	private static IBoardControllerPort getBoardController() {
		Injector injector = getConfiguredGuiceInjector();
		return injector.getInstance(IBoardControllerPort.class);
	}

	private static Injector getConfiguredGuiceInjector() {
		return Guice.createInjector(new HibernateModelModule(),
				new ControllerModule());
	}

	private static void startViews(IBoardControllerPort boardController) {
		// new GUIView(boardController);
		TUIView tui = new TUIView(boardController);

		// active waiting => infinite loop
		boolean quit = false;
		while (!quit) {
			quit = tui.handleInput(SCANNER.nextLine());
		}
	}

}
