package de.htwg.madn.model.couchdb;

import java.awt.Color;
import java.util.List;

import org.ektorp.support.CouchDbDocument;
import org.ektorp.support.TypeDiscriminator;

public final class PersistencePlayer extends CouchDbDocument {

	/**
	 * 
	 */
	private static final long serialVersionUID = 7941692372998412903L;

	/**
	 * @TypeDiscriminator is used to mark properties that makes this class's
	 *                    documents unique in the database.
	 */
	@TypeDiscriminator
	public String id;
	
	private int playerId;
	private Color color;
	private String name;
	private List<PersistenceFigure> figures;
	private PersistenceFinishField finishField;
	private PersistenceHomeField homeField;
	private boolean isHuman;

	public int getPlayerId() {
		return playerId;
	}

	public void setPlayerId(int id) {
		this.playerId = id;
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
