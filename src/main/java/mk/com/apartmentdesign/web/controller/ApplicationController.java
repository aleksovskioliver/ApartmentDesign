package mk.com.apartmentdesign.web.controller;

import mk.com.apartmentdesign.models.Apartment;
import mk.com.apartmentdesign.models.Application;
import mk.com.apartmentdesign.models.Designer;
import mk.com.apartmentdesign.models.User;
import mk.com.apartmentdesign.service.ApartmentService;
import mk.com.apartmentdesign.service.ApplicationService;
import mk.com.apartmentdesign.service.DesignerService;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.*;

@Controller
@RequestMapping("/applications")
public class ApplicationController {

    private final ApplicationService applicationService;
    private final ApartmentService apartmentService;
    private final DesignerService designerService;

    public ApplicationController (ApplicationService applicationService, ApartmentService apartmentService, DesignerService designerService) {
        this.applicationService = applicationService;
        this.apartmentService = apartmentService;
        this.designerService = designerService;
    }

    @GetMapping
    public String getApplicationPage(@RequestParam(required = false) String error,
                                     HttpServletRequest req,
                                     Model model){
        if (error!=null && !error.isEmpty ()){
            model.addAttribute ("hasError",true);
            model.addAttribute ("error",error);
        }
        String username = req.getRemoteUser ();
        List<Application> applications = this.applicationService.findAllByUsername (username);
        List<Application> adminapp = this.applicationService.findAll ();
        model.addAttribute ("adminapp",adminapp);
        model.addAttribute ("applications",applications);
        model.addAttribute ("bodyContent","applications");
        return "master-template";
    }

    @GetMapping("/add-form")
    public String addApplicationPage(Model model){
        List<Apartment> apartments = this.apartmentService.listAllApartments ();
        List<Designer> designers = this.designerService.listAll ();
        model.addAttribute ("apartments",apartments);
        model.addAttribute ("designers",designers);
        model.addAttribute ("bodyContent","add-application");
        return "master-template";
    }

    @PostMapping("/add")
    public String create(@RequestParam String area,
                         @RequestParam String description,
                         @RequestParam(required = false) List<Long> exampleApartmentsId,
                         @RequestParam(required = false) List<Long> designersId,
                         HttpServletRequest req,
                         Authentication authentication){
        try {
            User user = (User) authentication.getPrincipal ();
            List<Apartment> apartments = this.apartmentService.findAllByIds (exampleApartmentsId);
            List<Designer> designers = this.designerService.findAllById (designersId);
            this.applicationService.create (user.getUsername (),area,description,exampleApartmentsId,designersId);
            return "redirect:/applications";
        }catch (RuntimeException exception){
            return "redirect:/applications?error=" + exception.getMessage ();
        }
    }

    @PostMapping("/delete/{id}")
    public String deleteApplication(@PathVariable Long id){
        this.applicationService.deleteById (id);
        return "redirect:/applications";
    }

}
