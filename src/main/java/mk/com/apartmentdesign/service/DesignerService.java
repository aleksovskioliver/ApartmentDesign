package mk.com.apartmentdesign.service;

import mk.com.apartmentdesign.models.Apartment;
import mk.com.apartmentdesign.models.Designer;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

public interface DesignerService {

    List<Designer> listAll();
    Optional<Designer> findById(Long id);
    Designer delete(Long id);
    Designer create(String name, String surname, MultipartFile mainMultipartFile,
                    String biography, String contactEmail, String contactPhoneNumber) throws IOException;
    Designer update(Long id,String name, String surname,MultipartFile mainMultipartFile,
                    String biography, String contactEmail, String contactPhoneNumber) throws IOException;
    List<Designer> findAllById(List<Long> designersId);
}

