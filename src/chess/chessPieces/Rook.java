package chess.chessPieces;

import boardgame.Board;
import chess.ChessPiece;
import chess.Color;

public class Rook extends ChessPiece {

    public Rook(Board board, Color color){
        super(board, color);
    }
    
    public String toString(){
        return "R"; 
    }
    public boolean[][] possibleMoves() {
       boolean[][] mat = new boolean[getBoard().getRows()][getBoard().getColumns()];
       return mat;
    }
}