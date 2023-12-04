package chess;

import boardgame.BoardException;

public class ChessException extends BoardException {
	/*
	 * attributes section
	 */
	private static final long serialVersionUID = 1L;

	/*
	 * constructors section
	 */
	public ChessException(String message) {
		super(message);
	}
}
