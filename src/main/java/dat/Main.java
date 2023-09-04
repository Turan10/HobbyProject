package dat;

import config.HibernateConfig;
import dao.CityDAO;
import dao.PersonDAO;
import dao.PopulateDAO;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import model.Person;

// Press Shift twice to open the Search Everywhere dialog and type `show whitespaces`,
// then press Enter. You can now see whitespace characters in your code.
public class Main {
    public static void main(String[] args) {

        EntityManagerFactory emf = HibernateConfig.getEntityManagerFactoryConfig();

        try(EntityManager em = emf.createEntityManager()){
            PopulateDAO populateZip = new PopulateDAO();
            populateZip.populateDatabase(em, "src/main/resources/zip.csv");
            //populateZip.populateDatabase(em, "src/main/resources/hobby.csv");


            //For getting persons by hobby
            Person person = new Person("John", "Doe",28);
            PersonDAO personDAO = new PersonDAO();
            personDAO.getPersonsByHobby("Gaming");


        }
    }
}