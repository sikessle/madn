package de.htwg.madn.model;

import java.util.List;

public interface IModelDao {

	IBoard getModel(GameId gameId);

	void deleteModel(IBoard model);

	GameId storeModel(IBoard model, String comment);

	List<IBoard> getAllModels();

	IBoard createModel();
}
