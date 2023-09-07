package model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "hobbies_temp")
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

    @Override
    public String toString() {
        return "Hobby{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", wikilink='" + wikilink + '\'' +
                ", category='" + category + '\'' +
                ", type=" + type +
                ", persons=" + persons +
                '}';
    }
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Hobby hobby = (Hobby) o;
        return Objects.equals(id, hobby.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
