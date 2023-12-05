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
	private int movie_count;

	/* -------------------- constructors section -------------------- */
	public ChessPiece(Board board, Color color) {
		super(board);
		this.setColor(color);
	}

	/* -------------------- getters and setters section -------------------- */
	public Color getColor() {
		return this.color;
	}

	private void setColor(Color color) {
		this.color = color;
	}
	
	public int getMovieCount() {
		return this.movie_count;
	}

	private void setMovieCount(int movie_count) {
		this.movie_count = movie_count;
	}

	/* -------------------- methods section -------------------- */
	public void increaseMoveCount() {
//		this.movie_count++;
		this.setMovieCount(this.getMovieCount() + 1);
	}
	
	public void decreaseMoveCount() {
//		this.movie_count--;
		this.setMovieCount(this.getMovieCount() - 1);
	}
	
	public ChessPosition getChessPosition() {
		return ChessPosition.fromPosition(position);
	}
	
	protected boolean isThereOpponentPiece(Position position) {
		ChessPiece p = (ChessPiece) this.getBoard().piece(position);
		return ((p != null) && (p.getColor() != this.getColor()));
	}
}
