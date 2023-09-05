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
public class Adress {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String street;



    public Adress(String street) {
        this.street = street;

    }

    @OneToMany(mappedBy = "address")
    private Set<Person> persons;


    @ManyToOne
    private City city;

}

