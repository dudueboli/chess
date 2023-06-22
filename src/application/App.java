package application;
import java.util.InputMismatchException;
import java.util.Scanner;

import chess.ChessException;
import chess.ChessMatch;
import chess.ChessPiece;
import chess.ChessPosition;
public class App {
    //Ponto de partida do programa
    public static void main(String[]args){
        Scanner dudu = new Scanner(System.in);
        ChessMatch chessMatch = new ChessMatch();
        ChessPiece[] captured = new ChessPiece[32];
        //loop que continua até acontecer um xeque-mate
        while(!chessMatch.getCheckMate()){
        try{
            UI.clearScreen();
            UI.printMatch(chessMatch, captured);
            System.out.println();
            System.out.print("Source: ");
            ChessPosition source = UI.readChessPosition(dudu);

            boolean[][] possibleMoves = chessMatch.possibleMoves(source);
            UI.clearScreen();
            UI.printBoard(chessMatch.getPieces(), possibleMoves);
            System.out.println();
            System.out.print("Target: ");
            ChessPosition target = UI.readChessPosition(dudu);

            ChessPiece capturedPiece = chessMatch.performChessMove(source, target);

            if(capturedPiece != null){
                add(capturedPiece, captured);
            }
            
            }catch (ChessException e){
                System.out.println(e.getMessage());
                dudu.nextLine();
            }catch (InputMismatchException e){
                System.out.println(e.getMessage());
                dudu.nextLine();
            }
        }
        UI.clearScreen();
        UI.printMatch(chessMatch, captured);
    }
    //Método de adição de objetos em uma array
    public static void add(ChessPiece c , ChessPiece[] arr){
        for(int i=0;i<arr.length;i++){
            if(arr[i] == null){
                arr[i] = c;
                return;
            }
        }
    }
}
