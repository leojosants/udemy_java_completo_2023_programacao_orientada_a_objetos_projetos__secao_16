package boardgame;

public class Board {
	/*
	 * attributes section
	 */
	private int rows;
	private int columns;
	private Piece[][] pieces;
	
	/*
	 * constructors section
	 */
	public Board(int rows, int columns) {
		this.setRows(rows);
		this.setColumns(columns);
		this.pieces = new Piece[rows][columns];
	}

	public int getRows() {
		return this.rows;
	}

	public void setRows(int rows) {
		this.rows = rows;
	}

	public int getColumns() {
		return this.columns;
	}

	public void setColumns(int columns) {
		this.columns = columns;
	}
	
	/*
	 * methods section
	 */
	public Piece piece(int row, int column) {
		return this.pieces[row][column];
	}
	
	public Piece piece(Position position) {
		return this.pieces[position.getRow()][position.getColumn()];
	}
}
