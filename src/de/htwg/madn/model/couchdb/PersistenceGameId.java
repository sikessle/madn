package de.htwg.madn.model.couchdb;

import org.ektorp.support.CouchDbDocument;
import org.ektorp.support.TypeDiscriminator;


public class PersistenceGameId extends CouchDbDocument{

	/**
	 * 
	 */
	private static final long serialVersionUID = -2054308755791210997L;
	
	/**
	 * @TypeDiscriminator is used to mark properties that makes this class's
	 *                    documents unique in the database.
	 */
	//??? muss es ein String sein?
	@TypeDiscriminator
	private int gameId;
	private String comment;

	public int getGameId() {
		return gameId;
	}

	public void setGameId(int id) {
		this.gameId = id;
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

}
