package de.htwg.madn.model.db4o;

import java.util.List;

import com.db4o.Db4oEmbedded;
import com.db4o.EmbeddedObjectContainer;
import com.db4o.ObjectSet;
import com.db4o.query.Predicate;
import com.google.inject.Inject;
import com.google.inject.name.Named;

import de.htwg.madn.model.GameId;
import de.htwg.madn.model.IBoard;
import de.htwg.madn.model.IModelDao;

@SuppressWarnings("serial")
public class Db4oModelDao implements IModelDao {

	private static final int STARTING_ID = 1;
	private final EmbeddedObjectContainer db;

	@Inject
	public Db4oModelDao(@Named("db4oPath") String dbPath) {
		db = Db4oEmbedded.openFile(dbPath);
	}

	@Override
	public IBoard getModel(final GameId gameId) {

		ObjectSet<IBoard> boards = db.query(new Predicate<IBoard>() {
			@Override
			public boolean match(IBoard board) {
				return board.getGameId().equals(gameId);
			}
		});

		if (boards.size() > 1) {
			throw new IllegalStateException(
					"GameId is not unique in database. Multiple records found.");
		}

		if (boards.isEmpty()) {
			return null;
		}

		return boards.get(0);
	}

	@Override
	public void deleteModel(IBoard model) {
		db.delete(model);
	}

	@Override
	public GameId storeModel(IBoard model, String comment) {
		ensureModelHasGameId(model, comment);
		db.store(model);
		return model.getGameId();
	}

	private void ensureModelHasGameId(IBoard model, String comment) {
		if (model.getGameId() == null) {
			GameId id = getNextFreeGameId();
			id.setComment(comment);
			model.setGameId(id);
		}
	}

	private GameId getNextFreeGameId() {
		List<IBoard> models = getAllModels();
		GameId highestId = null;

		if (models.isEmpty()) {
			return new GameId(STARTING_ID);
		}

		for (IBoard model : models) {
			if (model.getGameId().compareTo(highestId) > 0) {
				highestId = model.getGameId();
			}
		}

		return new GameId(highestId.getId() + 1);
	}

	@Override
	public List<IBoard> getAllModels() {
		return db.query(IBoard.class);
	}

}
