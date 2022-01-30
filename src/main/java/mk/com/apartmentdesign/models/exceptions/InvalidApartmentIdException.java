package mk.com.apartmentdesign.models.exceptions;

public class InvalidApartmentIdException extends RuntimeException{
    public InvalidApartmentIdException (Long id) {
        super (String.format ("Apartment with id %d does not exist",id));
    }
}
