package de.htwg.madn.model;

public class GameId implements Comparable<GameId> {

	private final int id;
	private String comment;

	public GameId(int id) {
		this.id = id;
		comment = "";
	}

	public int getId() {
		return id;
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		if (comment == null) {
			throw new NullPointerException(
					"must be non-null. use empty string instead.");
		}
		this.comment = comment;
	}

	@Override
	public int compareTo(GameId other) {
		if (id < other.id) {
			return -1;
		}
		if (other.id < id) {
			return 1;
		}

		return 0;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + id;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null) {
			return false;
		}
		if (getClass() != obj.getClass()) {
			return false;
		}
		GameId other = (GameId) obj;
		return id == other.id;
	}

	@Override
	public String toString() {
		String str = String.valueOf(id);
		if (!comment.isEmpty()) {
			str += " (" + comment + ")";
		}
		return str;
	}

}
