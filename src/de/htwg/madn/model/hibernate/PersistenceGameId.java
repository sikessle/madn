package de.htwg.madn.model.hibernate;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "madn3_gameid")
public class PersistenceGameId implements Serializable {

	private static final long serialVersionUID = 9158941452194488213L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int dbId;

	private int id;
	private String comment;

	public int getId() {
		return id;
	}

	public int getDbId() {
		return dbId;
	}

	public void setDbId(int dbId) {
		this.dbId = dbId;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

}
