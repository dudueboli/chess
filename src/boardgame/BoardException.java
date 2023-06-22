package boardgame;

//Criação de exceção personalizada
public class BoardException extends RuntimeException{
    private static final long serialVersionUID = 1L;

    public BoardException(String msg){
        super(msg);
    }
}
