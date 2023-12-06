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
	private int moveCount;

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
		return moveCount;
	}

	/* -------------------- methods section -------------------- */
	public void increaseMoveCount() {
		moveCount++;
	}

	public void decreaseMoveCount() {
		moveCount--;
	}

	public ChessPosition getChessPosition() {
		return ChessPosition.fromPosition(position);
	}

	protected boolean isThereOpponentPiece(Position position) {
		ChessPiece p = (ChessPiece) getBoard().piece(position);
		return p != null && p.getColor() != color;
	}
}
