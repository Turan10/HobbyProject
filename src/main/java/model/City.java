package model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor

@Entity
public class City {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private int zip;
    private String cityName;


    public City(int zip, String cityName) {
        this.zip = zip;
        this.cityName = cityName;
    }

    @OneToMany(mappedBy = "city")
    private Set<Adress> addresses = new HashSet<>();
}
