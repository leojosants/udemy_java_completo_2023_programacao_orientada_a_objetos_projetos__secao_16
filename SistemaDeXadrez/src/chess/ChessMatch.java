package chess;

import boardgame.Board;

public class ChessMatch {
	/*
	 * attributes section
	 */
	private Board board;
	
	/*
	 * constructors section
	 */
	public ChessMatch() {
		this.board = new Board(8, 8);
	}
	
	/*
	 * methods section
	 */
	public ChessPiece[][] getPieces() {
		ChessPiece[][] matriz = new ChessPiece[this.board.getRows()][this.board.getColumns()];
		for (int i = 0; i < this.board.getRows(); i++) {
			for (int j = 0; j < this.board.getColumns(); j++) {
				matriz[i][j] = (ChessPiece) this.board.piece(i, j);
			}
		}
		return matriz;
	}
}
