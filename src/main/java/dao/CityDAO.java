package dao;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import model.City;



public class CityDAO {
    EntityManagerFactory emf = config.HibernateConfig.getEntityManagerFactoryConfig();


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


}
