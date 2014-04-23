package de.htwg.madn.model.couchdb;

import java.net.MalformedURLException;
import java.util.List;

import org.ektorp.CouchDbConnector;
import org.ektorp.CouchDbInstance;
import org.ektorp.http.HttpClient;
import org.ektorp.http.StdHttpClient;
import org.ektorp.impl.StdCouchDbInstance;

import com.google.inject.Inject;
import com.google.inject.name.Named;

import de.htwg.madn.model.GameId;
import de.htwg.madn.model.IBoard;
import de.htwg.madn.model.IModelDao;

public class CouchDbModelDao implements IModelDao {

	private CouchDbConnector db = null;

	@Inject
	public CouchDbModelDao(@Named("couchDBServerUrl") String serverUrl) {
		HttpClient client = null;
		try {
			client = new StdHttpClient.Builder().url(serverUrl).build();

		} catch (MalformedURLException e) {
			e.printStackTrace();
		}
		CouchDbInstance dbInstance = new StdCouchDbInstance(client);
		db = dbInstance.createConnector("madn_db", true);
	}

	@Override
	public IBoard getModel(GameId gameId) {
		PersistenceBoard board = db.find(PersistenceBoard.class,
				String.valueOf(gameId.getId()));
		if (board == null) {
			return null;
		}
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
	public List<GameId> getAllGameIds() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public IBoard createModel() {
		// TODO Auto-generated method stub
		return null;
	}

}
