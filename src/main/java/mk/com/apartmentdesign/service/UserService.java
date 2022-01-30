package mk.com.apartmentdesign.service;

import mk.com.apartmentdesign.models.Role;
import mk.com.apartmentdesign.models.User;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface UserService extends UserDetailsService {
    User register(String username, String password, String repeatPassword, String name, String surname, String mobileNumber, Role role);
    User login(String username,String password);
}
