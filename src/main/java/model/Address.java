package model;

import config.HibernateConfig;
import dao.CityDAO;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Set;

@Getter
@Setter
@NoArgsConstructor

@Entity
public class Address {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String street;


    public Address(String street) {
        this.street = street;

    }

    @OneToMany(mappedBy = "address")
    private Set<Person> persons;


    @ManyToOne
    private City city;


    public void addCity(int zip)
    {
        CityDAO cityDAO = new CityDAO();
        City city = cityDAO.getCityByZip(zip);

        if (city != null)
        {
            this.setCity(city);
        } else
        {
            throw new RuntimeException("City does not exist in database");
        }


    }

}


