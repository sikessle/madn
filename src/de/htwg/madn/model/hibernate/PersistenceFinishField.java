package de.htwg.madn.model.hibernate;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import de.htwg.madn.model.Figure;
import de.htwg.madn.model.Player;

@Entity
public class PersistenceFinishField implements Serializable {

	private static final long serialVersionUID = 3010779448296652902L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int dbId;

	private Player owner;
	private Figure[] fields;
	private int entryIndex;

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

	public int getEntryIndex() {
		return entryIndex;
	}

	public void setEntryIndex(int entryIndex) {
		this.entryIndex = entryIndex;
	}

}
