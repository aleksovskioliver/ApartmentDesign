package mk.com.apartmentdesign.web.controller;

import mk.com.apartmentdesign.models.User;
import mk.com.apartmentdesign.models.exceptions.InvalidUserCredentialsException;
import mk.com.apartmentdesign.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;

@Controller
@RequestMapping("/login")
public class LoginController {

    private final UserService userService;


    public LoginController (UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public String getLoginPage(Model model){
        model.addAttribute ("bodyContent","login");
        return "master-template";
    }

    @PostMapping
    public String  login(HttpServletRequest request,Model model){
        User user = null;
        try {
            user = this.userService.login (request.getParameter ("username"),
                    request.getParameter ("password"));
            request.getSession ().setAttribute ("user",user);
            return "redirect:/home";
        }
        catch (InvalidUserCredentialsException exception) {
            model.addAttribute("hasError", true);
            model.addAttribute("error", exception.getMessage());
            model.addAttribute ("bodyContent","login");
            return "master-template";
        }
    }



}
