package de.htwg.madn.model.hibernate;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "madn3_homefield")
public final class PersistenceHomeField implements Serializable {

	private static final long serialVersionUID = 7359796367214147054L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int dbId;

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

	public int getDbId() {
		return dbId;
	}

	public void setDbId(int dbId) {
		this.dbId = dbId;
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
