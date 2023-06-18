package boardgame;

public class Piece {
    protected Position position;
    private Board board;

    public Piece(Board board){

        this.board = board;
        position = null;
    
    }
    //protected para somente ser acessado pelo pacote tabuleiro e plas subclasses de pecas;
    protected Board getBoard(){
        return board;
    }
}
    