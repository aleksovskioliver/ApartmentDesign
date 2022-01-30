package mk.com.apartmentdesign.models.exceptions;

public class InvalidDesignerIdException extends RuntimeException{
    public InvalidDesignerIdException (Long id) {
        super (String.format ("Designer with id %d does not exist",id));
    }
}
