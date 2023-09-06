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

<<<<<<< Updated upstream:src/main/java/model/Adress.java


    public Adress(String street) {
=======
    public Address(String street) {
>>>>>>> Stashed changes:src/main/java/model/Address.java
        this.street = street;

    }

    @OneToMany(mappedBy = "address")
    private Set<Person> persons;


    @ManyToOne(cascade = CascadeType.PERSIST)
    private City city;

}

