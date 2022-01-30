package mk.com.apartmentdesign.models.exceptions;

public class InvalidEmailOrPasswordException extends RuntimeException{
    public InvalidEmailOrPasswordException () {
        super (String.format ("e-mail or password is invalid"));
    }
}
