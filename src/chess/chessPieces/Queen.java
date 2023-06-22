package chess.chessPieces;

import boardgame.Board;
import boardgame.Position;
import chess.ChessPiece;
import chess.Color;

public class Queen extends ChessPiece {

    public Queen(Board board, Color color){
        super(board, color);
    }  
    public String toString(){
        return "Q"; 
    }
    //Calcula e retorna uma matriz de booleans que representa os movimentos possíveis da rainha no tabuleiro
    public boolean[][] possibleMoves() {
       boolean[][] mat = new boolean[getBoard().getRows()][getBoard().getColumns()];
        
       Position p = new Position(0, 0);
       
       //possible positions above the Rook
       p.setValues(position.getRow() - 1, position.getColumn());
       while(getBoard().positionExists(p) && !getBoard().thereIsAPiece(p)){
           mat[p.getRow()][p.getColumn()] = true;
           p.setRow(p.getRow() - 1);
        }
        if(getBoard().positionExists(p) && isThereOpponentPiece(p)){
           mat[p.getRow()][p.getColumn()] = true;
        }

        //possible positions on the left of the Rook
       p.setValues(position.getRow(), position.getColumn() - 1);
       while(getBoard().positionExists(p) && !getBoard().thereIsAPiece(p)){
           mat[p.getRow()][p.getColumn()] = true;
           p.setColumn(p.getColumn() - 1);
        }
        if(getBoard().positionExists(p) && isThereOpponentPiece(p)){
           mat[p.getRow()][p.getColumn()] = true;
        }

         //possible positions on the right of the Rook
       p.setValues(position.getRow(), position.getColumn() + 1);
       while(getBoard().positionExists(p) && !getBoard().thereIsAPiece(p)){
           mat[p.getRow()][p.getColumn()] = true;
           p.setColumn(p.getColumn() + 1);
        }
        if(getBoard().positionExists(p) && isThereOpponentPiece(p)){
           mat[p.getRow()][p.getColumn()] = true;
        }

        //possible positions below the Rook
       p.setValues(position.getRow() + 1, position.getColumn());
       while(getBoard().positionExists(p) && !getBoard().thereIsAPiece(p)){
           mat[p.getRow()][p.getColumn()] = true;
           p.setRow(p.getRow() + 1);
        }
        if(getBoard().positionExists(p) && isThereOpponentPiece(p)){
           mat[p.getRow()][p.getColumn()] = true;
        }

        //nw
       p.setValues(position.getRow() - 1, position.getColumn() - 1);
       while(getBoard().positionExists(p) && !getBoard().thereIsAPiece(p)){
           mat[p.getRow()][p.getColumn()] = true;
           p.setValues(p.getRow() - 1, p.getColumn() - 1);
        }
        if(getBoard().positionExists(p) && isThereOpponentPiece(p)){
           mat[p.getRow()][p.getColumn()] = true;
        }

        //ne
       p.setValues(position.getRow() - 1, position.getColumn() + 1);
       while(getBoard().positionExists(p) && !getBoard().thereIsAPiece(p)){
           mat[p.getRow()][p.getColumn()] = true;
           p.setValues(p.getRow() - 1, p.getColumn() + 1);
        }
        if(getBoard().positionExists(p) && isThereOpponentPiece(p)){
           mat[p.getRow()][p.getColumn()] = true;
        }

         //se
       p.setValues(position.getRow() + 1, position.getColumn() + 1);
       while(getBoard().positionExists(p) && !getBoard().thereIsAPiece(p)){
           mat[p.getRow()][p.getColumn()] = true;
           p.setValues(p.getRow() + 1, p.getColumn() + 1);
        }
        if(getBoard().positionExists(p) && isThereOpponentPiece(p)){
           mat[p.getRow()][p.getColumn()] = true;
        }

        //sw
       p.setValues(position.getRow() + 1, position.getColumn() - 1);
       while(getBoard().positionExists(p) && !getBoard().thereIsAPiece(p)){
           mat[p.getRow()][p.getColumn()] = true;
           p.setValues(p.getRow() + 1, p.getColumn() - 1);
        }
        if(getBoard().positionExists(p) && isThereOpponentPiece(p)){
           mat[p.getRow()][p.getColumn()] = true;
        }

       return mat;
    }
}
