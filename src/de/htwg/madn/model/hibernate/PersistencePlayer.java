package de.htwg.madn.model.hibernate;

import java.awt.Color;
import java.io.Serializable;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public final class PersistencePlayer implements Serializable {

	private static final long serialVersionUID = -734474890707608653L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int dbId;

	private int id;
	private Color color;
	private String name;
	private List<PersistenceFigure> figures;
	private PersistenceFinishField finishField;
	private PersistenceHomeField homeField;
	private boolean isHuman;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Color getColor() {
		return color;
	}

	public void setColor(Color color) {
		this.color = color;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<PersistenceFigure> getFigures() {
		return figures;
	}

	public void setFigures(List<PersistenceFigure> figures) {
		this.figures = figures;
	}

	public PersistenceFinishField getFinishField() {
		return finishField;
	}

	public void setFinishField(PersistenceFinishField finishField) {
		this.finishField = finishField;
	}

	public PersistenceHomeField getHomeField() {
		return homeField;
	}

	public void setHomeField(PersistenceHomeField homeField) {
		this.homeField = homeField;
	}

	public boolean isHuman() {
		return isHuman;
	}

	public void setHuman(boolean isHuman) {
		this.isHuman = isHuman;
	}

}
