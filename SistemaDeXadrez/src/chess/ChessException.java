/* -------------------- packages section -------------------- */
package chess;

/* -------------------- imports section -------------------- */
import boardgame.BoardException;

/* -------------------- ChessException class -------------------- */
public class ChessException extends BoardException {

	/* -------------------- attributes section -------------------- */
	private static final long serialVersionUID = 1L;

	/* -------------------- constructors section -------------------- */
	public ChessException(String message) {
		super(message);
	}
}
