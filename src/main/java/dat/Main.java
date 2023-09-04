package dat;

import config.HibernateConfig;
import dao.CityDAO;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;

// Press Shift twice to open the Search Everywhere dialog and type `show whitespaces`,
// then press Enter. You can now see whitespace characters in your code.
public class Main {
    public static void main(String[] args) {

        EntityManagerFactory emf = HibernateConfig.getEntityManagerFactoryConfig();

        try(EntityManager em = emf.createEntityManager()){
            CityDAO populateZip = new CityDAO();
            populateZip.populateDatabase(em, "src/main/resources/zipcodes.csv");
        }
    }
}