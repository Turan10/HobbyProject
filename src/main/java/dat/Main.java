package dat;

import config.HibernateConfig;
import dao.CityDAO;
import dao.CityDAOImpl;
import dao.PersonDAOImpl;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import model.Person;

import java.util.List;
import java.util.Map;

// Press Shift twice to open the Search Everywhere dialog and type `show whitespaces`,
// then press Enter. You can now see whitespace characters in your code.
public class Main {
    public static void main(String[] args) {
        PersonDAOImpl personDAO = new PersonDAOImpl();
        List<Person> persons = personDAO.findPersonsByCityZip(2800);
        for (Person person : persons) {
            System.out.println("Name: " + person.getFirstName() + " " + person.getLastName());
            System.out.println("Age: " + person.getAge());

            CityDAOImpl cityDAO = new CityDAOImpl();
            Map<Integer, String> postcodesAndCities = cityDAO.getAllPostcodesAndCityNamesInDenmark();

            // Print eller vis ZIP numre og bynavne
            for (Map.Entry<Integer, String> entry : postcodesAndCities.entrySet()) {
                System.out.println("ZIP Code: " + entry.getKey() + ", City: " + entry.getValue());
            }
        }
    }
}