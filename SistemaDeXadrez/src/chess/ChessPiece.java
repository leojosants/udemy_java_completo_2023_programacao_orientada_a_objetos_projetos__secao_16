/* -------------------- packages section -------------------- */
package chess;

/* -------------------- imports section -------------------- */
import boardgame.Board;
import boardgame.Piece;
import boardgame.Position;

/* -------------------- ChessPiece class -------------------- */
public abstract class ChessPiece extends Piece {

	/* -------------------- attributes section -------------------- */
	private Color color;
	private int move_count;

	/* -------------------- constructors section -------------------- */
	public ChessPiece(Board board, Color color) {
		super(board);
		this.color = color;
	}

	/* -------------------- getters and setters section -------------------- */
	public Color getColor() {
		return color;
	}

	public int getMoveCount() {
		return move_count;
	}

	/* -------------------- methods section -------------------- */
	public void increaseMoveCount() {
		move_count++;
	}

	public void decreaseMoveCount() {
		move_count--;
	}

	public ChessPosition getChessPosition() {
		return ChessPosition.fromPosition(position);
	}

	protected boolean isThereOpponentPiece(Position position) {
		ChessPiece p = (ChessPiece) getBoard().piece(position);
		return p != null && p.getColor() != color;
	}
}
