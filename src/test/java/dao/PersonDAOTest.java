package dao;


import config.HibernateConfig;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.PersistenceUnit;
import lombok.Setter;
import model.Address;
import model.City;
import model.Person;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;


class PersonDAOTest {

    @Test
    void testFindPersonsByCityZip() {
        int zip = 2200;
        PersonDAO personDAO = new PersonDAO();
        List<Person> persons = personDAO.findPersonsByCityZip(zip);
        assertEquals(1, persons.size());
    }
}