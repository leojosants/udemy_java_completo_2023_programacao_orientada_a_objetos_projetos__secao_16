/* -------------------- packages section -------------------- */
package chess;

/* -------------------- imports section -------------------- */
import boardgame.Board;
import boardgame.Piece;
import boardgame.Position;
import chess.pieces.King;
import chess.pieces.Rook;

public class ChessMatch {
	/* -------------------- attributes section -------------------- */
	private Board board;

	/* -------------------- constructors section -------------------- */
	public ChessMatch() {
		this.board = new Board(8, 8);
		this.initialSetup();
	}

	/* -------------------- methods section -------------------- */
	public ChessPiece[][] getPieces() {
		ChessPiece[][] matriz = new ChessPiece[this.board.getRows()][this.board.getColumns()];
		for (int i = 0; i < this.board.getRows(); i++) {
			for (int j = 0; j < this.board.getColumns(); j++) {
				matriz[i][j] = (ChessPiece) this.board.piece(i, j);
			}
		}
		return matriz;
	}

	public ChessPiece performChessMovie(ChessPosition sourcePosition, ChessPosition targetPosition) {
		Position source = sourcePosition.toPosition();
		Position target = targetPosition.toPosition();
		validateSourcePosition(source);
		Piece captured_piece = makeMove(source, target);
		return (ChessPiece) captured_piece;
	}
	
	private Piece makeMove(Position source,Position target){
		Piece p = this.board.removePiece(source);
		Piece captured_piece = this.board.removePiece(target);
		this.board.placePiece(p, target);
		return captured_piece;
	}

	private void validateSourcePosition(Position position) {
		if (!this.board.theresIsAPiece(position)) {
			throw new ChessException("There is no piece on source position");
		}
		
		if(!this.board.piece(position).isThereAnyPossibleMove()) {
			throw new ChessException("There is no possible moves for the chosen piece");
		}
	}

	private void placeNewPiece(char column, int row, ChessPiece piece) {
		this.board.placePiece(piece, new ChessPosition(column, row).toPosition());
	}

	private void initialSetup() {
		placeNewPiece('c', 1, new Rook(board, Color.WHITE));
		placeNewPiece('c', 2, new Rook(board, Color.WHITE));
		placeNewPiece('d', 2, new Rook(board, Color.WHITE));
		placeNewPiece('e', 2, new Rook(board, Color.WHITE));
		placeNewPiece('e', 1, new Rook(board, Color.WHITE));
		placeNewPiece('d', 1, new King(board, Color.WHITE));

		placeNewPiece('c', 7, new Rook(board, Color.BLACK));
		placeNewPiece('c', 8, new Rook(board, Color.BLACK));
		placeNewPiece('d', 7, new Rook(board, Color.BLACK));
		placeNewPiece('e', 7, new Rook(board, Color.BLACK));
		placeNewPiece('e', 8, new Rook(board, Color.BLACK));
		placeNewPiece('d', 8, new King(board, Color.BLACK));
	}
}
