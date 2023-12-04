/* -------------------- packages section -------------------- */
package chess.pieces;

/* -------------------- imports section -------------------- */
import boardgame.Board;
import boardgame.Position;
import chess.ChessPiece;
import chess.Color;

public class King extends ChessPiece {
	/* -------------------- constructors section -------------------- */
	public King(Board board, Color color) {
		super(board, color);
	}

	/* -------------------- methods section -------------------- */
	@Override
	public String toString() {
		return "K";
	}

	private boolean canMove(Position position) {
		ChessPiece p = (ChessPiece) this.getBoard().piece(position);
		return ((p == null) || (p.getColor() != this.getColor()));
	}

	@Override
	public boolean[][] possibleMoves() {
		boolean[][] matriz = new boolean[this.getBoard().getRows()][this.getBoard().getColumns()];
		Position p = new Position(0, 0);

		// above
		p.setValues((position.getRow() - 1), position.getColumn());
		if ((this.getBoard().positionExists(p)) && this.canMove(p)) {
			matriz[p.getRow()][p.getColumn()] = true;
		}

		// below
		p.setValues((position.getRow() + 1), position.getColumn());
		if ((this.getBoard().positionExists(p)) && this.canMove(p)) {
			matriz[p.getRow()][p.getColumn()] = true;
		}

		// left
		p.setValues(position.getRow(), (position.getColumn() - 1));
		if ((this.getBoard().positionExists(p)) && this.canMove(p)) {
			matriz[p.getRow()][p.getColumn()] = true;
		}

		// right
		p.setValues(position.getRow(), (position.getColumn() + 1));
		if ((this.getBoard().positionExists(p)) && this.canMove(p)) {
			matriz[p.getRow()][p.getColumn()] = true;
		}

		// northwest
		p.setValues((position.getRow() - 1), (position.getColumn() - 1));
		if ((this.getBoard().positionExists(p)) && this.canMove(p)) {
			matriz[p.getRow()][p.getColumn()] = true;
		}

		// northeast
		p.setValues((position.getRow() - 1), (position.getColumn() + 1));
		if ((this.getBoard().positionExists(p)) && this.canMove(p)) {
			matriz[p.getRow()][p.getColumn()] = true;
		}

		// south west
		p.setValues((position.getRow() + 1), (position.getColumn() - 1));
		if ((this.getBoard().positionExists(p)) && this.canMove(p)) {
			matriz[p.getRow()][p.getColumn()] = true;
		}

		// south east
		p.setValues((position.getRow() + 1), (position.getColumn() + 1));
		if ((this.getBoard().positionExists(p)) && this.canMove(p)) {
			matriz[p.getRow()][p.getColumn()] = true;
		}

		return matriz;
	}
}
