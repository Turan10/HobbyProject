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
public class Type {
    @Id
    private String name;


    public Type(String name) {
        this.name = name;
    }


    @OneToMany(mappedBy = "type")
    private Set<Hobby> hobbies = new HashSet<>();
}
