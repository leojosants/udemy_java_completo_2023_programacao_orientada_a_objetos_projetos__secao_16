/* -------------------- packages section -------------------- */
package chess;

/* -------------------- imports section -------------------- */
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import boardgame.Board;
import boardgame.Piece;
import boardgame.Position;
import chess.pieces.King;
import chess.pieces.Rook;

public class ChessMatch {
	/* -------------------- attributes section -------------------- */
	private int turn;
	private Color current_playyer;
	private Board board;
	private boolean check;
	private List<Piece> pieces_on_the_board = new ArrayList<>();
	private List<Piece> captured_pieces = new ArrayList<>();

	/* -------------------- constructors section -------------------- */
	public ChessMatch() {
		this.board = new Board(8, 8);
		this.setTurn(1);
		this.setCurrentPlayer(Color.WHITE);
		this.initialSetup();
	}

	/* -------------------- getters and setters section -------------------- */
	public int getTurn() {
		return this.turn;
	}

	private void setTurn(int turn) {
		this.turn = turn;
	}

	public Color getCurrentPlayer() {
		return this.current_playyer;
	}

	private void setCurrentPlayer(Color current_playyer) {
		this.current_playyer = current_playyer;
	}
	
	public boolean getCheck() {
		return this.check;
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

	public boolean[][] possibleMoves(ChessPosition source_position) {
		Position position = source_position.toPosition();
		this.validateSourcePosition(position);
		return this.board.piece(position).possibleMoves();
	}

	public ChessPiece performChessMovie(ChessPosition source_position, ChessPosition target_position) {
		Position source = source_position.toPosition();
		Position target = target_position.toPosition();

		validateSourcePosition(source);
		validateTargetPosition(source, target);

		Piece captured_piece = makeMove(source, target);

		if (this.testCheck(current_playyer)) {
			this.undoMove(source, target, captured_piece);
			throw new ChessException("You can not put yourself in check");
		}

		this.check = (this.testCheck(this.opponent(current_playyer))) ? true : false;
		this.nextTurn();
		return (ChessPiece) captured_piece;
	}

	private Piece makeMove(Position source, Position target) {
		Piece piece = this.board.removePiece(source);
		Piece captured_piece = this.board.removePiece(target);

		this.board.placePiece(piece, target);

		if (captured_piece != null) {
			pieces_on_the_board.remove(captured_piece);
			captured_pieces.add(captured_piece);
		}

		return captured_piece;
	}

	private void undoMove(Position source, Position target, Piece captured_piece) {
		Piece piece = this.board.removePiece(target);
		this.board.placePiece(piece, source);

		if (captured_piece != null) {
			this.board.placePiece(captured_piece, target);
			captured_pieces.remove(captured_piece);
			pieces_on_the_board.add(captured_piece);
		}
	}

	private void validateSourcePosition(Position position) {
		if (!this.board.theresIsAPiece(position)) {
			throw new ChessException("There is no piece on source position");
		}

		if (this.getCurrentPlayer() != ((ChessPiece) this.board.piece(position)).getColor()) {
			throw new ChessException("The chosen piece is not yours");
		}

		if (!this.board.piece(position).isThereAnyPossibleMove()) {
			throw new ChessException("There is no possible moves for the chosen piece");
		}
	}

	private void validateTargetPosition(Position source, Position target) {
		if (!this.board.piece(source).possibleMove(target)) {
			throw new ChessException("The chosen piece can not move to target position");
		}
	}

	private void nextTurn() {
		this.setTurn(this.getTurn() + 1);
		this.setCurrentPlayer((this.getCurrentPlayer() == Color.WHITE) ? Color.BLACK : Color.WHITE);
	}

	private Color opponent(Color color) {
		return (color == Color.WHITE) ? Color.BLACK : Color.WHITE;
	}

	private ChessPiece king(Color color) {
		List<Piece> list = pieces_on_the_board.stream().filter(x -> ((ChessPiece) x).getColor() == color).collect(Collectors.toList());

		for (Piece piece : list) {
			if (piece instanceof King) {
				return (ChessPiece) piece;
			}
		}

		throw new IllegalStateException("There is no " + color + " king on the board");
	}

	private boolean testCheck(Color color) {
		Position king_position = king(color).getChessPosition().toPosition();
		List<Piece> opponent_pieces = pieces_on_the_board.stream().filter(x -> ((ChessPiece) x).getColor() == this.opponent(color)).collect(Collectors.toList());

		for (Piece piece : opponent_pieces) {
			boolean[][] matriz = piece.possibleMoves();

			if (matriz[king_position.getRow()][king_position.getColumn()]) {
				return true;
			}
		}

		return false;
	}

	private void placeNewPiece(char column, int row, ChessPiece piece) {
		this.board.placePiece(piece, new ChessPosition(column, row).toPosition());
		pieces_on_the_board.add(piece);
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
