package de.htwg.madn.model.hibernate;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "madn3_publicfield")
public final class PersistencePublicField implements Serializable {

	private static final long serialVersionUID = 6022183798764317693L;
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int dbId;
	private PersistenceFigure[] fields;

	public int getDbId() {
		return dbId;
	}

	public void setDbId(int dbId) {
		this.dbId = dbId;
	}

	public PersistenceFigure[] getFields() {
		return fields;
	}

	public void setFields(PersistenceFigure[] fields) {
		this.fields = fields;
	}

}
