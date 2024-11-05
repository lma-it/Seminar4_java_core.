package seminar4_Exceptions;

public class WrongLoginException extends Exception {
    private String message;
    public WrongLoginException(String message){
        this.message = message;
    }

    public String getMessage(){
        return this.message;
    }
}
