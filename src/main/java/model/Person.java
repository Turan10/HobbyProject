package model;

import dao.CityDAO;
import jakarta.persistence.*;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
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
    private LocalDate lastEdited;



    public Person(String firstName, String lastName, int age) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.age = age;
        lastEdited = LocalDate.now();
    }

    @PreUpdate
    public void setLastEdited() {
        lastEdited = LocalDate.now();
    }

    @ManyToMany(mappedBy = "persons", cascade = CascadeType.PERSIST)
    private Set<Hobby> hobbies = new HashSet<>();


    @ManyToOne(cascade = CascadeType.PERSIST)
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

    public void addPhone(Phone phone) {
        if (validatePhoneNumber(phone)) {
            phones.add(phone);
            phone.setPerson(this);
        } else {
            System.out.println("Invalid phone number");
        }
    }

    private boolean validatePhoneNumber(Phone number) {
        String pattern = "^\\+45\\d{8}$";
        return number.getNumber().matches(pattern);
    }


}
