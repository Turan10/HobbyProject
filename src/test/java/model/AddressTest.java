package model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.HashSet;

class AddressTest {

    private Address address;

    @BeforeEach
    void setUp() {
        address = new Address();
    }

    @Test
    void testNoArgsConstructor() {
        assertNotNull(address);
        assertEquals(0, address.getId());
        assertEquals(null, address.getStreet());
        assertEquals(null, address.getPersons());
        assertEquals(null, address.getCity());
    }

    @Test
    void testIdSetterAndGetter() {
        address.setId(1);
        assertEquals(1, address.getId());
    }

    @Test
    void testStreetSetterAndGetter() {
        address.setStreet("123 Main St");
        assertEquals("123 Main St", address.getStreet());
    }

    @Test
    void testPersonsSetterAndGetter() {
        HashSet<Person> persons = new HashSet<>();
        persons.add(new Person());
        address.setPersons(persons);
        assertEquals(persons, address.getPersons());
    }

    @Test
    void testCitySetterAndGetter() {
        City city = new City();
        address.setCity(city);
        assertEquals(city, address.getCity());
    }

    @Test
    void testConstructorWithStreet() {
        Address anotherAddress = new Address("123 Main St");
        assertNotNull(anotherAddress);
        assertEquals("123 Main St", anotherAddress.getStreet());
    }
}