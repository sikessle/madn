package de.htwg.madn.model.couchdb;

import org.ektorp.support.CouchDbDocument;
import org.ektorp.support.TypeDiscriminator;

public class PersistenceFinishField extends CouchDbDocument {

	/**
	 * 
	 */
	private static final long serialVersionUID = 833922773541078720L;
	
	/**
	 * @TypeDiscriminator is used to mark properties that makes this class's
	 *                    documents unique in the database.
	 */
	@TypeDiscriminator
	public String id;
	
	private PersistencePlayer owner;
	private PersistenceFigure[] fields;
	private int entryIndex;

	public PersistencePlayer getOwner() {
		return owner;
	}

	public void setOwner(PersistencePlayer owner) {
		this.owner = owner;
	}

	public PersistenceFigure[] getFields() {
		return fields;
	}

	public void setFields(PersistenceFigure[] fields) {
		this.fields = fields;
	}

	public int getEntryIndex() {
		return entryIndex;
	}

	public void setEntryIndex(int entryIndex) {
		this.entryIndex = entryIndex;
	}

}
