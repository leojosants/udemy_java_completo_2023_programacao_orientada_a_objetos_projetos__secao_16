/* -------------------- packages section -------------------- */
package chess.pieces;

/* -------------------- imports section -------------------- */
import boardgame.Board;
import boardgame.Position;
import chess.ChessPiece;
import chess.Color;

/* -------------------- Knight class -------------------- */
public class Knight extends ChessPiece {

	/* -------------------- constructors section -------------------- */
	public Knight(Board board, Color color) {
		super(board, color);
	}

	/* -------------------- methods section -------------------- */
	@Override
	public String toString() {
		return "N";
	}

	private boolean canMove(Position position) {
		ChessPiece piece = (ChessPiece) getBoard().piece(position);
		return piece == null || piece.getColor() != getColor();
	}

	@Override
	public boolean[][] possibleMoves() {
		boolean[][] matrix = new boolean[getBoard().getRows()][getBoard().getColumns()];

		Position position = new Position(0, 0);

		position.setValues(this.position.getRow() - 1, this.position.getColumn() - 2);
		if (getBoard().positionExists(position) && canMove(position)) {
			matrix[position.getRow()][position.getColumn()] = true;
		}

		position.setValues(this.position.getRow() - 2, this.position.getColumn() - 1);
		if (getBoard().positionExists(position) && canMove(position)) {
			matrix[position.getRow()][position.getColumn()] = true;
		}

		position.setValues(this.position.getRow() - 2, this.position.getColumn() + 1);
		if (getBoard().positionExists(position) && canMove(position)) {
			matrix[position.getRow()][position.getColumn()] = true;
		}

		position.setValues(this.position.getRow() - 1, this.position.getColumn() + 2);
		if (getBoard().positionExists(position) && canMove(position)) {
			matrix[position.getRow()][position.getColumn()] = true;
		}

		position.setValues(this.position.getRow() + 1, this.position.getColumn() + 2);
		if (getBoard().positionExists(position) && canMove(position)) {
			matrix[position.getRow()][position.getColumn()] = true;
		}

		position.setValues(this.position.getRow() + 2, this.position.getColumn() + 1);
		if (getBoard().positionExists(position) && canMove(position)) {
			matrix[position.getRow()][position.getColumn()] = true;
		}
		
		position.setValues(this.position.getRow() + 2, this.position.getColumn() - 1);
		if (getBoard().positionExists(position) && canMove(position)) {
			matrix[position.getRow()][position.getColumn()] = true;
		}

		position.setValues(this.position.getRow() + 1, this.position.getColumn() - 2);
		if (getBoard().positionExists(position) && canMove(position)) {
			matrix[position.getRow()][position.getColumn()] = true;
		}

		return matrix;
	}
}
