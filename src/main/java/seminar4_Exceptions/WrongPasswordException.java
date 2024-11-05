package seminar4_Exceptions;

public class WrongPasswordException extends Exception {
    private String message;
    public WrongPasswordException(String message){
        this.message = message;
    }

    public String getMessage(){
        return this.message;
    }
}
