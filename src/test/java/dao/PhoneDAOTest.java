package dao;

import config.HibernateConfig;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import model.Phone;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class PhoneDAOTest {
    private EntityManagerFactory emf = HibernateConfig.getEntityManagerFactoryConfig();
    private EntityManager em;
    private PhoneDAO phoneDAO = PhoneDAO.getInstance();
    private Phone phone;

    @BeforeEach
    public void setUp() {
        em = emf.createEntityManager();
        phone = new Phone("+4512345678"); // This is just a demonstration
    }

    @AfterEach
    public void tearDown() {
        if (em != null) {
            em.close();
        }
        if (emf != null) {
            emf.close();
        }
    }

    @Test
    @DisplayName("TEST ADD PHONE")
    public void addPhoneTest() {
        em.getTransaction().begin();
        phoneDAO.addPhone(phone);
        em.getTransaction().commit();

        assertNotNull(phone.getId(), "Phone should have an ID after being persisted");
    }

    @Test
    @DisplayName("TEST DELETE PHONE")
    public void deletePhoneTest() {
        em.getTransaction().begin();
        phoneDAO.addPhone(phone);
        em.getTransaction().commit();

        em.getTransaction().begin();
        phoneDAO.deletePhone(phone);
        em.getTransaction().commit();

        assertNull(em.find(Phone.class, phone.getId()), "Phone should be deleted");
    }

    @Test
    @DisplayName("TEST Validate Phone Number - Valid")
    public void validatePhoneNumberTest() {
        Phone number = new Phone("+4551234567");  // This matches the pattern
        assertTrue(number.validatePhoneNumber());
    }

    @Test
    @DisplayName("TEST Validate Phone Number - Invalid")
    public void invalidatePhoneNumberTest() {
        Phone number = new Phone("+51234567");  // This matches the pattern
        assertFalse(number.validatePhoneNumber());
    }
}