package boardgame;

public class Piece {
	/*
	 * attributes section
	 */
	protected Position position;
	private Board board;

	/*
	 * constructors section
	 */
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
}
