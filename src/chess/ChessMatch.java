package chess;

import java.util.Arrays;
import boardgame.Board;
import boardgame.Piece;
import boardgame.Position;
import chess.chessPieces.Bishop;
import chess.chessPieces.King;
import chess.chessPieces.Knight;
import chess.chessPieces.Pawn;
import chess.chessPieces.Queen;
import chess.chessPieces.Rook;

public class ChessMatch {
    
    private Board board;
    private int turn;
    private Color currentPlayer;
    private boolean check;
    private boolean checkMate;
    private Piece[] piecesOnTheBoard = new ChessPiece[32];
    private Piece[] capturedPieces = new ChessPiece[32];

    //Inicia uma nova partida de xadrez
    public ChessMatch(){
        board = new Board(8, 8);
        turn = 1;
        currentPlayer = Color.WHITE;
        check = false;
        initialSetup();
    }
    public int getTurn(){
        return turn;
    }  
    public Color getCurrentPlayer(){
        return currentPlayer;
    }  
    public boolean getCheck(){
        return check;
    }  
    public boolean getCheckMate(){
        return checkMate;
    }
    public ChessPiece[][] getPieces(){
        ChessPiece[][] mat = new ChessPiece[board.getRows()][board.getColumns()];
        for (int i=0; i<board.getRows(); i++){
            for (int j=0; j<board.getColumns(); j++){
                mat[i][j] = (ChessPiece) board.piece(i, j);

            }
        }
        return mat;
    }
    //imprimir posições possiveis a partir de uma posição de origem
    public boolean[][] possibleMoves(ChessPosition sourcePosition){
        Position position = sourcePosition.toPosition();
        validateSourcePosition(position);
        return board.piece(position).possibleMoves();
    }
    //Executa um movimento de xadrez a partir de uma posição de origem para uma posição de destino
    public ChessPiece performChessMove(ChessPosition sourcePosition, ChessPosition targetPosition){
        Position source = sourcePosition.toPosition();
        Position target = targetPosition.toPosition();
        validateSourcePosition(source);
        validateTargetPosition(source, target);
        Piece capturedPiece = makeMove(source, target);

        if (testCheck(currentPlayer)){
            undoMove(source, target, capturedPiece);
            throw new ChessException("Você não pode se colocar/manter em cheque");
        }

        check = (testCheck(opponent(currentPlayer))) ? true : false;

        if(testCheckMate(opponent(currentPlayer))){
           checkMate = true; 
        }else{
            nextTurn();
        }
    
        return (ChessPiece) capturedPiece;
    }
    //Realiza um movimento no tabuleiro
    private Piece makeMove(Position source, Position target){
        ChessPiece p = (ChessPiece)board.removePiece(source);
        p.increaseMoveCount();
        Piece capturedPiece = board.removePiece(target);
        board.placePiece(p, target);

        if (capturedPiece != null) {
    // Removendo a peça capturada do array piecesOnTheBoard
    Piece[] newPiecesOnTheBoard = new Piece[piecesOnTheBoard.length - 1];
    int index = 0;
    for (int i = 0; i < piecesOnTheBoard.length; i++) {
        if (piecesOnTheBoard[i] != capturedPiece) {
            newPiecesOnTheBoard[index] = piecesOnTheBoard[i];
            index++;
        }
    }
    piecesOnTheBoard = newPiecesOnTheBoard;

    // Adicionando a peça capturada ao array capturedPieces
    Piece[] newCapturedPieces = new Piece[capturedPieces.length + 1];
    for (int i = 0; i < capturedPieces.length; i++) {
        newCapturedPieces[i] = capturedPieces[i];
    }
    newCapturedPieces[capturedPieces.length] = capturedPiece;
    capturedPieces = newCapturedPieces;
    }
        return capturedPiece;
    }
    //Desfaz um movimento no tabuleiro
    private void undoMove(Position source, Position target, Piece capturedPiece){
        ChessPiece p = (ChessPiece)board.removePiece(target);
        p.decreaseMoveCount();
        board.placePiece(p, source);
        

        if(capturedPiece != null){
            board.placePiece(capturedPiece, target);
             // Removendo a peça capturada do array capturedPieces
            Piece[] newCapturedPieces = new Piece[capturedPieces.length - 1];
            int index = 0;
                for (int i = 0; i < capturedPieces.length; i++) {
                    if (capturedPieces[i] != capturedPiece) {
                        newCapturedPieces[index] = capturedPieces[i];
                        index++;
                    }
                }
            capturedPieces = newCapturedPieces;

            // Adicionando a peça capturada ao array piecesOnTheBoard
            Piece[] newPiecesOnTheBoard = new Piece[piecesOnTheBoard.length + 1];
                for (int i = 0; i < piecesOnTheBoard.length; i++) {
                    newPiecesOnTheBoard[i] = piecesOnTheBoard[i];
                }
            newPiecesOnTheBoard[piecesOnTheBoard.length] = capturedPiece;
            piecesOnTheBoard = newPiecesOnTheBoard; 
        }
    }
    //Valida a posiçaõ de origem
    private void validateSourcePosition(Position position){
        if(!board.thereIsAPiece(position)){
            throw new ChessException("Não existe peça nessa posicição");
        }
        if (currentPlayer != ((ChessPiece)board.piece(position)).getColor()){
            throw new ChessException("Essa peça não é sua");
        }
        if(!board.piece(position).isThereAnyPossibleMove()){
            throw new ChessException("Não existem possiveis movimentos para a peça selecionada");
        }
    }
    //Valida a posição de destino
    private void validateTargetPosition(Position source, Position target){
        if(!board.piece(source).possibleMove(target)){
            throw new ChessException("A peça escolhida não pode ir para essa posição");
        }

    }
    //Troca o turno e o jogador atual
    private void nextTurn(){
        turn++;
        currentPlayer =(currentPlayer == Color.WHITE) ? Color.BLACK : Color.WHITE;
    }
    //Retorna a cor do oponente com base na cor fornecida
    private Color opponent(Color color){
        return(color == Color.WHITE) ? Color.BLACK : Color.WHITE;
    }
    //Retorna a instância do rei da cor fornecida no tabuleiro
    private ChessPiece king(Color color){
        Piece[] array = Arrays.stream(piecesOnTheBoard)
            .filter(x -> x != null && ((ChessPiece) x).getColor() == color)
            .toArray(ChessPiece[]::new);
        for(Piece p : array){
            if(p instanceof King){
                return(ChessPiece)p;
            }
        }
        throw new IllegalStateException("Não existe o Rei " + color + " no tabuleiro");
    }
    //Verifica se um jogador está em xeque e verifica se algum movimento das peças do oponente coloca o rei do jogador atual em xeque
    private boolean testCheck(Color color){
        Position kingPosition = king(color).getChessPosition().toPosition();
        Piece[] opponentPieces = Arrays.stream(piecesOnTheBoard)
            .filter(x -> x != null && ((ChessPiece) x).getColor() == opponent(color))
            .toArray(ChessPiece[]::new);
        for(Piece p : opponentPieces){
            boolean[][] mat = p.possibleMoves();
            if(mat[kingPosition.getRow()][kingPosition.getColumn()]){
                return true;
            }
        }
        return false;
    }
    //Verifica se o jogador atual está em xeque-mate
    private boolean testCheckMate(Color color){
        if(!testCheck(color)){
            return false;
        }
        Piece[] array = Arrays.stream(piecesOnTheBoard)
            .filter(x -> x != null && ((ChessPiece) x).getColor() == color)
            .toArray(ChessPiece[]::new);
        
        for(Piece p : array){
            boolean[][] mat = p.possibleMoves();
            for(int i=0; i<board.getRows(); i++){
                for(int j=0; j<board.getColumns(); j++){
                    if(mat[i][j]){
                       Position source = ((ChessPiece)p).getChessPosition().toPosition();
                       Position target = new Position(i, j);
                       Piece capturePiece = makeMove(source, target);
                       boolean testCheck = testCheck(color);
                       undoMove(source, target, capturePiece);
                       if(!testCheck){
                        return false;
                       }
                    }
                }
            }
        }
        return true;
    }
    // Posiciona uma nova peça no tabuleiro e a adiciona ao array piecesOnTheBoard
    private void placeNewPiece(char column, int row, ChessPiece piece){
        board.placePiece(piece, new ChessPosition(column, row).toPosition());
        add(piece, piecesOnTheBoard);
    }
    //osiciona as peças iniciais no tabuleiro no início de uma partida de xadrez
    private void initialSetup(){
        placeNewPiece('a', 1, new Rook(board,Color.WHITE));
        placeNewPiece('b', 1, new Knight(board,Color.WHITE));
        placeNewPiece('c', 1, new Bishop(board,Color.WHITE));
        placeNewPiece('d', 1, new Queen(board,Color.WHITE));
        placeNewPiece('e', 1, new King(board,Color.WHITE));
        placeNewPiece('f', 1, new Bishop(board,Color.WHITE));
        placeNewPiece('g', 1, new Knight(board,Color.WHITE));
        placeNewPiece('h', 1, new Rook(board,Color.WHITE));
        placeNewPiece('a', 2, new Pawn(board,Color.WHITE));
        placeNewPiece('b', 2, new Pawn(board,Color.WHITE));
        placeNewPiece('c', 2, new Pawn(board,Color.WHITE));
        placeNewPiece('d', 2, new Pawn(board,Color.WHITE));
        placeNewPiece('e', 2, new Pawn(board,Color.WHITE));
        placeNewPiece('f', 2, new Pawn(board,Color.WHITE));
        placeNewPiece('g', 2, new Pawn(board,Color.WHITE));
        placeNewPiece('h', 2, new Pawn(board,Color.WHITE));

        placeNewPiece('a', 8, new Rook(board,Color.BLACK));
        placeNewPiece('b', 8, new Knight(board,Color.BLACK));
        placeNewPiece('c', 8, new Bishop(board,Color.BLACK));
        placeNewPiece('d', 8, new Queen(board,Color.BLACK));
        placeNewPiece('e', 8, new King(board,Color.BLACK));
        placeNewPiece('f', 8, new Bishop(board,Color.BLACK));
        placeNewPiece('g', 8, new Knight(board,Color.BLACK));
        placeNewPiece('h', 8, new Rook(board,Color.BLACK));
        placeNewPiece('a', 7, new Pawn(board,Color.BLACK));
        placeNewPiece('b', 7, new Pawn(board,Color.BLACK));
        placeNewPiece('c', 7, new Pawn(board,Color.BLACK));
        placeNewPiece('d', 7, new Pawn(board,Color.BLACK));
        placeNewPiece('e', 7, new Pawn(board,Color.BLACK));
        placeNewPiece('f', 7, new Pawn(board,Color.BLACK));
        placeNewPiece('g', 7, new Pawn(board,Color.BLACK));
        placeNewPiece('h', 7, new Pawn(board,Color.BLACK));


    }
    //Método de adição de objetos em uma array
    public static void add(ChessPiece c , Piece[] arr){
        for(int i=0;i<arr.length;i++){
            if(arr[i] == null){
                arr[i] = c;
                return;
            }
        }
    }
}
