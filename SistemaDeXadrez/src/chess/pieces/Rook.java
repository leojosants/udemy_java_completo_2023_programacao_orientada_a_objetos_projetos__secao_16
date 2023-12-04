/* -------------------- packages section -------------------- */
package chess.pieces;

/* -------------------- imports section -------------------- */
import boardgame.Board;
import boardgame.Position;
import chess.ChessPiece;
import chess.Color;

public class Rook extends ChessPiece {

	/* -------------------- constructors section -------------------- */
	public Rook(Board board, Color color) {
		super(board, color);
	}

	/* -------------------- methods section -------------------- */
	@Override
	public String toString() {
		return "R";
	}

	@Override
	public boolean[][] possibleMoves() {
		boolean[][] matriz = new boolean[this.getBoard().getRows()][this.getBoard().getColumns()];

		Position p = new Position(0, 0);

		/*
		 * above
		 */
		p.setValues((this.position.getRow() - 1), position.getColumn());

		while ((this.getBoard().positionExists(p)) && (!this.getBoard().theresIsAPiece(p))) {
			matriz[p.getRow()][p.getColumn()] = true;
			p.setRow(p.getRow() - 1);
		}

		if ((this.getBoard().positionExists(p)) && (this.isThereOpponentPiece(p))) {
			matriz[p.getRow()][p.getColumn()] = true;
		}

		/*
		 * left
		 */
		p.setValues(this.position.getRow(), (position.getColumn() - 1));

		while ((this.getBoard().positionExists(p)) && (!this.getBoard().theresIsAPiece(p))) {
			matriz[p.getRow()][p.getColumn()] = true;
			p.setColumn(p.getColumn() - 1);
		}

		if ((this.getBoard().positionExists(p)) && (this.isThereOpponentPiece(p))) {
			matriz[p.getRow()][p.getColumn()] = true;
		}

		/*
		 * right
		 */
		p.setValues(this.position.getRow(), (position.getColumn() + 1));

		while ((this.getBoard().positionExists(p)) && (!this.getBoard().theresIsAPiece(p))) {
			matriz[p.getRow()][p.getColumn()] = true;
			p.setColumn(p.getColumn() + 1);
		}

		if ((this.getBoard().positionExists(p)) && (this.isThereOpponentPiece(p))) {
			matriz[p.getRow()][p.getColumn()] = true;
		}

		/*
		 * below
		 */
		p.setValues((this.position.getRow() + 1), position.getColumn());

		while ((this.getBoard().positionExists(p)) && (!this.getBoard().theresIsAPiece(p))) {
			matriz[p.getRow()][p.getColumn()] = true;
			p.setRow(p.getRow() + 1);
		}

		if ((this.getBoard().positionExists(p)) && (this.isThereOpponentPiece(p))) {
			matriz[p.getRow()][p.getColumn()] = true;
		}

		return matriz;
	}
}
