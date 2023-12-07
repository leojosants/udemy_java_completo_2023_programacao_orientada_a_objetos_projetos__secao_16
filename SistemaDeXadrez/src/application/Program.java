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

				ChessPiece captured_piece = chess_match.performChessMove(source, target);
				
				if (captured_piece != null) {
					captured.add(captured_piece);
				}
				
				if (chess_match.getPromoted() != null) {
					System.out.print("Enter piece for promotion [B], [N], [R] or [Q]: ");
					String type = scanner.nextLine().toUpperCase().trim();
					
					while (!type.equals("B") && !type.equals("N") && !type.equals("R") && !type.equals("Q")) {
						System.out.print("Invalid value! Enter piece for promotion [B], [N], [R] or [Q]: ");
						type = scanner.nextLine().toUpperCase().trim();
					}
					
					chess_match.replacePromotedPiece(type);
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
