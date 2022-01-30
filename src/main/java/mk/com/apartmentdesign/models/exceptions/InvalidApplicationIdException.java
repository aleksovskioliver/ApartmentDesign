package mk.com.apartmentdesign.models.exceptions;

public class InvalidApplicationIdException extends RuntimeException{
    public InvalidApplicationIdException (Long id) {
        super (String.format ("Application with id %d does not exist",id));
    }
}
