package de.htwg.madn.model.hibernate;

import com.google.inject.Injector;

import de.htwg.madn.model.GameId;
import de.htwg.madn.model.IBoard;

public class PersistenceToDomainObjectTransformer {

	private final Injector injector;

	public PersistenceToDomainObjectTransformer(Injector injector) {
		this.injector = injector;
	}

	public IBoard transform(PersistenceBoard persistenceBoard) {
		IBoard board = injector.getInstance(IBoard.class);
		// board.setDice(persistenceBoard.getDice());
		return null;
	}

	public GameId transform(PersistenceGameId persistenceGameId) {
		GameId gameId = new GameId(persistenceGameId.getId());
		gameId.setComment(persistenceGameId.getComment());
		return gameId;
	}
}
