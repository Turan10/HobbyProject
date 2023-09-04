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
public class Hobby {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String name;


    @ManyToMany(mappedBy = "hobbies")
    private Set<Person> persons = new HashSet<>();

    @ManyToOne(cascade = CascadeType.PERSIST)
    private Type type;

    public Hobby(String name) {
        this.name = name;
    }
}
