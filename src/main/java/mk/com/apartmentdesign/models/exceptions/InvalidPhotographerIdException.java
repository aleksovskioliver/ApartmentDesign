package mk.com.apartmentdesign.models.exceptions;

public class InvalidPhotographerIdException extends RuntimeException{
    public InvalidPhotographerIdException (Long id) {
        super (String.format ("Photographer with id %d does not exist",id));
    }
}
