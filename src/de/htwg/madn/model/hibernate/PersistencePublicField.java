package de.htwg.madn.model.hibernate;

import java.io.Serializable;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

public final class PersistencePublicField implements Serializable {

	private static final long serialVersionUID = 6022183798764317693L;
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int dbId;
	private PersistenceFigure[] fields;

	public PersistenceFigure[] getFields() {
		return fields;
	}

	public void setFields(PersistenceFigure[] fields) {
		this.fields = fields;
	}

}
