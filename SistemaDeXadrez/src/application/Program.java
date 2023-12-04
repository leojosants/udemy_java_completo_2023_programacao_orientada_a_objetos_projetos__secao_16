package application;

import chess.ChessMatch;

public class Program {

	public static void main(String[] args) {

		ChessMatch chess_match = new ChessMatch();
		UI.printBoard(chess_match.getPieces());

	}

}
