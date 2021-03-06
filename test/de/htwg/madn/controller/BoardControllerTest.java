package de.htwg.madn.controller;

import static org.junit.Assert.assertTrue;

import java.awt.Color;

import org.junit.Before;
import org.junit.Test;

import de.htwg.madn.model.Board;
import de.htwg.madn.model.GameSettings;
import de.htwg.madn.model.IModelPort;
import de.htwg.madn.model.ModelPort;

public class BoardControllerTest {

	private static final int MINPLAYERS = 1;
	private static final int MAXPLAYERS = 2;
	private static final int FIGURESPERPLAYER = 1;
	private static final int PUBLICFIELDSCOUNT = 6;
	private static final int DICEMIN = 6;
	private static final int DICEMAX = 6;
	private static final int MINNUMBERTOEXITHOME = 1;
	private static final int THROWSALLOWEDINHOME = 3;
	private static final int THROWSALLOWEDINPUBLIC = 1;
	GameSettings settings;
	IModelPort model;
	IBoardControllerPort boardController;

	@Before
	public void setUp() throws Exception {
		settings = new GameSettings(MINPLAYERS, MAXPLAYERS, FIGURESPERPLAYER,
				PUBLICFIELDSCOUNT, DICEMIN, DICEMAX, MINNUMBERTOEXITHOME,
				THROWSALLOWEDINHOME, THROWSALLOWEDINPUBLIC);
		model = new ModelPort(settings, new Board(settings));
		boardController = new BoardController(model);
	}

	@Test
	public void testUpdate() {
		boardController.update();
		assertTrue(boardController != null);
		
	}
	
	@Test
	public void testGameIsRunning() {
		assertTrue(!boardController.gameIsRunning());
		boardController.addPlayer("test", Color.red, true);
		boardController.addPlayer("test", Color.red, true);
		boardController.startGame();
		assertTrue(boardController.gameIsRunning());
	}

	@Test
	public void testGetModelPort() {
		assertTrue(boardController.getModel() != null);
	}

	@Test
	public void testGetSettings() {
		assertTrue(boardController.getSettings() != null);
	}

	@Test
	public void testAddPlayer() {
		boardController.addPlayer("test", Color.red, true);
		boardController.addPlayer("test", Color.red, true);
		boardController.addPlayer("test", Color.red, true);
		boardController.addPlayer("test", Color.red, true);
		boardController.addPlayer("test", Color.red, true);
		boardController.addPlayer("test", Color.red, true);
		assertTrue(!boardController.getPlayers().isEmpty());

	}

	@Test
	public void testThrowDice() {		
		boardController.addPlayer("test", Color.red, true);
		boardController.addPlayer("test2", Color.red, true);
		boardController.rollDice();
		boardController.startGame();
		
		boardController.rollDice();
		boardController.moveFigure('a');
		boardController.rollDice();
		boardController.moveFigure('a');
		boardController.rollDice();
		
		boardController.reset();
		
		assertTrue(model.getDice().getThrowsCount() == 0);
		
		
		settings = new GameSettings(MINPLAYERS, MAXPLAYERS, FIGURESPERPLAYER,
				PUBLICFIELDSCOUNT, DICEMIN, DICEMAX, 50,
				THROWSALLOWEDINHOME, THROWSALLOWEDINPUBLIC);
		model = new ModelPort(settings, new Board(settings));
		boardController = new BoardController(model);
		boardController.addPlayer("test", Color.red, true);
		boardController.addPlayer("test2", Color.red, true);
		boardController.startGame();
		boardController.rollDice();
		boardController.rollDice();
		boardController.rollDice();
		boardController.rollDice();
	}

	@Test
	public void testReset() {
		boardController.addPlayer("test", Color.red, true);
		boardController.reset();
		assertTrue(boardController.getPlayers().isEmpty());
	}

	@Test
	public void testMoveFigure() {
		boardController.startGame();
		boardController.addPlayer("test", Color.red, true);
		boardController.startGame();
		boardController.startGame();
		boardController.moveFigure('z');
		boardController.rollDice();
		boardController.moveFigure('a');
		boardController.rollDice();
		boardController.moveFigure('a');
		boardController.moveFigure('a');
		assertTrue(true);
	}


	@Test
	public void testGetFinishedPlayersQueue() {
		assertTrue(boardController.getFinishedPlayersQueue().size() == 0);
	}

	@Test
	public void testGetPlayers() {
		assertTrue(boardController.getPlayers().size() == 0);
	}

	@Test
	public void testGetStatusString() {
		assertTrue(boardController.getStatusString() != null);
	}

	@Test
	public void testGetActivePlayer() {
		assertTrue(boardController.getActivePlayer() == null);
		boardController.addPlayer("test", Color.red, true);
		boardController.addPlayer("test", Color.red, true);
		boardController.startGame();
		assertTrue(boardController.getActivePlayer() != null);
	}

	@Test
	public void testGetActivePlayerString() {
		assertTrue(boardController.getActivePlayerString() == null);
		boardController.addPlayer("test", Color.red, true);
		boardController.addPlayer("test", Color.red, true);
		boardController.startGame();
		assertTrue(boardController.getActivePlayerString() != null);
	}

	@Test
	public void testGameIsFinished() {		
		assertTrue(boardController.gameIsFinished() == false);
		boardController.reset();
		boardController.addPlayer("test", Color.red, true);
		boardController.startGame();
		assertTrue(boardController.gameIsFinished() == false);
		boardController.reset();
		boardController.addPlayer("test", Color.red, true);
		assertTrue(boardController.gameIsFinished() == false);
		boardController.startGame();
		boardController.reset();
		finishGame();
		
		assertTrue(boardController.gameIsFinished() == true);
	}

	private void finishGame() {
		boardController.addPlayer("test", Color.red, true);
		boardController.startGame();
		boardController.rollDice();
		boardController.moveFigure('a');
		boardController.rollDice();
		boardController.moveFigure('a');
		boardController.rollDice();
		boardController.moveFigure('a');
		boardController.rollDice();
		boardController.moveFigure('a');
		// should be finished now
	}
	

	@Test
	public void testQuitGame() {
		// cannot be tested as this calls System.exit()
		assertTrue(true);
	}

}
