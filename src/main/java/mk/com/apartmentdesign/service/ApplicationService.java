package mk.com.apartmentdesign.service;

import mk.com.apartmentdesign.models.Application;
import mk.com.apartmentdesign.models.User;
import org.springframework.web.multipart.MultipartFile;

import java.util.*;

public interface ApplicationService {
    Application create(String username, String area, String description,
                       List<Long> apartmentsIds, List<Long> designersId);
    Application deleteById(Long id);
    List<Application> findAllByUsername(String username);//site app za daden user
    Application findById(Long id);
    List<Application> findAll();


}