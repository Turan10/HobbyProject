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

@Entity
public class Person {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String firstName;
    private String lastName;
    private int age;


    public Person(String firstName, String lastName, int age) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.age = age;
    }

    @ManyToMany(cascade = CascadeType.PERSIST)
    private Set<Hobby> hobbies = new HashSet<>();


    @ManyToOne(cascade = CascadeType.PERSIST)
    private Adress address;

    @OneToMany(mappedBy = "person")
    private Set<Phone> phones = new HashSet<>();

}
