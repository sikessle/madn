package de.htwg.madn.model.hibernate;

import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Transaction;
import org.hibernate.classic.Session;

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
	public IBoard getModel(GameId gameId) {
		IBoard board = null;
		PersistenceBoard persistenceBoard = null;

		executeInTransactionContext(new HibernateTransactionCommand() {
			@Override
			public void execute() {

			}
		});

		if (persistenceBoard != null) {
			board = persistenceToDomain.transform(persistenceBoard);
		}

		return board;
	}

	@Override
	public void deleteModel(IBoard model) {
		// TODO Auto-generated method stub

	}

	@Override
	public GameId storeModel(IBoard model, String comment) {
		PersistenceBoard persistenceBoard = domainToPersistence
				.transform(model);

		executeInTransactionContext(new HibernateTransactionCommand() {
			@Override
			public void execute() {

			}
		});
		return null;
	}

	@Override
	public List<GameId> getAllGameIds() {
		// TODO Auto-generated method stub
		return null;
	}

	private void executeInTransactionContext(HibernateTransactionCommand cmd) {
		Transaction tx = null;
		try {
			Session session = HibernateUtil.getInstance().getCurrentSession();
			tx = session.beginTransaction();
			cmd.execute();
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
