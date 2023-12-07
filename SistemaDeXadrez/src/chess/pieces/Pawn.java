/* -------------------- packages section -------------------- */
package chess.pieces;

/* -------------------- imports section -------------------- */
import boardgame.Board;
import boardgame.Position;
import chess.ChessMatch;
import chess.ChessPiece;
import chess.Color;

/* -------------------- Pawn class -------------------- */
public class Pawn extends ChessPiece {

	/* -------------------- attributes section -------------------- */
	private ChessMatch chess_match;
	
	/* -------------------- constructors section -------------------- */
	public Pawn(Board board, Color color, ChessMatch chess_match) {
		super(board, color);
		this.chess_match = chess_match;
	}

	/* -------------------- methods section -------------------- */
	@Override
	public boolean[][] possibleMoves() {
		boolean[][] matrix = new boolean[getBoard().getRows()][getBoard().getColumns()];

		Position position = new Position(0, 0);

		if (getColor() == Color.WHITE) {
			position.setValues(this.position.getRow() - 1, this.position.getColumn());
			if (getBoard().positionExists(position) && !getBoard().thereIsAPiece(position)) {
				matrix[position.getRow()][position.getColumn()] = true;
			}
			
			position.setValues(this.position.getRow() - 2, this.position.getColumn());
			Position position_2 = new Position(this.position.getRow() - 1, this.position.getColumn());
			if (getBoard().positionExists(position) && !getBoard().thereIsAPiece(position) && getBoard().positionExists(position_2) && !getBoard().thereIsAPiece(position_2) && getMoveCount() == 0) {
				matrix[position.getRow()][position.getColumn()] = true;
			}
			
			position.setValues(this.position.getRow() - 1, this.position.getColumn() - 1);
			if (getBoard().positionExists(position) && isThereOpponentPiece(position)) {
				matrix[position.getRow()][position.getColumn()] = true;
			}
		
			position.setValues(this.position.getRow() - 1, this.position.getColumn() + 1);
			if (getBoard().positionExists(position) && isThereOpponentPiece(position)) {
				matrix[position.getRow()][position.getColumn()] = true;
			}
			
			/* special move en passant - white */
			if (this.position.getRow() == 3) {
				Position left = new Position(this.position.getRow(), this.position.getColumn() - 1);
				if(getBoard().positionExists(left) && isThereOpponentPiece(left) && getBoard().piece(left) == chess_match.getEnPassanVulnerable()) {
					matrix[left.getRow() - 1][left.getColumn()] = true;
				}
				
				Position right = new Position(this.position.getRow(), this.position.getColumn() + 1);
				if(getBoard().positionExists(right) && isThereOpponentPiece(right) && getBoard().piece(right) == chess_match.getEnPassanVulnerable()) {
					matrix[right.getRow() - 1][right.getColumn()] = true;
				}
			}
		} 
		else {
			position.setValues(this.position.getRow() + 1, this.position.getColumn());
			if (getBoard().positionExists(position) && !getBoard().thereIsAPiece(position)) {
				matrix[position.getRow()][position.getColumn()] = true;
			}
			
			position.setValues(this.position.getRow() + 2, this.position.getColumn());
			Position p2 = new Position(this.position.getRow() + 1, this.position.getColumn());
			if (getBoard().positionExists(position) && !getBoard().thereIsAPiece(position) && getBoard().positionExists(p2)
					&& !getBoard().thereIsAPiece(p2) && getMoveCount() == 0) {
				matrix[position.getRow()][position.getColumn()] = true;
			}
			
			position.setValues(this.position.getRow() + 1, this.position.getColumn() - 1);
			if (getBoard().positionExists(position) && isThereOpponentPiece(position)) {
				matrix[position.getRow()][position.getColumn()] = true;
			}
			
			position.setValues(this.position.getRow() + 1, this.position.getColumn() + 1);
			if (getBoard().positionExists(position) && isThereOpponentPiece(position)) {
				matrix[position.getRow()][position.getColumn()] = true;
			}
			
			/* special move en passant - black */
			if (this.position.getRow() == 4) {
				Position left = new Position(this.position.getRow(), this.position.getColumn() - 1);
				if(getBoard().positionExists(left) && isThereOpponentPiece(left) && getBoard().piece(left) == chess_match.getEnPassanVulnerable()) {
					matrix[left.getRow() + 1][left.getColumn()] = true;
				}
				
				Position right = new Position(this.position.getRow(), this.position.getColumn() + 1);
				if(getBoard().positionExists(right) && isThereOpponentPiece(right) && getBoard().piece(right) == chess_match.getEnPassanVulnerable()) {
					matrix[right.getRow() + 1][right.getColumn()] = true;
				}
			}
		}
		
		return matrix;
	}

	@Override
	public String toString() {
		return "P";
	}

}
