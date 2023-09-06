package dao;

import config.HibernateConfig;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.TypedQuery;
import model.City;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class CityDAOImpl {

    private EntityManagerFactory factory;

    public CityDAOImpl() {
        this.factory = HibernateConfig.getEntityManagerFactoryConfig();
    }

    public Map<Integer, String> getAllPostcodesAndCityNamesInDenmark() {
        EntityManager entityManager = factory.createEntityManager();
        entityManager.getTransaction().begin();
        TypedQuery<City> query = entityManager.createQuery("SELECT c FROM City c", City.class);
        List<City> cities = query.getResultList();
        entityManager.getTransaction().commit();
        entityManager.close();

        // Assuming that ZIP codes are unique and map to a single city
        return cities.stream()
                .collect(Collectors.toMap(City::getZip, City::getCityName));
    }
}