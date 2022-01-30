package mk.com.apartmentdesign.service;

import mk.com.apartmentdesign.models.Apartment;
import mk.com.apartmentdesign.models.Manufacturer;
import java.util.*;
public interface ManufacturerService {
    Manufacturer findById(Long id);
    List<Manufacturer> findAll();
    Manufacturer create(String nameCompany,String address, List<Long> apartmentsId);
    Manufacturer update(Long id,String nameCompany,String address, List<Long> apartmentsId);
}
