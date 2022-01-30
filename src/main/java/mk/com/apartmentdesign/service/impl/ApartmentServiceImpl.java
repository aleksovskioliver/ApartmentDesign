package mk.com.apartmentdesign.service.impl;

import mk.com.apartmentdesign.models.*;
import mk.com.apartmentdesign.models.exceptions.InvalidApartmentIdException;
import mk.com.apartmentdesign.models.exceptions.InvalidDesignerIdException;
import mk.com.apartmentdesign.models.exceptions.InvalidPhotographerIdException;
import mk.com.apartmentdesign.repository.*;
import mk.com.apartmentdesign.service.ApartmentService;
import mk.com.apartmentdesign.web.controller.FileUploadUtil;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Optional;

@Service
public class ApartmentServiceImpl implements ApartmentService {

    private final ApartmentRepository apartmentRepository;
    private final ManufacturerRepository manufacturerRepository;
    private final PhotographerRepository photographerRepository;
    private final DesignerRepository designerRepository;

    public ApartmentServiceImpl (ApartmentRepository apartmentRepository, ManufacturerRepository manufacturerRepository, PhotographerRepository photographerRepository, DesignerRepository designerRepository) {
        this.apartmentRepository = apartmentRepository;
        this.manufacturerRepository = manufacturerRepository;
        this.photographerRepository = photographerRepository;
        this.designerRepository = designerRepository;
    }

    @Override
    public List<Apartment> listAllApartments () {
        return this.apartmentRepository.findAll ();
    }

    @Override
    public Optional<Apartment> findById (Long id) {
        return this.apartmentRepository.findById (id);
    }

    @Override
    public Apartment create (String title,String location, Double area, Integer year, String description,
                             Long photographerId, List<Long> manufacturerId, Long designerId,
                             MultipartFile mainMultipartFile,MultipartFile[] extraMultipleFiles) throws IOException {
        Photographer photographer = this.photographerRepository.findById (photographerId)
                .orElseThrow (() -> new InvalidPhotographerIdException (photographerId));
        List<Manufacturer> manufacturers = this.manufacturerRepository.findAllById (manufacturerId);
        Designer designer = this.designerRepository.findById (designerId)
                .orElseThrow (() -> new InvalidDesignerIdException (designerId));

        String primaryImage = StringUtils.cleanPath (mainMultipartFile.getOriginalFilename ());
        String extraImageName1="";
        String extraImageName2="";
        String extraImageName3="";
        int count = 0;
        for (MultipartFile extraMultipart : extraMultipleFiles){
            String extraImageName = StringUtils.cleanPath (extraMultipart.getOriginalFilename ());

            if (count==0) extraImageName1 = extraImageName;
            if (count==1) extraImageName2 = extraImageName;
            if (count==2) extraImageName3 = extraImageName;
            count++;
        }

        Apartment apartment = new Apartment (title,location,area,year,description,photographer,manufacturers,designer,primaryImage,extraImageName1,extraImageName2,extraImageName3);
        Apartment apart = this.apartmentRepository.save(apartment);
        String uploadDir = "./src/main/resources/static/img/apartments" + apart.getId ();
        FileUploadUtil.saveFile (uploadDir,mainMultipartFile,primaryImage);


        for (MultipartFile extraMultipart : extraMultipleFiles) {
            String fileName = StringUtils.cleanPath (extraMultipart.getOriginalFilename ());

            FileUploadUtil.saveFile (uploadDir,extraMultipart,fileName);
        }
        //return this.apartmentRepository.save(apartment);
        return apart;
    }

    @Override
    public Apartment update (Long id,String title, String location, Double area,
                             Integer year, String description, Long photographerId,
                             List<Long> manufacturerId,Long designerId,
                             MultipartFile mainMultipartFile,MultipartFile[] extraMultipleFiles) throws IOException {
        Apartment apartment = this.apartmentRepository.findById (id)
                .orElseThrow (() -> new InvalidApartmentIdException (id));
        Photographer photographer = this.photographerRepository.findById (photographerId)
                .orElseThrow (() -> new InvalidPhotographerIdException (id));
        List<Manufacturer> manufacturers = this.manufacturerRepository.findAllById (manufacturerId);
        Designer designer = this.designerRepository.findById (designerId)
                .orElseThrow (() -> new InvalidDesignerIdException (id));

        String primaryImage = StringUtils.cleanPath (mainMultipartFile.getOriginalFilename ());
        String extraImageName1="";
        String extraImageName2="";
        String extraImageName3="";
        int count = 0;
        for (MultipartFile extraMultipart : extraMultipleFiles){
            String extraImageName = StringUtils.cleanPath (extraMultipart.getOriginalFilename ());

            if (count==0) extraImageName1 = extraImageName;
            if (count==1) extraImageName2 = extraImageName;
            if (count==2) extraImageName3 = extraImageName;
            count++;
        }
        apartment.setPrimaryImage (primaryImage);
        apartment.setExtraImage1 (extraImageName1);
        apartment.setExtraImage2 (extraImageName2);
        apartment.setExtraImage3 (extraImageName3);
        apartment.setTitle (title);
        apartment.setLocation (location);
        apartment.setArea (area);
        apartment.setYear (year);
        apartment.setDescription (description);
        apartment.setPhotographer (photographer);
        apartment.setManufacturer (manufacturers);
        apartment.setDesigner (designer);
        Apartment apart = this.apartmentRepository.save (apartment);


        String uploadDir = "./src/main/resources/static/img/apartments" + apart.getId ();
        FileUploadUtil.saveFile (uploadDir,mainMultipartFile,primaryImage);
        for (MultipartFile extraMultipart : extraMultipleFiles) {
            String fileName = StringUtils.cleanPath (extraMultipart.getOriginalFilename ());

            FileUploadUtil.saveFile (uploadDir,extraMultipart,fileName);
        }

        return apart;
    }

    @Override
    public Apartment delete (Long id) {
        Apartment apartment = this.apartmentRepository.findById (id)
                .orElseThrow (() -> new InvalidApartmentIdException (id));
        this.apartmentRepository.delete (apartment);
        return apartment;
    }

    @Override
    public List<Apartment> findAllByIds (List<Long> apartmentsId) {
        return this.apartmentRepository.findAllById (apartmentsId);
    }
}
