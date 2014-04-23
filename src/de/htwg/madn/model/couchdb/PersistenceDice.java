package de.htwg.madn.model.couchdb;

import org.ektorp.support.CouchDbDocument;
import org.ektorp.support.TypeDiscriminator;

public final class PersistenceDice extends CouchDbDocument {

	/**
	 * 
	 */
	private static final long serialVersionUID = 8244483004340409627L;
	
	/**
	 * @TypeDiscriminator is used to mark properties that makes this class's
	 *                    documents unique in the database.
	 */
	@TypeDiscriminator
	public String id;

	private int min;
	private int max;
	private int throwsCount;
	private int lastNumber;
	private PersistencePlayer lastThrower;

	public int getMin() {
		return min;
	}

	public void setMin(int min) {
		this.min = min;
	}

	public int getMax() {
		return max;
	}

	public void setMax(int max) {
		this.max = max;
	}

	public int getThrowsCount() {
		return throwsCount;
	}

	public void setThrowsCount(int throwsCount) {
		this.throwsCount = throwsCount;
	}

	public int getLastNumber() {
		return lastNumber;
	}

	public void setLastNumber(int lastNumber) {
		this.lastNumber = lastNumber;
	}

	public PersistencePlayer getLastThrower() {
		return lastThrower;
	}

	public void setLastThrower(PersistencePlayer lastThrower) {
		this.lastThrower = lastThrower;
	}

}
