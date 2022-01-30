package mk.com.apartmentdesign.models.exceptions;

public class InvalidManufacturerIdException extends RuntimeException{

    public InvalidManufacturerIdException (Long id) {
        super (String.format ("Manufacturer with id %d does not exist",id));
    }

}
