package mk.com.apartmentdesign.models;

import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

import javax.persistence.*;
import java.util.*;

@Data
@Entity
public class Designer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String surname;
    private String profilePicture;
    @Column(length = 4000)
    private String biography;
    private String contactEmail;
    private String contactPhoneNumber;
    @OneToMany(mappedBy = "designer",fetch = FetchType.EAGER)
    private List<Apartment> apartments;

    public Designer (String name, String surname, String profilePicture,String biography, String contactEmail, String contactPhoneNumber) {
        this.name = name;
        this.surname = surname;
        this.profilePicture = profilePicture;
        this.biography = biography;
        this.contactEmail = contactEmail;
        this.contactPhoneNumber = contactPhoneNumber;
        this.apartments = new ArrayList<> ();
    }

    public Designer () {
    }
}
