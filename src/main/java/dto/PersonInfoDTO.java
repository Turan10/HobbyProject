package dto;


import lombok.Getter;
import lombok.Setter;
import model.Hobby;
import model.Phone;

import java.util.Set;

@Getter
@Setter

public class PersonInfoDTO {
    private String firstName;
    private String lastName;
    private int age;
    private Set<Phone> phoneNumber;
    private String street;
    private int zip;
    private String cityName;
    private String municipalityName;
    private String regionName;
    private Set<Hobby> hobbyName;

    public PersonInfoDTO(String firstName, String lastName, int age, Set<Phone> phoneNumber, String street, int zip, String cityName, String municipalityName, String regionName, Set<Hobby> hobbyName) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.age = age;
        this.phoneNumber = phoneNumber;
        this.street = street;
        this.zip = zip;
        this.cityName = cityName;
        this.municipalityName = municipalityName;
        this.regionName = regionName;
        this.hobbyName = hobbyName;
    }

    @Override
    public String toString() {
        return "PersonInfoDTO{" +
                "firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", age=" + age +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", street='" + street + '\'' +
                ", zip=" + zip +
                ", cityName='" + cityName + '\'' +
                ", municipalityName='" + municipalityName + '\'' +
                ", regionName='" + regionName + '\'' +
                ", hobbyName='" + hobbyName + '\'' +
                '}';
    }
}
