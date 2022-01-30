package mk.com.apartmentdesign.service.impl;

import mk.com.apartmentdesign.models.Apartment;
import mk.com.apartmentdesign.models.Manufacturer;
import mk.com.apartmentdesign.models.exceptions.InvalidManufacturerIdException;
import mk.com.apartmentdesign.repository.ApartmentRepository;
import mk.com.apartmentdesign.repository.ManufacturerRepository;
import mk.com.apartmentdesign.service.ManufacturerService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ManufacturerServiceImpl implements ManufacturerService {

    private final ManufacturerRepository manufacturerRepository;
    private final ApartmentRepository apartmentRepository;

    public ManufacturerServiceImpl (ManufacturerRepository manufacturerRepository, ApartmentRepository apartmentRepository) {
        this.manufacturerRepository = manufacturerRepository;
        this.apartmentRepository = apartmentRepository;
    }

    @Override
    public Manufacturer findById (Long id) {
        return this.manufacturerRepository.findById (id)
                .orElseThrow (() -> new InvalidManufacturerIdException (id));
    }

    @Override
    public List<Manufacturer> findAll () {
        return this.manufacturerRepository.findAll ();
    }

    @Override
    public Manufacturer create (String nameCompany, String address, List<Long> apartmentsId) {
        List<Apartment> apartments = this.apartmentRepository.findAllById (apartmentsId);
        Manufacturer manufacturer = new Manufacturer (nameCompany,address,apartments);
        return this.manufacturerRepository.save (manufacturer);
    }

    @Override
    public Manufacturer update (Long id, String nameCompany, String address, List<Long> apartmentsId) {
       Manufacturer manufacturer = this.manufacturerRepository.findById (id)
               .orElseThrow (() -> new InvalidManufacturerIdException (id));
        List<Apartment> apartments = this.apartmentRepository.findAllById (apartmentsId);
        manufacturer.setNameCompany (nameCompany);
        manufacturer.setAddress (address);
        manufacturer.setApartments (apartments);
        return this.manufacturerRepository.save (manufacturer);
    }
}
