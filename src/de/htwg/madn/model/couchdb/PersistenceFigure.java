package de.htwg.madn.model.couchdb;

import org.ektorp.support.CouchDbDocument;
import org.ektorp.support.TypeDiscriminator;

public final class PersistenceFigure extends CouchDbDocument  {

	/**
	 * 
	 */
	private static final long serialVersionUID = -7026260330822474078L;

	/**
	 * @TypeDiscriminator is used to mark properties that makes this class's
	 *                    documents unique in the database.
	 */
	@TypeDiscriminator
	public String id;
	
	private char letter;
	private PersistencePlayer owner;
	private boolean atHomeArea = true;
	private boolean atFinishArea = false;
	private boolean finished = false;
	private int currentFieldIndex = -1;

	public char getLetter() {
		return letter;
	}

	public void setLetter(char letter) {
		this.letter = letter;
	}

	public PersistencePlayer getOwner() {
		return owner;
	}

	public void setOwner(PersistencePlayer owner) {
		this.owner = owner;
	}

	public boolean isAtHomeArea() {
		return atHomeArea;
	}

	public void setAtHomeArea(boolean atHomeArea) {
		this.atHomeArea = atHomeArea;
	}

	public boolean isAtFinishArea() {
		return atFinishArea;
	}

	public void setAtFinishArea(boolean atFinishArea) {
		this.atFinishArea = atFinishArea;
	}

	public boolean isFinished() {
		return finished;
	}

	public void setFinished(boolean finished) {
		this.finished = finished;
	}

	public int getCurrentFieldIndex() {
		return currentFieldIndex;
	}

	public void setCurrentFieldIndex(int currentFieldIndex) {
		this.currentFieldIndex = currentFieldIndex;
	}

}
