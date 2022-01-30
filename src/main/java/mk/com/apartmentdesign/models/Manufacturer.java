package mk.com.apartmentdesign.models;
import lombok.Data;

import javax.persistence.*;
import java.util.*;

@Data
@Entity
public class Manufacturer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nameCompany;
    private String address;

    @ManyToMany
    List<Apartment> apartments;

    public Manufacturer (String nameCompany,String address,List<Apartment> apartments) {
        this.nameCompany = nameCompany;
        this.address = address;
        this.apartments = apartments;
    }

    public Manufacturer () {
    }
}
