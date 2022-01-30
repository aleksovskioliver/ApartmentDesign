package mk.com.apartmentdesign.web.controller;

import mk.com.apartmentdesign.models.Role;
import mk.com.apartmentdesign.models.exceptions.InvalidArgumentsException;
import mk.com.apartmentdesign.models.exceptions.PasswordDoNotMatchException;
import mk.com.apartmentdesign.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/register")
public class RegisterController {

    private final UserService userService;

    public RegisterController (UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public String getRegisterPage(@RequestParam(required = false) String error,
                                  Model model){
        if(error != null && !error.isEmpty()) {
            model.addAttribute("hasError", true);
            model.addAttribute("error", error);
        }
        model.addAttribute ("bodyContent","register");
        return "master-template";
    }

    @PostMapping
    public String register(@RequestParam String username,
                           @RequestParam String password,
                           @RequestParam String repeatedPassword,
                           @RequestParam String name,
                           @RequestParam String surname,
                           @RequestParam String mobileNumber){
        try {
            this.userService.register (username,password,repeatedPassword,name,surname,mobileNumber, Role.ROLE_USER);
            return "redirect:/login";
        }catch (InvalidArgumentsException | PasswordDoNotMatchException exception){
            return "redirect:/register?error=" + exception.getMessage ();
        }
    }
}