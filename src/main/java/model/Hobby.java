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
    private String wikiLink;
    private String category;



    @ManyToMany(mappedBy = "hobbies")
    private Set<Person> persons = new HashSet<>();

    @JoinColumn(name = "type_id")
    @ManyToOne(cascade = CascadeType.PERSIST)
    private Type type;

    public Hobby(String name, String wikiLink, String category) {
        this.name = name;
        this.wikiLink = wikiLink;
        this.category = category;
    }
}
