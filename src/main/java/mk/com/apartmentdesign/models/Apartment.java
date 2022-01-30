package mk.com.apartmentdesign.models;

import lombok.Data;

import javax.persistence.*;
import java.util.*;

@Data
@Entity
public class Apartment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(length = 500)
    private String title;
    private String location;
    private Double area;
    private Integer year;
    @Column(length = 4000)
    private String description;

    @Column(name = "primary_image")
    private String primaryImage;
    @Column(name = "extra_image1")
    private String extraImage1;
    @Column(name = "extra_image2")
    private String extraImage2;
    @Column(name = "extra_image3")
    private String extraImage3;

    @ManyToOne
    private Photographer photographer;
    @ManyToMany
    private List<Manufacturer> manufacturer;
    @ManyToOne
    private Designer designer;

    public Apartment (String title,String location, Double area, Integer year,
                      String description, Photographer photographer,
                      List<Manufacturer> manufacturer, Designer designer,
                      String primaryImage,String extraImage1,String extraImage2,String extraImage3) {
        this.title = title;
        this.location = location;
        this.area = area;
        this.year = year;
        this.description = description;
        this.photographer = photographer;
        this.manufacturer = manufacturer;
        this.designer = designer;
        this.primaryImage = primaryImage;
        this.extraImage1=extraImage1;
        this.extraImage2=extraImage2;
        this.extraImage3 = extraImage3;
    }

    public Apartment () {
    }

    public List<Manufacturer> getManufacturer () {
        return manufacturer;
    }
}
