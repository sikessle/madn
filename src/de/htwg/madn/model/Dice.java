package de.htwg.madn.model;

import java.util.Random;

public final class Dice {

	private int min;
	private int max;
	private int throwsCount;
	private int lastNumber;
	private Player lastThrower;

	public Dice(final int min, final int max) {
		this.min = min;
		this.max = max;
		resetThrowsCount();
	}

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

	public void setLastNumber(int lastNumber) {
		this.lastNumber = lastNumber;
	}

	public void setLastThrower(Player lastThrower) {
		this.lastThrower = lastThrower;
	}

	public int rollDice(Player thrower) {
		this.lastThrower = thrower;
		this.throwsCount++;
		Random rand = new Random();
		lastNumber = min + Math.abs(rand.nextInt()) % (max - min + 1);
		return lastNumber;
	}

	public int getLastNumber() {
		if (this.throwsCount == 0) {
			throw new IllegalStateException("dice has not been thrown before!");
		}
		return this.lastNumber;
	}

	public Player getLastThrower() {
		return lastThrower;
	}

	public int getThrowsCount() {
		return throwsCount;
	}

	public void setThrowsCount(int throwsCount) {
		this.throwsCount = throwsCount;
	}

	public void resetThrowsCount() {
		this.throwsCount = 0;
	}

}
