package mk.com.apartmentdesign.models.exceptions;

public class InvalidEmailException extends RuntimeException{
    public InvalidEmailException (String email) {
        super (String.format ("e-mail address: %s does not exist",email));
    }
}
