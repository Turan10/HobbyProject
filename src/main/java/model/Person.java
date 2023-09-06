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
@Table(name = "persons_temp")
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

    @ManyToMany(mappedBy = "persons", cascade = CascadeType.PERSIST)
    private Set<Hobby> hobbies = new HashSet<>();


    @ManyToOne
    private Address address;

    @OneToMany(mappedBy = "person", cascade=CascadeType.ALL)
    private Set<Phone> phones = new HashSet<>();

    public void addHobby(Hobby hobby){
        hobbies.add(hobby);
        if(hobby != null)
        {
            hobby.getPersons().add(this);
        }
    }

    public void addPhone(Phone phone)
    {
        phones.add(phone);
        if (phone != null)
        {
            phone.setPerson(this);
        }
    }

}
