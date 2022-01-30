package mk.com.apartmentdesign.models;

import lombok.Data;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;



@Data
@Entity
@Table(name = "clients")
public class User implements UserDetails {

    @Id
    private String username;
    private String password;
    private String name;
    private String surname;
    private String mobileNumber;
    @Enumerated(value = EnumType.STRING)
    private Role role;
    @OneToMany(mappedBy = "user",fetch = FetchType.EAGER)
    private List<Application> applications;

    private boolean isAccountNonExpired = true;
    private boolean isAccountNonLocked = true;
    private boolean isCredentialsNonExpired = true;
    private boolean isEnabled = true;

    public User(){}

    public User (String username, String password, String name, String surname, String mobileNumber, Role role) {
        this.username = username;
        this.password = password;
        this.name = name;
        this.surname = surname;
        this.mobileNumber = mobileNumber;
        this.role = role;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities () {
       return Collections.singletonList (role);
    }

    @Override
    public String getUsername () {
        return username;
    }

    @Override
    public boolean isAccountNonExpired () {
        return isAccountNonExpired;
    }

    @Override
    public boolean isAccountNonLocked () {
        return isAccountNonLocked;
    }

    @Override
    public boolean isCredentialsNonExpired () {
        return isCredentialsNonExpired;
    }

    @Override
    public boolean isEnabled () {
        return isEnabled;
    }
}
