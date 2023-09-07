package model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@ToString

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


    public City(int zip, String cityName, String regionName, String municipalityName)
    {
        this.zip = zip;
        this.cityName = cityName;
        this.regionName = regionName;
        this.municipalityName = municipalityName;
    }


    @OneToMany(fetch = FetchType.EAGER,mappedBy = "city", cascade = CascadeType.MERGE)
    Set<Address> addresses = new HashSet<>();

}
