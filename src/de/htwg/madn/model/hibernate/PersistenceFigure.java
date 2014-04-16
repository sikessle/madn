package de.htwg.madn.model.hibernate;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "madn3_figure")
public final class PersistenceFigure implements Serializable {

	private static final long serialVersionUID = 6788871770979260748L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int dbId;

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
