package mk.com.apartmentdesign.models;

import lombok.Data;
import mk.com.apartmentdesign.models.enumerations.ApplicationStatus;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.*;

@Data
@Entity
public class Application {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    private User user;
    private String area;
    @Column(length = 4000)
    private String description;
    private LocalDateTime dateCreated;

    @Enumerated(EnumType.STRING)
    private ApplicationStatus status;

    @ManyToMany
    private List<Apartment> exampleApartments;

    @ManyToMany
    private List<Designer> designers;

    public Application(){}
    public Application(User user,String area,String description,List<Apartment> exampleApartments,List<Designer> designers){
        this.dateCreated = LocalDateTime.now ();
        this.user = user;
        this.area = area;
        this.description = description;
        this.exampleApartments = exampleApartments;
        this.designers = designers;
        this.status = ApplicationStatus.CREATED;
    }

}
