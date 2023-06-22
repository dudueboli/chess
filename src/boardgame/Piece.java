package boardgame;

public abstract class Piece {
    protected Position position;
    private Board board;
    
    //Nova instância de Piece associada a um objeto Board específico
    public Piece(Board board){
        this.board = board;
        position = null;
    
    }
    //protected para somente ser acessado pelo pacote tabuleiro e plas subclasses de pecas/Retorna a referência ao objeto Board associado à peça
    protected Board getBoard(){
        return board;
    }
    //matriz de booleanos que representa os movimentos possíveis da peça no tabuleiro
    public abstract boolean[][] possibleMoves();
    //Verifica se um determinado movimento é possível para a peça
    public boolean possibleMove(Position position){
        return possibleMoves()[position.getRow()][position.getColumn()];
    }
    //Verifica se há algum movimento possível para a peça
    public boolean isThereAnyPossibleMove(){
        boolean[][] mat = possibleMoves();
        for(int i=0; i<mat.length; i++){
            for(int j=0; j<mat.length;j++){
                if(mat[i][j]){
                    return true;
                }
            }
        }
        return false;
    }

}

    