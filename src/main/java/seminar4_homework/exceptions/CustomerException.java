package seminar4_homework.exceptions;

public class CustomerException extends RuntimeException{

    private String message = "Покупателя не существует.";

    public CustomerException(String message){
        this.message = message;
    }
    public CustomerException() {}

    @Override
    public String getMessage() {
        return message;
    }
}
