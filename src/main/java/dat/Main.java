package dat;

import config.HibernateConfig;
import dao.*;
import dto.PersonInfoDTO;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import model.Address;
import model.City;
import model.Person;
import model.Phone;

// Press Shift twice to open the Search Everywhere dialog and type `show whitespaces`,
// then press Enter. You can now see whitespace characters in your code.
public class Main {
    public static void main(String[] args) {

        /*EntityManagerFactory emf = HibernateConfig.getEntityManagerFactoryConfig();

        try(EntityManager em = emf.createEntityManager()){
            CityDAO populateZip = new CityDAO();
            populateZip.populateDatabase(em, "src/main/resources/zip.csv");*/


        //populateZip.populateDatabase(em, "src/main/resources/hobby.csv");


        EntityManagerFactory emf = HibernateConfig.getEntityManagerFactoryConfig();

       /* try (EntityManager em = emf.createEntityManager()) {
            TypeDAO populateType = new TypeDAO();
            populateType.populateTypes(em, "src/main/resources/hobby.csv");

            PopulateDAO populateDAO = new PopulateDAO();
            populateDAO.populateDatabase(em, "src/main/resources/hobby.csv");

            populateDAO.populateDatabase(em, "src/main/resources/zip.csv");
*/


          Person p = new Person("Vivek", "Nagra", 26);
          Address a = new Address("Amagerbrogade 25");
          Phone phone1 = new Phone("+4512345678");
          Phone phone2 = new Phone("+4532154367");
          AdressDAO adressDAO = new AdressDAO();

          a.addCity(2200);

          p.setAddress(a);
          p.addPhone(phone1);
          p.addPhone(phone2);

          PersonDAO personDAO = new PersonDAO();

          personDAO.addPerson(p);

        System.out.println(p.getAddress().getId());




        }
    }

