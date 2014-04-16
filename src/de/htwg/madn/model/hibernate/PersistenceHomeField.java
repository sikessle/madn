package de.htwg.madn.model.hibernate;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import de.htwg.madn.model.Figure;
import de.htwg.madn.model.Player;

@Entity
public final class PersistenceHomeField implements Serializable {

	private static final long serialVersionUID = 7359796367214147054L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int dbId;

	private Player owner;
	private Figure[] fields;
	private int exitIndex;

	public Player getOwner() {
		return owner;
	}

	public void setOwner(Player owner) {
		this.owner = owner;
	}

	public Figure[] getFields() {
		return fields;
	}

	public void setFields(Figure[] fields) {
		this.fields = fields;
	}

	public int getExitIndex() {
		return exitIndex;
	}

	public void setExitIndex(int exitIndex) {
		this.exitIndex = exitIndex;
	}

}
