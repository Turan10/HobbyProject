package dao;

import dto.CityPostcodeAndName;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.TypedQuery;
import model.City;

import java.util.List;


public class CityDAO {

    private static CityDAO instance;
    EntityManagerFactory emf = config.HibernateConfig.getEntityManagerFactoryConfig();


    public static CityDAO getInstance() {
        if (instance == null) {
            instance = new CityDAO();
        }
        return instance;
    }


    public void persistCity(City city) {
        try (EntityManager em = emf.createEntityManager()) {
            em.getTransaction().begin();
            em.persist(city);
            em.getTransaction().commit();
        }
    }

    public void deleteCity(City city) {
        try (EntityManager em = emf.createEntityManager()) {
            em.getTransaction().begin();
            em.remove(city);
            em.getTransaction().commit();
        }
    }

    public City updateCity(City city) {
        try (EntityManager em = emf.createEntityManager()) {
            em.getTransaction().begin();
            City city1 = em.merge(city);
            em.getTransaction().commit();
            return city1;
        }
    }

    public City getCityByZip(int id) {
        try (EntityManager em = emf.createEntityManager()) {
            return em.find(City.class, id);
        }
    }


    public List<CityPostcodeAndName> getAllPostcodesAndCityNamesInDenmark() {
        try (EntityManager em = emf.createEntityManager()) {
            TypedQuery<CityPostcodeAndName> query = em.createQuery("SELECT c FROM City c", CityPostcodeAndName.class);
            return query.getResultList();

        }
    }
}