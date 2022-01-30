package mk.com.apartmentdesign.service;

import mk.com.apartmentdesign.models.Apartment;
import mk.com.apartmentdesign.models.Photographer;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface PhotographerService{
    Photographer findById(Long id);
    Photographer create(String name, String surname,
                                  String biography, List<Long> apartmentIds,
                                  String contactByEmail,String contactByPhoneNumber);
    Photographer update(Long id,String name, String surname,
                        String biography, List<Long> apartmentIds,
                        String contactByEmail,String contactByPhoneNumber);
    List<Photographer> listAll();
}
