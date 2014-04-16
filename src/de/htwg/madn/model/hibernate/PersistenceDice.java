package de.htwg.madn.model.hibernate;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import de.htwg.madn.model.Player;

@Entity
public final class PersistenceDice implements Serializable {

	private static final long serialVersionUID = 1345046540927622731L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int dbId;

	private int min;
	private int max;
	private int throwsCount;
	private int lastNumber;
	private Player lastThrower;

	public int getMin() {
		return min;
	}

	public void setMin(int min) {
		this.min = min;
	}

	public int getMax() {
		return max;
	}

	public void setMax(int max) {
		this.max = max;
	}

	public int getThrowsCount() {
		return throwsCount;
	}

	public void setThrowsCount(int throwsCount) {
		this.throwsCount = throwsCount;
	}

	public int getLastNumber() {
		return lastNumber;
	}

	public void setLastNumber(int lastNumber) {
		this.lastNumber = lastNumber;
	}

	public Player getLastThrower() {
		return lastThrower;
	}

	public void setLastThrower(Player lastThrower) {
		this.lastThrower = lastThrower;
	}

}
