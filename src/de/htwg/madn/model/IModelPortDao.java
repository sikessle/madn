package de.htwg.madn.model;

public interface IModelPortDao {

	IModelPort getModelPort();

	boolean deleteModelPort(IModelPort modelPort);

	void saveModelPort(IModelPort modelPort);
}
