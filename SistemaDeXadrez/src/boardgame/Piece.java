/* -------------------- packages section -------------------- */
package boardgame;

public abstract class Piece {
	/* -------------------- attributes section -------------------- */
	protected Position position;
	private Board board;

	/* -------------------- constructors section -------------------- */
	public Piece(Board board) {
		this.setBoard(board);
		this.position = null;
	}

	protected Board getBoard() {
		return board;
	}

	private void setBoard(Board board) {
		this.board = board;
	}

	/* -------------------- methods section -------------------- */
	public abstract boolean[][] possibleMoves();

	public boolean possibleMove(Position position) {
		return this.possibleMoves()[position.getRow()][position.getColumn()];
	}
	
	public boolean isThereAnyPossibleMove() {
		boolean[][] matriz = possibleMoves();
		for (int i = 0; i < matriz.length; i++) {
			for (int j = 0; j < matriz.length; j++) {
				if(matriz[i][j]) {
					return true;
				}
			}
		}
		return false;
	}
}
