package mk.com.apartmentdesign.web.controller;

import mk.com.apartmentdesign.models.Apartment;
import mk.com.apartmentdesign.models.Designer;
import mk.com.apartmentdesign.models.exceptions.InvalidDesignerIdException;
import mk.com.apartmentdesign.service.ApartmentService;
import mk.com.apartmentdesign.service.DesignerService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.*;

@Controller
@RequestMapping("/designers")
public class DesignerController {

    private final DesignerService designerService;
    private final ApartmentService apartmentService;

    public DesignerController (DesignerService designerService, ApartmentService apartmentService) {
        this.designerService = designerService;
        this.apartmentService = apartmentService;
    }

    @GetMapping()
    public String getDesignerPage(Model model){
        List<Designer> designers = this.designerService.listAll ();
        model.addAttribute ("designers",designers);
        model.addAttribute ("bodyContent","designers");
        return "master-template";
    }

    @GetMapping("/detailed/{id}")
    public String getDetailedDesignerPage(@PathVariable Long id, Model model){
        Designer designer = this.designerService.findById (id)
                .orElseThrow (() -> new InvalidDesignerIdException(id));
        model.addAttribute ("designer",designer);
        model.addAttribute ("bodyContent","designers-detailed");
        return "master-template";
    }
    @GetMapping("/{id}/edit")
    public String editDesignerPage(@PathVariable Long id,Model model){
        if(this.designerService.findById (id).isPresent()){
            Designer designer = this.designerService.findById (id).get();
            model.addAttribute ("designer",designer);
            model.addAttribute ("bodyContent","add-designer");
            return "master-template";
        }
        return "redirect:/apartments?error=ApartmentNotFound";
    }

    @GetMapping("/add-form")
    public String addDesignerPage(Model model){
        List<Designer> designers = this.designerService.listAll ();
        model.addAttribute ("designers",designers);
        model.addAttribute ("bodyContent","add-designer");
        return "master-template";
    }

    @PostMapping
    public String create(@RequestParam String name,
                         @RequestParam String surname,
                         @RequestParam("profilePicture") MultipartFile mainMultipartFile,
                         @RequestParam String biography,
                         @RequestParam String contactEmail,
                         @RequestParam String contactPhoneNumber) throws IOException {
        this.designerService.create (name,surname,mainMultipartFile,biography,contactEmail,contactPhoneNumber);
        return "redirect:/designers";
    }

    @PostMapping("/{id}")
    public String update(@PathVariable Long id,
                         @RequestParam String name,
                         @RequestParam String surname,
                         @RequestParam("profilePicture") MultipartFile mainMultipartFile,
                         @RequestParam String biography,
                         @RequestParam String contactEmail,
                         @RequestParam String contactPhoneNumber) throws IOException {
        this.designerService.update (id,name,surname,mainMultipartFile,biography,contactEmail,contactPhoneNumber);
        return "redirect:/designers";
    }

    @PostMapping("/{id}/delete")
    public String deleteDesigner(@PathVariable Long id){
        this.designerService.delete (id);
        return "redirect:/designers";
    }
}
