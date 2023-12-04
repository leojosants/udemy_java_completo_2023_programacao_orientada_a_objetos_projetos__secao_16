/* -------------------- packages section -------------------- */
package chess;

/* -------------------- imports section -------------------- */
import boardgame.Board;
import boardgame.Piece;

public abstract class ChessPiece extends Piece {
	/* -------------------- attributes section -------------------- */
	private Color color;

	/* -------------------- constructors section -------------------- */
	public ChessPiece(Board board, Color color) {
		super(board);
		this.setColor(color);
	}

	public Color getColor() {
		return this.color;
	}

	private void setColor(Color color) {
		this.color = color;
	}
}
