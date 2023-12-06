/* -------------------- packages section -------------------- */
package boardgame;

/* -------------------- BoardException class -------------------- */
public class BoardException extends RuntimeException {
	
	/* -------------------- attributes section -------------------- */
	private static final long serialVersionUID = 1L;

	/* -------------------- constructors section -------------------- */
	public BoardException(String message) {
		super(message);
	}
}
