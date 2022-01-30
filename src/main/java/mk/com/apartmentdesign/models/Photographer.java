package mk.com.apartmentdesign.models;

import lombok.Data;

import javax.persistence.*;
import java.util.*;

@Data
@Entity
public class Photographer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String surname;
    @Column(length = 4000)
    private String biography;
    private String contactByEmail;
    private String contactByPhoneNumber;

    @OneToMany(mappedBy = "photographer",fetch = FetchType.EAGER)
    private List<Apartment> apartments;

    public Photographer(){}

    public Photographer (String name, String surname,
                         String biography, List<Apartment> apartments,
                         String contactByEmail,String contactByPhoneNumber) {
        this.name = name;
        this.surname = surname;
        this.biography = biography;
        this.apartments = apartments;
        this.contactByEmail = contactByEmail;
        this.contactByPhoneNumber = contactByPhoneNumber;
    }
}
