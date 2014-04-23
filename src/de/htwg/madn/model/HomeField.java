package de.htwg.madn.model;

public final class HomeField extends AbstractSpecialField {
	private int exitIndex;

	public HomeField(final int exitIndex, final int fieldsCount) {
		super(fieldsCount);
		this.exitIndex = exitIndex;
	}

	@Override
	public int getEntryIndex() {
		throw new UnsupportedOperationException("home field has no entry index");
	}

	@Override
	public int getExitIndex() {
		return exitIndex;
	}

	public void setExitIndex(int exitIndex) {
		this.exitIndex = exitIndex;
	}

}
