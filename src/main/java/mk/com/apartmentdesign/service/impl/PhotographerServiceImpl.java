package mk.com.apartmentdesign.service.impl;

import mk.com.apartmentdesign.models.Apartment;
import mk.com.apartmentdesign.models.Photographer;
import mk.com.apartmentdesign.models.exceptions.InvalidManufacturerIdException;
import mk.com.apartmentdesign.models.exceptions.InvalidPhotographerIdException;
import mk.com.apartmentdesign.repository.ApartmentRepository;
import mk.com.apartmentdesign.repository.PhotographerRepository;
import mk.com.apartmentdesign.service.PhotographerService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PhotographerServiceImpl implements PhotographerService {

    private final PhotographerRepository photographerRepository;
    private final ApartmentRepository apartmentRepository;

    public PhotographerServiceImpl (PhotographerRepository photographerRepository, ApartmentRepository apartmentRepository) {
        this.photographerRepository = photographerRepository;
        this.apartmentRepository = apartmentRepository;
    }

    @Override
    public Photographer findById (Long id) {
        return this.photographerRepository.findById (id)
                .orElseThrow (() -> new InvalidPhotographerIdException (id));
    }

    @Override
    public Photographer create (String name, String surname, String biography, List<Long> apartmentIds, String contactByEmail, String contactByPhoneNumber) {
        List<Apartment> apartments = this.apartmentRepository.findAllById (apartmentIds);
        Photographer photographer = new Photographer (name,surname,biography,apartments,contactByEmail,contactByPhoneNumber);
        return this.photographerRepository.save (photographer);
    }

    @Override
    public Photographer update (Long id, String name, String surname, String biography, List<Long> apartmentIds, String contactByEmail, String contactByPhoneNumber) {
        Photographer photographer = this.photographerRepository.findById (id)
                .orElseThrow (() -> new InvalidPhotographerIdException (id));
        List<Apartment> apartments = this.apartmentRepository.findAllById (apartmentIds);
        photographer.setName (name);
        photographer.setSurname (surname);
        photographer.setBiography (biography);
        photographer.setApartments (apartments);
        photographer.setContactByEmail (contactByEmail);
        photographer.setContactByPhoneNumber (contactByPhoneNumber);
        return this.photographerRepository.save (photographer);
    }

    @Override
    public List<Photographer> listAll () {
        return this.photographerRepository.findAll ();
    }
}
