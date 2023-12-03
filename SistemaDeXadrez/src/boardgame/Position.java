package boardgame;

public class Position {
	/*
	 * attributes section
	 */
	private int row;
	private int column;

	/*
	 * constructors section
	 */
	public Position(int row, int column) {
		this.setRow(row);
		this.setColumn(column);
	}

	/*
	 * getters and setters section
	 */
	public int getRow() {
		return this.row;
	}

	public void setRow(int row) {
		this.row = row;
	}

	public int getColumn() {
		return this.column;
	}

	public void setColumn(int column) {
		this.column = column;
	}

	@Override
	public String toString() {
		return String.format("%d, %d", this.getRow(), this.getColumn());
	}
}
