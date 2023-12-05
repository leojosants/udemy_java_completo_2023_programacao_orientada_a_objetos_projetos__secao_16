/* -------------------- packages section -------------------- */
package application;

/* -------------------- imports section -------------------- */
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;
import chess.ChessException;
import chess.ChessMatch;
import chess.ChessPiece;
import chess.ChessPosition;

/* -------------------- Program class -------------------- */
public class Program {

	/* -------------------- functions section -------------------- */
	private static Scanner generateInstanceScanner() {
		return new Scanner(System.in);
	}

	private static void displayMessageChessException(Scanner scanner, Throwable e) {
		System.out.println(e.getMessage());
		scanner.nextLine();
	}

	private static void displayMessageInputMismatchException(Scanner scanner, Throwable e) {
		System.out.println(e.getMessage());
		scanner.nextLine();
	}
	
	private static List<ChessPiece> generateInstanceChessPieceList() {
		return new ArrayList<>();
	}
	
	/* -------------------- main method -------------------- */
	public static void main(String[] args) {

		Scanner scanner = generateInstanceScanner();
		ChessMatch chess_match = new ChessMatch();
		List<ChessPiece> captured = generateInstanceChessPieceList();

		while (!chess_match.getCheckMate()) {
			try {
				UI.clearScreen();
				UI.printMatch(chess_match, captured);
				
				System.out.println();
				System.out.print("Source: ");
				ChessPosition source = UI.readChessPosition(scanner);

				boolean[][] possible_moves = chess_match.possibleMoves(source);
				UI.clearScreen();
				UI.printBoard(chess_match.getPieces(), possible_moves);
				
				System.out.println();
				System.out.print("Target: ");
				ChessPosition target = UI.readChessPosition(scanner);

				ChessPiece captured_piece = chess_match.performChessMovie(source, target);
				
				if (captured_piece != null) {
					captured.add(captured_piece);
				}
			}
			catch (ChessException e) {
				displayMessageChessException(scanner, e);
			}
			catch (InputMismatchException e) {
				displayMessageInputMismatchException(scanner, e);
			}
		}
		
		UI.clearScreen();
		UI.printMatch(chess_match, captured);
	}
}
