package de.htwg.madn.model.couchdb;

import org.ektorp.support.CouchDbDocument;
import org.ektorp.support.TypeDiscriminator;

public final class PersistencePublicField extends CouchDbDocument {

	/**
	 * 
	 */
	private static final long serialVersionUID = -6786749319789609417L;

	/**
	 * @TypeDiscriminator is used to mark properties that makes this class's
	 *                    documents unique in the database.
	 */
	@TypeDiscriminator
	public String id;
	
	private PersistenceFigure[] fields;

	public PersistenceFigure[] getFields() {
		return fields;
	}

	public void setFields(PersistenceFigure[] fields) {
		this.fields = fields;
	}

}
