package dao;

import dto.CityPostcodeAndName;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.TypedQuery;
import model.City;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;


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

    public City getCityByName(String name) {
        try (EntityManager em = emf.createEntityManager()) {
            return em.find(City.class, name);
        }
    }


    public List<CityPostcodeAndName> getAllPostcodesAndCityNamesInDenmark() {
        try (EntityManager em = emf.createEntityManager()) {
            TypedQuery<CityPostcodeAndName> query = em.createQuery("SELECT c FROM City c", CityPostcodeAndName.class);
            return query.getResultList();

        }

    }


}
