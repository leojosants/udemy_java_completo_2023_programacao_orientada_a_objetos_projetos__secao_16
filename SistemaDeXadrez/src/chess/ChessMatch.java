/* -------------------- packages section -------------------- */
package chess;

/* -------------------- imports section -------------------- */
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import boardgame.Board;
import boardgame.Piece;
import boardgame.Position;
import chess.pieces.Bishop;
import chess.pieces.King;
import chess.pieces.Knight;
import chess.pieces.Pawn;
import chess.pieces.Queen;
import chess.pieces.Rook;

/* -------------------- ChessMatch class -------------------- */
public class ChessMatch {

	/* -------------------- attributes section -------------------- */
	private int turn;
	private Color current_player;
	private Board board;
	private boolean check;
	private boolean check_mate;
	private ChessPiece en_passant_vulnerable;
	private ChessPiece promoted;
	private List<Piece> pieces_on_the_board = new ArrayList<>();
	private List<Piece> captured_pieces = new ArrayList<>();

	/* -------------------- constructors section -------------------- */
	public ChessMatch() {
		board = new Board(8, 8);
		turn = 1;
		current_player = Color.WHITE;
		initialSetup();
	}

	/* -------------------- getters and setters section -------------------- */
	public int getTurn() {
		return turn;
	}

	public Color getCurrentPlayer() {
		return current_player;
	}

	public boolean getCheck() {
		return check;
	}

	public boolean getCheckMate() {
		return check_mate;
	}
	
	public ChessPiece getEnPassanVulnerable() {
		return this.en_passant_vulnerable;
	}
	
	public ChessPiece getPromoted() {
		return promoted;
	}

	/* -------------------- methods section -------------------- */
	public ChessPiece[][] getPieces() {
		ChessPiece[][] matrix = new ChessPiece[board.getRows()][board.getColumns()];
		
		for (int i = 0; i < board.getRows(); i++) {
			for (int j = 0; j < board.getColumns(); j++) {
				matrix[i][j] = (ChessPiece) board.piece(i, j);
			}
		}
		
		return matrix;
	}

	public boolean[][] possibleMoves(ChessPosition source_position) {
		Position position = source_position.toPosition();
		validateSourcePosition(position);
		return board.piece(position).possibleMoves();
	}

	public ChessPiece performChessMove(ChessPosition source_position, ChessPosition target_position) {
		Position source = source_position.toPosition();
		Position target = target_position.toPosition();
		
		validateSourcePosition(source);
		validateTargetPosition(source, target);
		
		Piece captured_piece = makeMove(source, target);

		if (testCheck(current_player)) {
			undoMove(source, target, captured_piece);
			throw new ChessException("You can't put yourself in check");
		}
		
		ChessPiece moved_piece = (ChessPiece) board.piece(target);
		
		/* special move promotion */
		promoted = null;
		
		if (moved_piece instanceof Pawn) {
			if ((moved_piece.getColor() == Color.WHITE && target.getRow() == 0) || (moved_piece.getColor() == Color.BLACK && target.getRow() == 7)) {
				promoted = (ChessPiece) board.piece(target);
				promoted = replacePromotedPiece("Q");
			}
		}

		check = (testCheck(opponent(current_player))) ? true : false;

		if (testCheckMate(opponent(current_player))) {
			check_mate = true;
		} 
		else {
			nextTurn();
		}
		
		/* special move en passant */
		if (moved_piece instanceof Pawn && (target.getRow() == source.getRow() - 2 || target.getRow() == source.getRow() + 2)) {
			en_passant_vulnerable = moved_piece;
		}
		else {
			en_passant_vulnerable = null;
		}

		return (ChessPiece) captured_piece;
	}
	
	public ChessPiece replacePromotedPiece(String type) {
		if (promoted == null) {
			throw new IllegalStateException("There is no piece to be promoted");
		}
		
		if (!type.equals("B") && !type.equals("N") && !type.equals("R") && !type.equals("Q")) {
			return promoted;
		}
		
		Position position = promoted.getChessPosition().toPosition();
		Piece piece = board.removePiece(position);
		pieces_on_the_board.remove(piece);
		
		ChessPiece newPiece = newPiece(type, promoted.getColor());
		board.placePiece(newPiece, position);
		pieces_on_the_board.add(newPiece);
		return newPiece;
	}
	
	private ChessPiece newPiece(String type, Color color) {
		if (type.equals("B")) return new Bishop(board, color);
		if (type.equals("N")) return new Knight(board, color);
		if (type.equals("Q")) return new Queen(board, color);
		return new Rook(board, color);
	}

	private Piece makeMove(Position source, Position target) {
		ChessPiece piece = (ChessPiece) board.removePiece(source);
		piece.increaseMoveCount();
		Piece captured_piece = board.removePiece(target);
		board.placePiece(piece, target);

		if (captured_piece != null) {
			pieces_on_the_board.remove(captured_piece);
			captured_pieces.add(captured_piece);
		}
		
		/* special move castling king side rook */
		if (piece instanceof King && target.getColumn() == source.getColumn() + 2) {
			Position source_rook = new Position(source.getRow(), source.getColumn() + 3);
			Position target_rook = new Position(source.getRow(), source.getColumn() + 1);
			ChessPiece rook = (ChessPiece) board.removePiece(source_rook);
			board.placePiece(rook, target_rook);
			rook.increaseMoveCount();
		}
		
		/* special move castling queen side rook */
		if (piece instanceof King && target.getColumn() == source.getColumn() - 2) {
			Position source_rook = new Position(source.getRow(), source.getColumn() - 4);
			Position target_rook = new Position(source.getRow(), source.getColumn() - 1);
			ChessPiece rook = (ChessPiece) board.removePiece(source_rook);
			board.placePiece(rook, target_rook);
			rook.increaseMoveCount();
		}
		
		/* special move en passant */
		if (piece instanceof Pawn) {
			if (source.getColumn() != target.getColumn() && captured_piece == null) {
				Position pawn_position;
				
				if (piece.getColor() == Color.WHITE) {
					pawn_position = new Position(target.getRow() + 1, target.getColumn());
				}
				else {
					pawn_position = new Position(target.getRow() - 1, target.getColumn());
				}
				
				captured_piece = board.removePiece(pawn_position);
				captured_pieces.add(captured_piece);
				pieces_on_the_board.remove(captured_piece);
			}
		}
		
		return captured_piece;
	}

	private void undoMove(Position source, Position target, Piece captured_piece) {
		ChessPiece piece = (ChessPiece) board.removePiece(target);
		piece.decreaseMoveCount();
		board.placePiece(piece, source);

		if (captured_piece != null) {
			board.placePiece(captured_piece, target);
			captured_pieces.remove(captured_piece);
			pieces_on_the_board.add(captured_piece);
		}
		
		/* special move castling king side rook */
		if (piece instanceof King && target.getColumn() == source.getColumn() + 2) {
			Position source_rook = new Position(source.getRow(), source.getColumn() + 3);
			Position target_rook = new Position(source.getRow(), source.getColumn() + 1);
			ChessPiece rook = (ChessPiece) board.removePiece(target_rook);
			board.placePiece(rook, source_rook);
			rook.decreaseMoveCount();
		}
		
		/* special move castling queen side rook */
		if (piece instanceof King && target.getColumn() == source.getColumn() - 2) {
			Position source_rook = new Position(source.getRow(), source.getColumn() - 4);
			Position target_rook = new Position(source.getRow(), source.getColumn() - 1);
			ChessPiece rook = (ChessPiece) board.removePiece(target_rook);
			board.placePiece(rook, source_rook);
			rook.decreaseMoveCount();
		}
		
		/* special move en passant */
		if (piece instanceof Pawn) {
			if (source.getColumn() != target.getColumn() && captured_piece == en_passant_vulnerable) {
				ChessPiece pawn = (ChessPiece) board.removePiece(target);
				Position pawn_position;
				
				if (piece.getColor() == Color.WHITE) {
					pawn_position = new Position(3, target.getColumn());
				}
				else {
					pawn_position = new Position(4, target.getColumn());
				}
				
				board.placePiece(pawn, pawn_position);
			}
		}
	}

	private void validateSourcePosition(Position position) {
		if (!board.thereIsAPiece(position)) {
			throw new ChessException("There is no piece on source position");
		}
		if (current_player != ((ChessPiece) board.piece(position)).getColor()) {
			throw new ChessException("The chosen piece is not yours");
		}
		if (!board.piece(position).isThereAnyPossibleMove()) {
			throw new ChessException("There is no possible moves for the chosen piece");
		}
	}

	private void validateTargetPosition(Position source, Position target) {
		if (!board.piece(source).possibleMove(target)) {
			throw new ChessException("The chosen piece can't move to target position");
		}
	}

	private void nextTurn() {
		turn++;
		current_player = (current_player == Color.WHITE) ? Color.BLACK : Color.WHITE;
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
		
		List<Piece> opponent_pieces = pieces_on_the_board.stream().filter(x -> ((ChessPiece) x).getColor() == opponent(color)).collect(Collectors.toList());

		for (Piece piece : opponent_pieces) {
			boolean[][] matrix = piece.possibleMoves();
		
			if (matrix[king_position.getRow()][king_position.getColumn()]) {
				return true;
			}
		}
		
		return false;
	}

	private boolean testCheckMate(Color color) {
		if (!testCheck(color)) {
			return false;
		}
		
		List<Piece> list = pieces_on_the_board.stream().filter(x -> ((ChessPiece) x).getColor() == color).collect(Collectors.toList());
		
		for (Piece piece : list) {
			boolean[][] matrix = piece.possibleMoves();
			
			for (int i = 0; i < board.getRows(); i++) {
				for (int j = 0; j < board.getColumns(); j++) {
					
					if (matrix[i][j]) {
						Position source = ((ChessPiece) piece).getChessPosition().toPosition();
						Position target = new Position(i, j);
						
						Piece captured_piece = makeMove(source, target);
						
						boolean test_check = testCheck(color);
						undoMove(source, target, captured_piece);
						
						if (!test_check) {
							return false;
						}
					}
				}
			}
		}
		
		return true;
	}

	private void placeNewPiece(char column, int row, ChessPiece piece) {
		board.placePiece(piece, new ChessPosition(column, row).toPosition());
		pieces_on_the_board.add(piece);
	}

	private void initialSetup() {
		placeNewPiece('a', 1, new Rook(board, Color.WHITE));
		placeNewPiece('b', 1, new Knight(board, Color.WHITE));
		placeNewPiece('c', 1, new Bishop(board, Color.WHITE));
		placeNewPiece('d', 1, new Queen(board, Color.WHITE));
		placeNewPiece('e', 1, new King(board, Color.WHITE, this));
		placeNewPiece('f', 1, new Bishop(board, Color.WHITE));
		placeNewPiece('g', 1, new Knight(board, Color.WHITE));
		placeNewPiece('h', 1, new Rook(board, Color.WHITE));
		placeNewPiece('a', 2, new Pawn(board, Color.WHITE, this));
		placeNewPiece('b', 2, new Pawn(board, Color.WHITE, this));
		placeNewPiece('c', 2, new Pawn(board, Color.WHITE, this));
		placeNewPiece('d', 2, new Pawn(board, Color.WHITE, this));
		placeNewPiece('e', 2, new Pawn(board, Color.WHITE, this));
		placeNewPiece('f', 2, new Pawn(board, Color.WHITE, this));
		placeNewPiece('g', 2, new Pawn(board, Color.WHITE, this));
		placeNewPiece('h', 2, new Pawn(board, Color.WHITE, this));

		placeNewPiece('a', 8, new Rook(board, Color.BLACK));
		placeNewPiece('b', 8, new Knight(board, Color.BLACK));
		placeNewPiece('c', 8, new Bishop(board, Color.BLACK));
		placeNewPiece('d', 8, new Queen(board, Color.BLACK));
		placeNewPiece('e', 8, new King(board, Color.BLACK, this));
		placeNewPiece('f', 8, new Bishop(board, Color.BLACK));
		placeNewPiece('g', 8, new Knight(board, Color.BLACK));
		placeNewPiece('h', 8, new Rook(board, Color.BLACK));
		placeNewPiece('a', 7, new Pawn(board, Color.BLACK, this));
		placeNewPiece('b', 7, new Pawn(board, Color.BLACK, this));
		placeNewPiece('c', 7, new Pawn(board, Color.BLACK, this));
		placeNewPiece('d', 7, new Pawn(board, Color.BLACK, this));
		placeNewPiece('e', 7, new Pawn(board, Color.BLACK, this));
		placeNewPiece('f', 7, new Pawn(board, Color.BLACK, this));
		placeNewPiece('g', 7, new Pawn(board, Color.BLACK, this));
		placeNewPiece('h', 7, new Pawn(board, Color.BLACK, this));
	}
}
