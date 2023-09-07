package dao;

import config.HibernateConfig;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.TypedQuery;

import model.*;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class PersonDAOTest {

    private EntityManager em;
    private PersonDAO personDAO = new PersonDAO();
    private EntityManagerFactory emf = HibernateConfig.getEntityManagerFactoryConfig();
    private PopulateDAO populateDAO = new PopulateDAO();
    private HobbyDAO hobbyDAO = new HobbyDAO();
    private TypeDAO typeDAO = new TypeDAO();

    private Person person = new Person();




    @Test
    void getPersonsByHobby() {
            String testHobbyName = "Basketbball";

            List<Person> persons = personDAO.getPersonsByHobby(testHobbyName);

            assertNotNull(persons);

            assertFalse(persons.isEmpty());

            for (Person person : persons) {
                boolean hasHobby = person.getHobbies().stream().anyMatch(hobby -> testHobbyName.equals(hobby.getName()));
                assertTrue(hasHobby, "Every person in the result should have the specified hobby.");
            }
    }

    @Test
    void getPersonInfoByPhoneNumber() {

    }

    @Test
    void testAddPerson() {
        Person newPerson = new Person("Sitthichai", "Akbas", 28);

        em = emf.createEntityManager();

        personDAO.addPerson(newPerson);

        TypedQuery<Person> query = em.createQuery("SELECT p FROM Person p WHERE p.firstName = :firstName AND p.lastName = :lastName", Person.class);
        query.setParameter("firstName", newPerson.getFirstName());
        query.setParameter("lastName", newPerson.getLastName());

        Person fetchedPerson = query.getSingleResult();

        assertNotNull(fetchedPerson, "The added person should exist in the database.");
        assertEquals(newPerson.getFirstName(), fetchedPerson.getFirstName());
        assertEquals(newPerson.getLastName(), fetchedPerson.getLastName());
        assertEquals(newPerson.getAge(), fetchedPerson.getAge());
        assertNotNull(fetchedPerson.getLastEdited());
    }

    @Test
    void getPersonByName() {
        String name = "Mr";

        Person fetchedPerson = personDAO.getPersonByName(name);

        assertNotNull(fetchedPerson, "Person should exist for given name.");
        assertEquals(name, fetchedPerson.getFirstName());

        System.out.println(fetchedPerson.getId());
    }

    @Test
    void updatePerson() {
        String currentName = "Mr";
        String newPersonName = "Metin";

        Person currentPerson = personDAO.getPersonByName(currentName);

        currentPerson.setFirstName(newPersonName);

        Person updatedPerson = personDAO.updatePerson(currentPerson);

        assertEquals(newPersonName,updatedPerson.getFirstName(), "Person updated");
    }


    @Test
    public void deleteByPersonId() {
        int personId= 5;

        Person person1 = personDAO.getPersonById(personId);

        personDAO.deleteByPersonId(person1);

        assertNull(personDAO.getPersonById(personId));

    }

    @Test
    public void testAddExistingHobbyToPerson() {
        int hobbyId = 9;
        int personId = 3;

        Hobby existingHobby = hobbyDAO.getHobbyById(hobbyId);

        Person personToUpdate = personDAO.getPersonById(personId);

        personToUpdate.addHobby(existingHobby);

        personDAO.updatePerson(personToUpdate);

        Person updatedPerson = personDAO.getPersonById(personId);

        System.out.println(updatedPerson.getHobbies().toString()+ "list of hobbies");

        assertTrue(updatedPerson.getHobbies().contains(existingHobby));
}


    @Test
    void testFindPersonsByCityZip() {
        int zip = 2200;
        PersonDAO personDAO = new PersonDAO();
        List<Person> persons = personDAO.findPersonsByCityZip(zip);
        assertEquals(1, persons.size());
    }
}