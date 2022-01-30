package mk.com.apartmentdesign.service.impl;

import mk.com.apartmentdesign.models.Designer;
import mk.com.apartmentdesign.models.exceptions.InvalidDesignerIdException;
import mk.com.apartmentdesign.repository.ApartmentRepository;
import mk.com.apartmentdesign.repository.DesignerRepository;
import mk.com.apartmentdesign.service.DesignerService;
import mk.com.apartmentdesign.web.controller.FileUploadUtil;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

@Service
public class DesignerServiceImpl implements DesignerService {

    private final DesignerRepository designerRepository;
    private final ApartmentRepository apartmentRepository;

    public DesignerServiceImpl (DesignerRepository designerRepository, ApartmentRepository apartmentRepository) {
        this.designerRepository = designerRepository;
        this.apartmentRepository = apartmentRepository;
    }

    @Override
    public List<Designer> listAll () {
        return this.designerRepository.findAll ();
    }

    @Override
    public Optional<Designer> findById (Long id) {
        return this.designerRepository.findById (id);
    }

    @Override
    public Designer delete (Long id) {
        Designer designer = this.designerRepository.findById (id)
                .orElseThrow (() -> new InvalidDesignerIdException(id));
        this.designerRepository.delete (designer);
        return designer;

    }

    @Override
    public Designer create (String name, String surname, MultipartFile mainMultipartFile,
                            String biography, String contactEmail, String contactPhoneNumber) throws IOException {
        String profilePicture = StringUtils.cleanPath (mainMultipartFile.getOriginalFilename ());
        Designer designer = new Designer (name,surname,profilePicture,biography,contactEmail,contactPhoneNumber);
        Designer desgn = this.designerRepository.save (designer);
        String uploadDir = "./src/main/resources/static/img/designers" + desgn.getId ();
        FileUploadUtil.saveFile (uploadDir,mainMultipartFile,profilePicture);
        return desgn;
    }

    @Override
    public Designer update (Long id, String name, String surname,MultipartFile mainMultipartFile,
                            String biography, String contactEmail, String contactPhoneNumber) throws IOException {
        Designer designer = this.designerRepository.findById (id)
                .orElseThrow (() -> new InvalidDesignerIdException(id));
        String profilePicture = StringUtils.cleanPath (mainMultipartFile.getOriginalFilename ());
        designer.setProfilePicture (profilePicture);
        designer.setName (name);
        designer.setSurname (surname);
        designer.setBiography (biography);
        designer.setContactEmail (contactEmail);
        designer.setContactPhoneNumber (contactPhoneNumber);
        Designer dsg = designerRepository.save (designer);
        String uploadDir = "./src/main/resources/static/img/designers" + dsg.getId ();
        FileUploadUtil.saveFile (uploadDir,mainMultipartFile,profilePicture);

        return dsg;
    }

    @Override
    public List<Designer> findAllById (List<Long> designersId) {
        return this.designerRepository.findAllById (designersId);
    }
}
