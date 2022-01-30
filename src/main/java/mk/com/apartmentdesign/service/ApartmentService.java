package mk.com.apartmentdesign.service;

import mk.com.apartmentdesign.models.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.*;

public interface ApartmentService {

    List<Apartment> listAllApartments();
    Optional<Apartment> findById(Long id);
    Apartment create (String title,String location, Double area, Integer year, String description,
                      Long photographerId, List<Long> manufacturerId, Long designerId,
                      MultipartFile mainMultipartFile,MultipartFile[] extraMultipleFiles) throws IOException;
    Apartment update(Long id,String title,String location, Double area,
                     Integer year, String description, Long photographerId,
                     List<Long> manufacturerId,Long designerId,
                     MultipartFile mainMultipartFile,MultipartFile[] extraMultipleFiles) throws IOException;
    Apartment delete(Long id);
    List<Apartment> findAllByIds(List<Long> apartmentsId);
}
