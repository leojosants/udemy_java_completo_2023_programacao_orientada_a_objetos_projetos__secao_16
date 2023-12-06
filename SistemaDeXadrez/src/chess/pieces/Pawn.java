/* -------------------- packages section -------------------- */
package chess.pieces;

/* -------------------- imports section -------------------- */
import boardgame.Board;
import boardgame.Position;
import chess.ChessPiece;
import chess.Color;

/* -------------------- Pawn class -------------------- */
public class Pawn extends ChessPiece {

	/* -------------------- constructors section -------------------- */
	public Pawn(Board board, Color color) {
		super(board, color);
	}

	/* -------------------- methods section -------------------- */
	@Override
	public boolean[][] possibleMoves() {
		boolean[][] matriz = new boolean[this.getBoard().getRows()][this.getBoard().getColumns()];
		Position position = new Position(0, 0);
		
		if (this.getColor() == Color.WHITE) {
			position.setValues(this.position.getRow() - 1, this.position.getColumn());
			
			if (this.getBoard().positionExists(position) && !this.getBoard().theresIsAPiece(position)) {
				matriz[position.getRow()][position.getColumn()] = true;
			}
			
			position.setValues(this.position.getRow() - 2, this.position.getColumn());
			Position position_2 = new Position(this.position.getRow() - 1, this.position.getColumn());
			
			if (this.getBoard().positionExists(position) && !this.getBoard().theresIsAPiece(position) && this.getBoard().positionExists(position_2) && !this.getBoard().theresIsAPiece(position_2) && this.getMovieCount() == 0) {
				matriz[position.getRow()][position.getColumn()] = true;
			}
			
			position.setValues(this.position.getRow() - 1, this.position.getColumn() - 1);
			
			if (this.getBoard().positionExists(position) && this.isThereOpponentPiece(position)) {
				matriz[position.getRow()][position.getColumn()] = true;
			}
			
			position.setValues(this.position.getRow() - 1, this.position.getColumn() + 1);
			
			if (this.getBoard().positionExists(position) && this.isThereOpponentPiece(position)) {
				matriz[position.getRow()][position.getColumn()] = true;
			}
		}
		else {
			position.setValues(this.position.getRow() + 1, this.position.getColumn());
			
			if (this.getBoard().positionExists(position) && !this.getBoard().theresIsAPiece(position)) {
				matriz[position.getRow()][position.getColumn()] = true;
			}
			
			position.setValues(this.position.getRow() + 2, this.position.getColumn());
			Position position_2 = new Position(this.position.getRow() - 1, this.position.getColumn());
			
			if (this.getBoard().positionExists(position) && !this.getBoard().theresIsAPiece(position) && this.getBoard().positionExists(position_2) && !this.getBoard().theresIsAPiece(position_2) && this.getMovieCount() == 0) {
				matriz[position.getRow()][position.getColumn()] = true;
			}
			
			position.setValues(this.position.getRow() + 1, this.position.getColumn() - 1);
			
			if (this.getBoard().positionExists(position) && this.isThereOpponentPiece(position)) {
				matriz[position.getRow()][position.getColumn()] = true;
			}
			
			position.setValues(this.position.getRow() + 1, this.position.getColumn() + 1);
			
			if (this.getBoard().positionExists(position) && this.isThereOpponentPiece(position)) {
				matriz[position.getRow()][position.getColumn()] = true;
			}
		}
		
		return matriz;
	}

	@Override
	public String toString() {
		return "P";
	}

}
