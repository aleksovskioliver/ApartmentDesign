package mk.com.apartmentdesign.web.controller;

import mk.com.apartmentdesign.models.*;
import mk.com.apartmentdesign.service.ApartmentService;
import mk.com.apartmentdesign.service.DesignerService;
import mk.com.apartmentdesign.service.ManufacturerService;
import mk.com.apartmentdesign.service.PhotographerService;
import org.eclipse.jetty.util.StringUtil;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.*;

@Controller
@RequestMapping("/apartments")
public class ApartmentController {

    private final ApartmentService apartmentService;
    private final ManufacturerService manufacturerService;
    private final DesignerService designerService;
    private final PhotographerService photographerService;

    public ApartmentController (ApartmentService apartmentService, ManufacturerService manufacturerService, DesignerService designerService, PhotographerService photographerService) {
        this.apartmentService = apartmentService;
        this.manufacturerService = manufacturerService;
        this.designerService = designerService;
        this.photographerService = photographerService;
    }

    @GetMapping
    public String getApartmentsPage(Model model){
        List<Apartment> apartments = this.apartmentService.listAllApartments ();
        model.addAttribute ("apartments",apartments);
        model.addAttribute ("bodyContent","apartments");
        return "master-template";
    }

    @GetMapping("/detailed/{id}")
    public String getDetailedApartment(@PathVariable Long id,
                                       Model model){
        Apartment apartment = this.apartmentService.findById (id).get ();
        model.addAttribute ("apartment",apartment);
        model.addAttribute ("bodyContent","apartment-detailed");
        return "master-template";
    }

    @GetMapping("/{id}/edit")
    public String editApartmentPage(@PathVariable Long id,Model model){
        if(this.apartmentService.findById (id).isPresent()){
            Apartment apartment = this.apartmentService.findById (id).get ();
            List<Manufacturer> manufacturers = this.manufacturerService.findAll ();
            List<Designer> designers = this.designerService.listAll ();
            List<Photographer> photographers = this.photographerService.listAll ();
            model.addAttribute ("apartment",apartment);
            model.addAttribute ("manufacturers",manufacturers);
            model.addAttribute ("designers",designers);
            model.addAttribute ("photographers",photographers);
            model.addAttribute ("bodyContent","add-apartment");
            return "master-template";
        }
        return "redirect:/apartments?error=ApartmentNotFound";
    }

    @GetMapping("/add-form")
    public String addApartmentPage(Model model){
        List<Manufacturer> manufacturers = this.manufacturerService.findAll ();
        List<Designer> designers = this.designerService.listAll ();
        List<Photographer> photographers = this.photographerService.listAll ();
        model.addAttribute ("manufacturers",manufacturers);
        model.addAttribute ("designers",designers);
        model.addAttribute ("photographers",photographers);
        model.addAttribute ("bodyContent","add-apartment");
        return "master-template";
    }

    @PostMapping
    public String create(@RequestParam String title,
                         @RequestParam String location,
                         @RequestParam Double area,
                         @RequestParam Integer year,
                         @RequestParam String description,
                         @RequestParam Long photographerId,
                         @RequestParam List<Long> manufacturerId,
                         @RequestParam Long designerId,
                         @RequestParam("primaryImage") MultipartFile mainMultipartFile,
                         @RequestParam("extraImage") MultipartFile[] extraMultipartFiles) throws IOException {

        this.apartmentService.create (title,location,area,year,description,photographerId,manufacturerId,designerId,mainMultipartFile,extraMultipartFiles);
        return "redirect:/apartments";
    }

    @PostMapping("/{id}")
    public String update(@PathVariable Long id,
                         @RequestParam String title,
                         @RequestParam String location,
                         @RequestParam Double area,
                         @RequestParam Integer year,
                         @RequestParam String description,
                         @RequestParam Long photographerId,
                         @RequestParam List<Long> manufacturerId,
                         @RequestParam Long designerId,
                         @RequestParam("primaryImage") MultipartFile mainMultipartFile,
                         @RequestParam("extraImage") MultipartFile[] extraMultipartFiles) throws IOException {
        this.apartmentService.update (id,title,location,area,year,description,photographerId,manufacturerId,designerId,mainMultipartFile,extraMultipartFiles);
        return "redirect:/apartments";
    }

    @PostMapping("/{id}/delete")
    public String deleteApartment(@PathVariable Long id){
        this.apartmentService.delete (id);
        return "redirect:/apartments";
    }

}
