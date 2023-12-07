/* -------------------- packages section -------------------- */
package boardgame;

/* -------------------- Piece class -------------------- */
public abstract class Piece {
	
	/* -------------------- attributes section -------------------- */
	protected Position position;
	private Board board;

	/* -------------------- constructors section -------------------- */
	public Piece(Board board) {
		this.board = board;
		position = null;
	}

	/* -------------------- getters and setters section -------------------- */
	protected Board getBoard() {
		return board;
	}

	/* -------------------- methods section -------------------- */
	public abstract boolean[][] possibleMoves();

	public boolean possibleMove(Position position) {
		return possibleMoves()[position.getRow()][position.getColumn()];
	}

	public boolean isThereAnyPossibleMove() {
		boolean[][] matrix = possibleMoves();
		
		for (int i = 0; i < matrix.length; i++) {
			for (int j = 0; j < matrix.length; j++) {
				if (matrix[i][j]) {
					return true;
				}
			}
		}
		return false;
	}
}
