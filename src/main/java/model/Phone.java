package model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor

@Entity
public class Phone {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String number;


    public Phone(String number) {
        this.number = number;
    }

    @ManyToOne
    private Person person;


   public boolean validatePhoneNumber() {
        String pattern = "^\\+45\\d{8}$";
        return this.number.matches(pattern);
    }

}