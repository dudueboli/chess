package application;
import java.util.InputMismatchException;
import java.util.Scanner;

import chess.ChessException;
import chess.ChessMatch;
import chess.ChessPiece;
import chess.ChessPosition;
public class App {
    public static void main(String[]args){
        Scanner dudu = new Scanner(System.in);
        ChessMatch chessMatch = new ChessMatch();

        while(true){
        try{
            UI.clearScreen();
            UI.printBoard(chessMatch.getPieces());
            System.out.println();
            System.out.print("Source: ");
            ChessPosition source = UI.readChessPosition(dudu);

            System.out.println();
            System.out.print("Target: ");
            ChessPosition target = UI.readChessPosition(dudu);

            ChessPiece capturedPiece = chessMatch.performChessMove(source, target);
            }catch (ChessException e){
                System.out.println(e.getMessage());
                dudu.nextLine();
            }catch (InputMismatchException e){
                System.out.println(e.getMessage());
                dudu.nextLine();
            }
        }

    }
}
