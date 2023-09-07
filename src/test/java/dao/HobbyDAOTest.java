package dao;

import config.HibernateConfig;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.TypedQuery;

import model.*;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class HobbyDAOTest {

    private EntityManagerFactory emf = HibernateConfig.getEntityManagerFactoryConfig();
    private EntityManager em;
    private TypeDAO typeDAO;
    private PopulateDAO populateDAO;
    private HobbyDAO hobbyDAO = new HobbyDAO();
    private Person person = new Person();
    private Hobby hobby = new Hobby();
    private City city = new City();
    private Phone phone = new Phone();
    private Address address = new Address();



    @BeforeEach
    public void setUp() {
        em = emf.createEntityManager();
        populateDAO= new PopulateDAO();
        typeDAO = new TypeDAO();
    }

    @AfterEach
    public void tearDown(){
        if (em != null) {
            em.close();
        }
        if (emf != null) {
            emf.close();
        }
    }

    @Test
    @DisplayName("TEST POPULATION OF TYPE TABLE")
    public void populateTypes(){
        typeDAO.populateTypes(em, "src/main/resources/hobby.csv");

        TypedQuery<Hobby> query = em.createQuery("SELECT h From Hobby h", Hobby.class);

        assertNotNull(query, "Type tablen er tom");
        System.out.println("The hobby table is populated");
    }

    @Test
    @DisplayName("TEST POPULATION HOBBIES")
    void populateHobbies(){
        populateDAO.populateDatabase(em, "src/main/resources/hobby.csv");

        TypedQuery<Hobby> query = em.createQuery("SELECT h From Hobby h", Hobby.class);

        assertNotNull(query, "Hobby tablen er tom");


        List<Hobby> allHobbies = em.createQuery("SELECT h FROM Hobby h", Hobby.class).getResultList();
        assertFalse(allHobbies.isEmpty(), "Hobby table should not be empty");
        //tjek om alle hobbies har en valid type
        for (Hobby hobby : allHobbies) {
            Type type = hobby.getType();
            assertEquals(type, em.find(Type.class, type.getName()), "Each hobby should have a valid type");
        }
    }

    @Test
    public void testPersistType() {
        Type newType = new Type("Baller");

        typeDAO.persistType(newType);

        Type retrievedType = em.find(Type.class, newType.getName());
        assertNotNull(retrievedType, "Type should be saved");
        assertEquals(newType.getName(), retrievedType.getName(), "Type names should be equal");
    }


    @Test
    void persistHobby() {

        Type newType = new Type("Baller");
        Hobby newHobby = new Hobby("Ballin", "https://wikilink.ballers", "Balllller","Baller" );
        newHobby.setType(newType);

        hobbyDAO.persistHobby(newHobby);

        Hobby retrievedHobby = em.find(Hobby.class, newHobby.getId());

        assertNotNull(retrievedHobby, "Hobby should be saved");
        assertEquals(newHobby.getName(), retrievedHobby.getName(), "Names should be equal");
        assertEquals(newHobby.getType().getName(), retrievedHobby.getType().getName(), "Types should be equal");
        assertEquals(newHobby.getWikilink(), retrievedHobby.getWikilink(), "Wikilinks should be equal");
        assertEquals(newHobby.getCategory(), retrievedHobby.getCategory(), "Categories should be equal");

    }

    @Test
    void getHobby(){
        String hobbyName = "Ballin";

        Hobby foundHobby = hobbyDAO.getHobbyByName(hobbyName);


        assertNotNull(foundHobby, "Fetched hobby should not be null");
        assertEquals(hobbyName, foundHobby.getName(), "Could not find a hobby with name " + hobbyName);

        System.out.println("Hobby" + " " + hobbyName + " " + "Has been found. ID: " + foundHobby.getId() );
    }

    @Test
    void updateHobbyName() {
        int hobbyId = 1;  // Replace with a hobby ID that exists in your database
        String newName = "Programmering";

        Hobby currentHobby = hobbyDAO.getHobbyById(hobbyId);

        currentHobby.setName(newName);

        Hobby updatedHobby = hobbyDAO.updateHobby(currentHobby);

        assertEquals(newName, updatedHobby.getName(), "Hobby name upated");
    }

    @Test
    void updateHobbyType() {
        int hobbyId = 1;
        String newTypeName = "Konkurrence";

        hobbyDAO.updateHobbyType(hobbyId, newTypeName);

        Hobby updatedHobby = hobbyDAO.getHobbyById(hobbyId);
        assertEquals(newTypeName, updatedHobby.getType().getName(), "hobby type updated");
    }


    @Test
    void deleteHobby() {
        HobbyDAO hobbyDAO = new HobbyDAO();
        int hobbyId = 1356;

        Hobby existingHobby = em.find(Hobby.class, hobbyId);
        assertNotNull(existingHobby, "Hobby should exist");

        hobbyDAO.deleteHobby(existingHobby);
        assertNull(em.find(Hobby.class, hobbyId), "Hobby should be deleted");
    }

    @Test
    void getHobbiesWithCount() {


    }

}