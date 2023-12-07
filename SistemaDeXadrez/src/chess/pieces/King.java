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
	private ChessMatch chess_match;

	/* -------------------- constructors section -------------------- */
	public King(Board board, Color color, ChessMatch chess_match) {
		super(board, color);
		this.chess_match = chess_match;
	}

	/* -------------------- methods section -------------------- */
	@Override
	public String toString() {
		return "K";
	}

	private boolean canMove(Position position) {
		ChessPiece piece = (ChessPiece) getBoard().piece(position);
		return piece == null || piece.getColor() != getColor();
	}

	private boolean testRockCastling(Position position) {
		ChessPiece piece = (ChessPiece) getBoard().piece(position);
		return piece != null && piece instanceof Rook && piece.getColor() == getColor() && piece.getMoveCount() == 0;
	}

	@Override
	public boolean[][] possibleMoves() {
		boolean[][] matrix = new boolean[getBoard().getRows()][getBoard().getColumns()];

		Position position = new Position(0, 0);

		// above
		position.setValues(this.position.getRow() - 1, this.position.getColumn());
		if (getBoard().positionExists(position) && canMove(position)) {
			matrix[position.getRow()][position.getColumn()] = true;
		}

		// below
		position.setValues(this.position.getRow() + 1, this.position.getColumn());
		if (getBoard().positionExists(position) && canMove(position)) {
			matrix[position.getRow()][position.getColumn()] = true;
		}

		// left
		position.setValues(this.position.getRow(), this.position.getColumn() - 1);
		if (getBoard().positionExists(position) && canMove(position)) {
			matrix[position.getRow()][position.getColumn()] = true;
		}

		// right
		position.setValues(this.position.getRow(), this.position.getColumn() + 1);
		if (getBoard().positionExists(position) && canMove(position)) {
			matrix[position.getRow()][position.getColumn()] = true;
		}

		// nw
		position.setValues(this.position.getRow() - 1, this.position.getColumn() - 1);
		if (getBoard().positionExists(position) && canMove(position)) {
			matrix[position.getRow()][position.getColumn()] = true;
		}

		// ne
		position.setValues(this.position.getRow() - 1, this.position.getColumn() + 1);
		if (getBoard().positionExists(position) && canMove(position)) {
			matrix[position.getRow()][position.getColumn()] = true;
		}

		// sw
		position.setValues(this.position.getRow() + 1, this.position.getColumn() - 1);
		if (getBoard().positionExists(position) && canMove(position)) {
			matrix[position.getRow()][position.getColumn()] = true;
		}

		// se
		position.setValues(this.position.getRow() + 1, this.position.getColumn() + 1);
		if (getBoard().positionExists(position) && canMove(position)) {
			matrix[position.getRow()][position.getColumn()] = true;
		}

		/* special move castling */
		if (getMoveCount() == 0 && !chess_match.getCheck()) {

			/* special move castling king side rook */
			Position position_rook_1 = new Position(this.position.getRow(), this.position.getColumn() + 3);
			if (testRockCastling(position_rook_1)) {
				Position position_1 = new Position(this.position.getRow(), this.position.getColumn() + 1);
				Position position_2 = new Position(this.position.getRow(), this.position.getColumn() + 2);

				if (getBoard().piece(position_1) == null && getBoard().piece(position_2) == null) {
					matrix[this.position.getRow()][this.position.getColumn() + 2] = true;
				}
			}

			/* special move castling queen side rook */
			Position position_rook_2 = new Position(this.position.getRow(), this.position.getColumn() - 4);
			if (testRockCastling(position_rook_2)) {
				Position position_1 = new Position(this.position.getRow(), this.position.getColumn() - 1);
				Position position_2 = new Position(this.position.getRow(), this.position.getColumn() - 2);
				Position position_3 = new Position(this.position.getRow(), this.position.getColumn() - 3);

				if (getBoard().piece(position_1) == null && getBoard().piece(position_2) == null && getBoard().piece(position_3) == null) {
					matrix[this.position.getRow()][this.position.getColumn() - 2] = true;
				}
			}
		}

		return matrix;
	}
}
