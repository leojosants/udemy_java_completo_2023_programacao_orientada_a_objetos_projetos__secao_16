package chess.pieces;

import boardgame.Board;
import chess.ChessPiece;
import chess.Color;

public class King extends ChessPiece {
	/*
	 * constructors section
	 */
	public King(Board board, Color color) {
		super(board, color);
	}

	/*
	 * methods section
	 */
	@Override
	public String toString() {
		return "K";
	}
}
