package de.htwg.madn.model.hibernate;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "madn3_finishfield")
public class PersistenceFinishField implements Serializable {

	private static final long serialVersionUID = 3010779448296652902L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int dbId;

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
