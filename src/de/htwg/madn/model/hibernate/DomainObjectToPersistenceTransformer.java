package de.htwg.madn.model.hibernate;

import com.google.inject.Injector;

import de.htwg.madn.model.IBoard;

public class DomainObjectToPersistenceTransformer {

	public PersistenceBoard transform(IBoard board) {
		PersistenceBoard persistenceBoard = new PersistenceBoard();
		// persistenceBoard.setDice(board.getDice())..
		return null;
	}
}