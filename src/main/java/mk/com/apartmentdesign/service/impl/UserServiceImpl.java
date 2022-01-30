package mk.com.apartmentdesign.service.impl;
import mk.com.apartmentdesign.models.Role;
import mk.com.apartmentdesign.models.User;
import mk.com.apartmentdesign.models.exceptions.*;
import mk.com.apartmentdesign.repository.UserRepository;
import mk.com.apartmentdesign.service.UserService;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public UserServiceImpl (UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public User register (String username, String password, String repeatPassword, String name, String surname,String mobileNumber, Role role) {
        if (username==null || username.isEmpty()  || password==null || password.isEmpty())
            throw new InvalidEmailOrPasswordException ();
        if (!password.equals(repeatPassword))
            throw new PasswordDoNotMatchException ();
        if(this.userRepository.findByUsername (username).isPresent())
            throw new UserAlreadyExistsException (username);
        User user = new User(username,passwordEncoder.encode(password),name,surname,mobileNumber,Role.ROLE_USER);
        return userRepository.save(user);
    }

    @Override
    public User login (String username, String password) {
        if (username==null || username.isEmpty () || password==null || password.isEmpty ())
            throw new InvalidArgumentsException ();

        return userRepository.findByUsernameAndPassword (username,password)
                .orElseThrow (() -> new InvalidUserCredentialsException (username));
    }

    @Override
    public UserDetails loadUserByUsername (String username) throws UsernameNotFoundException {
        return userRepository.findByUsername (username)
                .orElseThrow (() -> new UsernameNotFoundException (username));
    }
}
