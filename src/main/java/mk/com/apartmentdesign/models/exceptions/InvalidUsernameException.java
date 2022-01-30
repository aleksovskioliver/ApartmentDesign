package mk.com.apartmentdesign.models.exceptions;

public class InvalidUsernameException extends RuntimeException{
    public InvalidUsernameException (String username) {
        super (String.format ("Username %s invalid exception"));
    }
}
