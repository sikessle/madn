package de.htwg.madn.model.hibernate;

import java.util.List;

import de.htwg.madn.model.GameId;
import de.htwg.madn.model.IBoard;
import de.htwg.madn.model.IModelDao;

public class HibernateModelDao implements IModelDao {

	@Override
	public IBoard getModel(GameId gameId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void deleteModel(IBoard model) {
		// TODO Auto-generated method stub

	}

	@Override
	public GameId storeModel(IBoard model, String comment) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<IBoard> getAllModels() {
		// TODO Auto-generated method stub
		return null;
	}

}
