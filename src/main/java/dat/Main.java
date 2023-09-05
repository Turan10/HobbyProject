package dat;

import config.HibernateConfig;
import dao.CityDAO;
import dao.PersonDAO;
import dao.PopulateDAO;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import model.Hobby;
import model.Person;

import java.util.Set;

// Press Shift twice to open the Search Everywhere dialog and type `show whitespaces`,
// then press Enter. You can now see whitespace characters in your code.
public class Main {
    public static void main(String[] args) {

        EntityManagerFactory emf = HibernateConfig.getEntityManagerFactoryConfig();

        try(EntityManager em = emf.createEntityManager()){
            PopulateDAO populateZip = new PopulateDAO();
            populateZip.populateDatabase(em, "src/main/resources/hobby.csv");




        }
    }
}