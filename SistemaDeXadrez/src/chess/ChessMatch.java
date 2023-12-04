package chess;

import boardgame.Board;
import chess.pieces.King;
import chess.pieces.Rook;

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
		this.initialSetup();
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
	
	private void placeNewPiece(char column, int row, ChessPiece piece) {
		this.board.placePiece(piece, new ChessPosition(column, row).toPosition());
	}
	
	private void initialSetup() {
		placeNewPiece('b', 6, new Rook(this.board, Color.WHITE));
		placeNewPiece('e', 8, new King(this.board, Color.BLACK));
		placeNewPiece('e', 1, new King(this.board, Color.WHITE));
	}
}
