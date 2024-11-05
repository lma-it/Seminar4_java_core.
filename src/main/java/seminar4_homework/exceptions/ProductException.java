package seminar4_homework.exceptions;

public class ProductException extends RuntimeException{

    private final String message;

    public ProductException(String message){
        this.message = message;
    }

    @Override
    public String getMessage() {
        return message;
    }
}
