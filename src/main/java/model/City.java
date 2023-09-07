package model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor

@Entity
@Table(name = "zipcode")
public class City {
    @Id
    private int zip;
    @Column(name = "city_name")
    private String cityName;
    @Column(name = "region_name")
    private String regionName;
    @Column(name = "municipality_name")
    private String municipalityName;


    public City(int zip, String cityName, String regionName, String municipalityName) {
        this.zip = zip;
        this.cityName = cityName;
        this.regionName = regionName;
        this.municipalityName = municipalityName;
    }
}
