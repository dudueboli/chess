package boardgame;

public class Board {
    
    private int rows;
    private int columns;
    private Piece[][] pieces;

    public Board(int rows, int columns){
        if(rows < 1 || columns < 1){
            throw new BoardException("ERROR: para criar o tabuleiro é necessário conter pelo menos uma 1 e 1 coluna");
        }
        this.rows = rows;
        this.columns = columns;
        pieces = new Piece [rows][columns];

    }
    //Retorna número de linhas do tabuleiro
    public int getRows(){
        return rows;
    }
    //Retorna o número de colunas do tabuleiro
    public int getColumns(){
        return columns;
    }
    //Retorna a peça na posição especificada pelas coordenadas de linha (row) e coluna (column)
    public Piece piece(int row, int column){
        if(!positionExists(row, column)){
            throw new BoardException("Posição inexistente");
        }
        return pieces[row][column];
    }
    //Retorna a peça na posição especificada pelo objeto Position
    public Piece piece(Position position){
         if(!positionExists(position)){
            throw new BoardException("Posição inexistente");
        }
        return pieces[position.getRow()][position.getColumn()];
    }
    //Posiciona uma peça (piece) na posição especificada (position) no tabuleiro
    public void placePiece(Piece piece, Position position){
        if(thereIsAPiece(position)){
            throw new BoardException("Já tem uma peça nessa posição " + position);
        }
        pieces[position.getRow()][position.getColumn()] = piece;
        piece.position = position;
    }
    //Remove a peça na posição especificada (position) do tabuleiro e a retorna
    public Piece removePiece(Position position){
        if(!positionExists(position)){
            throw new BoardException("Posição inexistente");
        }
        if(piece(position) == null){
            return null;
        }
        Piece aux = piece(position);
        aux.position = null;
        pieces[position.getRow()][position.getColumn()] = null;
        return aux;
    }
    //Verifica se uma posição com as coordenadas de linha e coluna especificadas existe
    private boolean positionExists(int row, int column){
        return row >= 0 && row < rows && column >=0 && column < columns;
    }
    //Verifica se a posição especificada pelo objeto Position existe
    public boolean positionExists(Position position){
        return positionExists(position.getRow(), position.getColumn());
    }
    //Verifica se há uma peça em uma posição especifica
    public boolean thereIsAPiece(Position position){
        if(!positionExists(position)){
            throw new BoardException("Posição inexistente");
        }
        return piece(position) != null;
    }
}
