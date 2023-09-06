package model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.HashSet;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class PersonTest {

    private Person person;

    @BeforeEach
    public void setUp() {
        person = new Person("Metin", "Akbas", 22);
    }

    @Test
    public void testConstructor() {
        assertEquals("Metin", person.getFirstName());
        assertEquals("Akbas", person.getLastName());
        assertEquals(22, person.getAge());
    }

    @Test
    public void testSettersAndGetters() {
        person.setFirstName("Jeanette");
        person.setLastName("Jensen");
        person.setAge(30);

        assertEquals("Jeanette", person.getFirstName());
        assertEquals("Jensen", person.getLastName());
        assertEquals(30, person.getAge());
    }

    @Test
    public void testAddHobby() {
        Hobby hobby = new Hobby();
        hobby.setName("Football");
        person.addHobby(hobby);

        assertTrue(person.getHobbies().contains(hobby));
        assertTrue(hobby.getPersons().contains(person));
    }

    @Test
    public void testAddPhone() {
        Phone phone = new Phone();
        phone.setNumber("+4512345678");
        person.addPhone(phone);

        assertTrue(person.getPhones().contains(phone));
        assertEquals(person, phone.getPerson());
    }

    @Test
    public void testAddress() {
        Address address = new Address();
        address.setStreet("Landemærket 11");
        person.setAddress(address);

        assertEquals(address, person.getAddress());
    }
}