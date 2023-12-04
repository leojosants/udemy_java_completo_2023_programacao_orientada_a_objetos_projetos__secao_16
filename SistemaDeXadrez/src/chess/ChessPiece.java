/* -------------------- packages section -------------------- */
package chess;

/* -------------------- imports section -------------------- */
import boardgame.Board;
import boardgame.Piece;
import boardgame.Position;

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

	/* -------------------- methods section -------------------- */
	protected boolean isThereOpponentPiece(Position position) {
		ChessPiece p = (ChessPiece) this.getBoard().piece(position);
		return ((p != null) && (p.getColor() != this.getColor()));
	}
}
