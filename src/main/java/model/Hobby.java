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
@ToString
@NoArgsConstructor
@Entity

public class Hobby {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String wikilink;

    @Column(nullable = false)
    private String category;

    @ManyToOne
    private Type type;

    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.MERGE)
    private Set<Person> persons = new HashSet<>();


    public Hobby(String name, String wikilink, String category, String type) {
        this.name = name;
        this.wikilink = wikilink;
        this.category = category;
        this.type = new Type(type);
    }
}
