/* -------------------- packages section -------------------- */
package boardgame;

/* -------------------- Position class -------------------- */
public class Position {

	/* -------------------- attributes section -------------------- */
	private int row;
	private int column;

	/* -------------------- constructors section -------------------- */
	public Position(int row, int column) {
		this.row = row;
		this.column = column;
	}

	/* -------------------- getters and setters section -------------------- */
	public int getRow() {
		return row;
	}

	public void setRow(int row) {
		this.row = row;
	}

	public int getColumn() {
		return column;
	}

	public void setColumn(int column) {
		this.column = column;
	}

	/* -------------------- methods section -------------------- */
	public void setValues(int row, int column) {
		this.row = row;
		this.column = column;
	}

	@Override
	public String toString() {
		return row + ", " + column;
	}
}
