package chess;
import boardgame.Piece;
import boardgame.Position;
import boardgame.Board;

public abstract class ChessPiece extends Piece {
    
    private Color color;
    private int moveCount;
    
    public ChessPiece(Board board, Color color){
        super(board);
        this.color = color;
    }  
    public Color getColor(){
        return color;
    }
    public int getMoveCount(){
        return moveCount;
    }
    //Incrementa o valor de moveCount
    public void increaseMoveCount(){
        moveCount++;
    }
    //Decrementam o valor de moveCount
    public void decreaseMoveCount(){
        moveCount--;
    }
    //Retorna uma instância de ChessPosition convertendo a posição atual da peça em uma posição de xadrez
    public ChessPosition getChessPosition(){
        return ChessPosition.fromPosition(position);

    }
    //Verifica se há uma peça oponente em uma determinada posição
    protected boolean isThereOpponentPiece(Position position){
        ChessPiece p = (ChessPiece)getBoard().piece(position);
        return p != null && p.getColor() != color;
    }
}
