package mk.com.apartmentdesign.service.impl;

import mk.com.apartmentdesign.models.Apartment;
import mk.com.apartmentdesign.models.Application;
import mk.com.apartmentdesign.models.Designer;
import mk.com.apartmentdesign.models.User;
import mk.com.apartmentdesign.models.exceptions.InvalidApplicationIdException;
import mk.com.apartmentdesign.models.exceptions.InvalidUsernameException;
import mk.com.apartmentdesign.repository.ApartmentRepository;
import mk.com.apartmentdesign.repository.ApplicationRepository;
import mk.com.apartmentdesign.repository.DesignerRepository;
import mk.com.apartmentdesign.repository.UserRepository;
import mk.com.apartmentdesign.service.ApplicationService;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Service
public class ApplicationServiceImpl implements ApplicationService {

    private final ApplicationRepository applicationRepository;
    private final UserRepository userRepository;
    private final ApartmentRepository apartmentRepository;
    private final DesignerRepository designerRepository;

    public ApplicationServiceImpl (ApplicationRepository applicationRepository, UserRepository userRepository, ApartmentRepository apartmentRepository, DesignerRepository designerRepository) {
        this.applicationRepository = applicationRepository;
        this.userRepository = userRepository;
        this.apartmentRepository = apartmentRepository;
        this.designerRepository = designerRepository;
    }

    @Override
    public Application findById (Long id) {
        return this.applicationRepository.findById (id)
                .orElseThrow (() -> new InvalidApplicationIdException (id));
    }

    @Override
    public List<Application> findAll () {
        return this.applicationRepository.findAll ();
    }

    @Override
    public Application create (String username, String area, String description,
                               List<Long> apartmentsId, List<Long> designerIds) {
        User user = this.userRepository.findByUsername (username)
                        .orElseThrow (() -> new InvalidUsernameException (username));
        List<Apartment> apartments = this.apartmentRepository.findAllById (apartmentsId);
        List<Designer> designers = this.designerRepository.findAllById (designerIds);
        Application application = new Application (user,area,description,apartments,designers);
        return this.applicationRepository.save (application);
    }

    @Override
    public Application deleteById (Long id) {
        Application application = this.applicationRepository.findById (id)
                .orElseThrow (() -> new InvalidApplicationIdException (id));
        this.applicationRepository.delete (application);
        return application;

    }

    @Override
    public List<Application> findAllByUsername (String username) {
        User user = this.userRepository.findByUsername (username)
                .orElseThrow (() -> new InvalidUsernameException (username));
        return this.applicationRepository
                .findAllByUser (user);
    }
}
