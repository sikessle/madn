package de.htwg.madn.model.hibernate;

import java.util.LinkedList;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Transaction;
import org.hibernate.classic.Session;
import org.hibernate.criterion.Restrictions;

import com.google.inject.Inject;
import com.google.inject.Injector;

import de.htwg.madn.model.GameId;
import de.htwg.madn.model.IBoard;
import de.htwg.madn.model.IModelDao;

public class HibernateModelDao implements IModelDao {

	private final Injector injector;
	private final DomainObjectToPersistenceTransformer domainToPersistence;
	private final PersistenceToDomainObjectTransformer persistenceToDomain;

	@Inject
	public HibernateModelDao(Injector injector) {
		this.injector = injector;
		domainToPersistence = new DomainObjectToPersistenceTransformer();
		persistenceToDomain = new PersistenceToDomainObjectTransformer(injector);
	}

	@Override
	public IBoard getModel(final GameId gameId) {
		final List<PersistenceBoard> persistenceBoards = getPersistenceBoardsByDbId(gameId
				.getId());

		if (persistenceBoards.size() > 1) {
			throw new IllegalStateException(
					"GameId is not unique in database. Multiple records found.");
		}

		if (persistenceBoards.isEmpty()) {
			return null;
		}

		IBoard board = persistenceToDomain.transform(persistenceBoards.get(0));

		return board;
	}

	@Override
	public void deleteModel(IBoard model) {
		final List<PersistenceBoard> boards = getPersistenceBoardsByDbId(model
				.getGameId().getId());

		executeInTransactionContext(new HibernateTransactionCommand() {
			@Override
			public void execute(Session session) {
				for (PersistenceBoard board : boards) {
					session.delete(board);
				}
			}
		});
	}

	private List<PersistenceBoard> getPersistenceBoardsByDbId(final int dbId) {
		final List<PersistenceBoard> boards = new LinkedList<PersistenceBoard>();

		executeInTransactionContext(new HibernateTransactionCommand() {
			@Override
			public void execute(Session session) {
				@SuppressWarnings("unchecked")
				List<PersistenceBoard> localBoards = session
						.createCriteria(PersistenceBoard.class)
						.add(Restrictions.idEq(dbId)).list();

				boards.addAll(localBoards);
			}
		});

		return boards;
	}

	@Override
	public GameId storeModel(IBoard model, String comment) {
		GameId gameId = model.getGameId();

		if (gameId == null) {
			gameId = storeNewModel(model, comment);
		} else {
			updateModel(model, comment);
		}

		return gameId;
	}

	// stores the model and uses the generated dbId to set the GameId to the
	// same value and restore it in the database
	private GameId storeNewModel(IBoard model, String comment) {

		final PersistenceBoard persistenceBoard = domainToPersistence
				.transform(model);
		final List<Integer> ids = new LinkedList<Integer>();

		executeInTransactionContext(new HibernateTransactionCommand() {
			@Override
			public void execute(Session session) {
				int dbId = (Integer) session.save(persistenceBoard);
				ids.add(dbId);
			}
		});

		GameId gameId = new GameId(ids.get(0));
		model.setGameId(gameId);
		persistenceBoard.setGameId(domainToPersistence.transform(gameId));

		// update the saved board with the new game id in database
		executeInTransactionContext(new HibernateTransactionCommand() {
			@Override
			public void execute(Session session) {
				session.save(persistenceBoard);
			}
		});

		return gameId;
	}

	private void updateModel(IBoard model, String comment) {
		model.getGameId().setComment(comment);
		final PersistenceBoard persistenceBoard = domainToPersistence
				.transform(model);

		executeInTransactionContext(new HibernateTransactionCommand() {
			@Override
			public void execute(Session session) {
				session.save(persistenceBoard);
			}
		});
	}

	@Override
	public List<GameId> getAllGameIds() {
		final List<GameId> gameIds = new LinkedList<GameId>();

		executeInTransactionContext(new HibernateTransactionCommand() {
			@Override
			public void execute(Session session) {
				@SuppressWarnings("unchecked")
				List<PersistenceGameId> persistenceGameIds = session
						.createCriteria(PersistenceGameId.class).list();

				for (PersistenceGameId pId : persistenceGameIds) {
					gameIds.add(persistenceToDomain.transform(pId));
				}
			}
		});

		return gameIds;
	}

	private void executeInTransactionContext(HibernateTransactionCommand cmd) {
		Transaction tx = null;
		try {
			Session session = HibernateUtil.getInstance().getCurrentSession();
			tx = session.beginTransaction();
			cmd.execute(session);
			tx.commit();
		} catch (HibernateException ex) {
			if (tx != null) {
				try {
					tx.rollback();
				} catch (HibernateException exRb) {
					throw new RuntimeException(ex.getMessage());
				}
			}
		}
	}

	@Override
	public IBoard createModel() {
		return injector.getInstance(IBoard.class);
	}
}
