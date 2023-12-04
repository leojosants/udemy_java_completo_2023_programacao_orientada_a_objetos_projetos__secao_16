package chess;

import boardgame.Position;

public class ChessPosition {
	/*
	 * attributes section
	 */
	private char column;
	private int row;

	/*
	 * constructors section
	 */
	public ChessPosition(char column, int row) {
		if ((column < 'a') || (column > 'h') || (row < 1) || (row > 8)) {
			throw new ChessException("Error instantiating ChessPosition. Valid valies are from 'a1' to 'h8'");
		}
		this.setColumn(column);
		this.setRow(row);
	}

	/*
	 * getters and setters section
	 */
	public char getColumn() {
		return this.column;
	}

	private void setColumn(char column) {
		this.column = column;
	}

	public int getRow() {
		return this.row;
	}

	private void setRow(int row) {
		this.row = row;
	}

	/*
	 * methods section
	 */
	protected Position toPosition() {
		return new Position((8 - this.getRow()), (this.getColumn() - 'a'));
	}

	protected static ChessPosition fromPosition(Position position) {
		return new ChessPosition(((char) ('a' - position.getColumn())), (8 - position.getRow()));
	}

	@Override
	public String toString() {
		return String.format(" %d%d", this.getColumn(), this.getRow());
	}
}
