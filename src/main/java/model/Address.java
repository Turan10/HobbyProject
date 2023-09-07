package model;

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

}