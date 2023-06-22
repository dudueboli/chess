package chess;
import boardgame.Position;

public class ChessPosition {
    
    private char column;
    private int row;

    public ChessPosition(char column, int row){
        if (column < 'a' || column > 'h' || row < 1 || row > 8){
            throw new ChessException("ERROR: Valores válidos são de a1 até h8");
        }
        this.column = column;
        this.row = row;

    }
    public char getColumn(){
        return column;
    }
    public int getRow(){
        return row;    
    }
    //Notação de xadrez (por exemplo, "a1", "e5") / Posições internas do tabuleiro (linhas e colunas da matriz que representa o tabuleiro)
    //Método que converte a posição da peça de xadrez para a posição interna do tabuleiro
    protected Position toPosition(){
        return new Position(8 - row, column - 'a');
    }
    //Método que converte uma posição interna do tabuleiro para a notação de posição de xadrez
    protected static ChessPosition fromPosition(Position position){
        return new ChessPosition((char)('a' + position.getColumn()), 8 - position.getRow());
    }
    public String toString(){
        return "" + column + row;
    }
}

