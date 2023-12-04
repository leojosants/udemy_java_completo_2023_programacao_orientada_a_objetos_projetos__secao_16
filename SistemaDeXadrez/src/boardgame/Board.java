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
		if((rows < 1) || (columns < 1)) {
			throw new BoardException("Error creating board: there must be at least 1 row and 1 collumn");
		}
		this.setRows(rows);
		this.setColumns(columns);
		this.pieces = new Piece[rows][columns];
	}

	public int getRows() {
		return this.rows;
	}

	private void setRows(int rows) {
		this.rows = rows;
	}

	public int getColumns() {
		return this.columns;
	}

	private void setColumns(int columns) {
		this.columns = columns;
	}
	
	/*
	 * methods section
	 */
	public Piece piece(int row, int column) {
		if(!this.positionExists(row, column)) {
			throw new BoardException("Position not on the board");
		}
		return this.pieces[row][column];
	}
	
	public Piece piece(Position position) {
		if(!this.positionExists(position)) {
			throw new BoardException("Position not on the board");
		}
		return this.pieces[position.getRow()][position.getColumn()];
	}
	
	public void placePiece(Piece piece, Position position) {
		if(this.theresIsAPiece(position)) {
			throw new BoardException("There is already a piece on position " + position);
		}
		this.pieces[position.getRow()][position.getColumn()] = piece;
		piece.position = position;
	}
	
	private boolean positionExists(int row, int column) {
		return ((row >= 0) && (row < this.getRows()) && (column >= 0) && (column < this.getColumns()));
	}
	
	public boolean positionExists(Position position) {
		return this.positionExists(position.getRow(), position.getColumn());
	}
	
	public boolean theresIsAPiece(Position position) {
		if(!this.positionExists(position)) {
			throw new BoardException("Position not on the board");
		}
		return this.piece(position) != null;
	}
}
