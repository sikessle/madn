package de.htwg.madn.model.hibernate;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

import de.htwg.madn.model.Figure;
import de.htwg.madn.model.FinishField;
import de.htwg.madn.model.GameId;
import de.htwg.madn.model.IBoard;
import de.htwg.madn.model.Player;

public class DomainObjectToPersistenceTransformer {

	public PersistenceBoard transform(IBoard board) {
		PersistenceBoard persistenceBoard = new PersistenceBoard();

		persistenceBoard.setActivePlayer(transform(board.getActivePlayer()));

		return persistenceBoard;
	}

	private PersistencePlayer transform(Player player) {
		PersistencePlayer persistencePlayer = new PersistencePlayer();
		persistencePlayer.setColor(player.getColor());
		persistencePlayer.setFigures(transform(player.getFigures(),
				persistencePlayer));
		persistencePlayer.setFinishField(transform(player.getFinishField(),
				persistencePlayer));
		persistencePlayer.setHomeField(null); // TODO

		return null;
	}

	private PersistenceFinishField transform(FinishField finishField,
			PersistencePlayer owner) {
		PersistenceFinishField result = new PersistenceFinishField();

		result.setEntryIndex(finishField.getEntryIndex());
		result.setFields(transform(finishField.getFields(), owner));
		result.setOwner(owner);

		return result;
	}

	private PersistenceFigure[] transform(Figure[] fields,
			PersistencePlayer owner) {
		return (PersistenceFigure[]) transform(Arrays.asList(fields), owner)
				.toArray();
	}

	private List<PersistenceFigure> transform(List<Figure> figures,
			PersistencePlayer owner) {
		List<PersistenceFigure> result = new LinkedList<PersistenceFigure>();

		for (Figure fig : figures) {
			result.add(transform(fig, owner));
		}

		return result;
	}

	private PersistenceFigure transform(Figure fig, PersistencePlayer owner) {
		PersistenceFigure persistenceFigure = new PersistenceFigure();

		persistenceFigure.setAtFinishArea(fig.isAtFinishArea());
		persistenceFigure.setAtHomeArea(fig.isAtHomeArea());
		persistenceFigure.setCurrentFieldIndex(fig.getCurrentFieldIndex());
		persistenceFigure.setFinished(fig.isFinished());
		persistenceFigure.setLetter(fig.getLetter());
		persistenceFigure.setOwner(owner);

		return persistenceFigure;
	}

	public PersistenceGameId transform(GameId gameId) {
		PersistenceGameId persistenceGameId = new PersistenceGameId();
		persistenceGameId.setId(gameId.getId());
		persistenceGameId.setComment(gameId.getComment());
		return persistenceGameId;
	}
}
