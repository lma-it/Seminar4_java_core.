package seminar4_homework.exceptions;

public class NotNullCountOfProductsException extends RuntimeException{
    private final String message;

    public NotNullCountOfProductsException(String message){
        this.message = message;
    }

    @Override
    public String getMessage() {
        return message;
    }
}
