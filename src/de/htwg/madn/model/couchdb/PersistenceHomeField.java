package de.htwg.madn.model.couchdb;

import org.ektorp.support.CouchDbDocument;
import org.ektorp.support.TypeDiscriminator;


public final class PersistenceHomeField extends CouchDbDocument {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1989195687653664714L;

	/**
	 * @TypeDiscriminator is used to mark properties that makes this class's
	 *                    documents unique in the database.
	 */
	@TypeDiscriminator
	public String id;

	private PersistencePlayer owner;
	private PersistenceFigure[] fields;
	private int exitIndex;

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

	public int getExitIndex() {
		return exitIndex;
	}

	public void setExitIndex(int exitIndex) {
		this.exitIndex = exitIndex;
	}

}
