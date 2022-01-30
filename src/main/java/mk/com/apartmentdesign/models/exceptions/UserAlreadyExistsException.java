package mk.com.apartmentdesign.models.exceptions;

public class UserAlreadyExistsException extends RuntimeException{
    public UserAlreadyExistsException (String email) {
        super (String.format ("User with email: %s already exist",email));
    }
}
