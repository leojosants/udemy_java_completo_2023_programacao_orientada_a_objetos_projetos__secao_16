/*
 * packages section
 */
package application;

/*
 * libraries section
 */
import java.util.Scanner;
import chess.ChessMatch;
import chess.ChessPiece;
import chess.ChessPosition;

public class Program {
	/*
	 * functions section
	 */
	private static Scanner generateInstanceScanner() {
		return new Scanner(System.in);
	}
	
	public static void main(String[] args) {

		Scanner scanner = generateInstanceScanner();
		ChessMatch chess_match = new ChessMatch();

		while (true) {
			UI.printBoard(chess_match.getPieces());
			System.out.println();

			System.out.print("Source: ");
			ChessPosition source = UI.readChessPosition(scanner);
			
			System.out.println();
			
			System.out.print("Target: ");
			ChessPosition target = UI.readChessPosition(scanner);
			
			ChessPiece captured_piece = chess_match.performChessMovie(source, target);
		}
	}
}
