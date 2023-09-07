package model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class PhoneTest {

    private Phone phone;

    @BeforeEach
    void setUp() {
        phone = new Phone();
    }

    @Test
    void testNoArgsConstructor() {
        assertNotNull(phone);
        assertEquals(0, phone.getId());
        assertEquals(null, phone.getNumber());
        assertEquals(null, phone.getPerson());
    }

    @Test
    void testIdSetterAndGetter() {
        phone.setId(1);
        assertEquals(1, phone.getId());
    }

    @Test
    void testNumberSetterAndGetter() {
        phone.setNumber("+4512345678");
        assertEquals("+4512345678", phone.getNumber());
    }

    @Test
    void testPersonSetterAndGetter() {
        Person person = new Person();
        phone.setPerson(person);
        assertEquals(person, phone.getPerson());
    }

    @Test
    void testConstructorWithNumber() {
        Phone anotherPhone = new Phone("+4512345678");
        assertNotNull(anotherPhone);
        assertEquals("+4512345678", anotherPhone.getNumber());
    }
}
