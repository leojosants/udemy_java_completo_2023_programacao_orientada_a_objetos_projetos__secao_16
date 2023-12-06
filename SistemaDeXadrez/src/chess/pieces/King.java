/* -------------------- packages section -------------------- */
package chess.pieces;

/* -------------------- imports section -------------------- */
import boardgame.Board;
import boardgame.Position;
import chess.ChessMatch;
import chess.ChessPiece;
import chess.Color;

/* -------------------- King class -------------------- */
public class King extends ChessPiece {

	/* -------------------- attributes section -------------------- */
	private ChessMatch chessMatch;

	/* -------------------- constructors section -------------------- */
	public King(Board board, Color color, ChessMatch chessMatch) {
		super(board, color);
		this.chessMatch = chessMatch;
	}

	/* -------------------- methods section -------------------- */
	@Override
	public String toString() {
		return "K";
	}

	private boolean canMove(Position position) {
		ChessPiece p = (ChessPiece) getBoard().piece(position);
		return p == null || p.getColor() != getColor();
	}

	private boolean testRockCastling(Position position) {
		ChessPiece p = (ChessPiece) getBoard().piece(position);
		return p != null && p instanceof Rook && p.getColor() == getColor() && p.getMoveCount() == 0;
	}

	@Override
	public boolean[][] possibleMoves() {
		boolean[][] mat = new boolean[getBoard().getRows()][getBoard().getColumns()];

		Position p = new Position(0, 0);

		// above
		p.setValues(position.getRow() - 1, position.getColumn());
		if (getBoard().positionExists(p) && canMove(p)) {
			mat[p.getRow()][p.getColumn()] = true;
		}

		// below
		p.setValues(position.getRow() + 1, position.getColumn());
		if (getBoard().positionExists(p) && canMove(p)) {
			mat[p.getRow()][p.getColumn()] = true;
		}

		// left
		p.setValues(position.getRow(), position.getColumn() - 1);
		if (getBoard().positionExists(p) && canMove(p)) {
			mat[p.getRow()][p.getColumn()] = true;
		}

		// right
		p.setValues(position.getRow(), position.getColumn() + 1);
		if (getBoard().positionExists(p) && canMove(p)) {
			mat[p.getRow()][p.getColumn()] = true;
		}

		// nw
		p.setValues(position.getRow() - 1, position.getColumn() - 1);
		if (getBoard().positionExists(p) && canMove(p)) {
			mat[p.getRow()][p.getColumn()] = true;
		}

		// ne
		p.setValues(position.getRow() - 1, position.getColumn() + 1);
		if (getBoard().positionExists(p) && canMove(p)) {
			mat[p.getRow()][p.getColumn()] = true;
		}

		// sw
		p.setValues(position.getRow() + 1, position.getColumn() - 1);
		if (getBoard().positionExists(p) && canMove(p)) {
			mat[p.getRow()][p.getColumn()] = true;
		}

		// se
		p.setValues(position.getRow() + 1, position.getColumn() + 1);
		if (getBoard().positionExists(p) && canMove(p)) {
			mat[p.getRow()][p.getColumn()] = true;
		}

		/* special move castling */
		if (getMoveCount() == 0 && !chessMatch.getCheck()) {

			/* special move castling king side rook */
			Position posT_1 = new Position(position.getRow(), position.getColumn() + 3);
			if (testRockCastling(posT_1)) {
				Position p_1 = new Position(position.getRow(), position.getColumn() + 1);
				Position p_2 = new Position(position.getRow(), position.getColumn() + 2);

				if (getBoard().piece(p_1) == null && getBoard().piece(p_2) == null) {
					mat[position.getRow()][position.getColumn() + 2] = true;
				}
			}

			/* special move castling queen side rook */
			Position posT_2 = new Position(position.getRow(), position.getColumn() + 4);
			if (testRockCastling(posT_2)) {
				Position p_1 = new Position(position.getRow(), position.getColumn() - 1);
				Position p_2 = new Position(position.getRow(), position.getColumn() - 2);
				Position p_3 = new Position(position.getRow(), position.getColumn() - 3);

				if (getBoard().piece(p_1) == null && getBoard().piece(p_2) == null && getBoard().piece(p_3) == null) {
					mat[position.getRow()][position.getColumn() - 2] = true;
				}
			}
		}

		return mat;
	}
}
