package mk.com.apartmentdesign.models.exceptions;

import mk.com.apartmentdesign.models.User;

public class InvalidUserCredentialsException extends RuntimeException{
    public InvalidUserCredentialsException (String username) {
        super (String.format ("User with username %s does not have credentials for this",username));
    }
}
