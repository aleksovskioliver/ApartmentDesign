package mk.com.apartmentdesign.models.exceptions;

public class PasswordDoNotMatchException extends RuntimeException{

    public PasswordDoNotMatchException () {
        super(String.format ("Passwords do not matches!"));
    }
}
