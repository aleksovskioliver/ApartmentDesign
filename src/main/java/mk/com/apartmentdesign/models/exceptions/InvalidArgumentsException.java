package mk.com.apartmentdesign.models.exceptions;

public class InvalidArgumentsException extends RuntimeException{
    public InvalidArgumentsException () {
        super (String.format ("Invalid Arguments Exception"));
    }
}
