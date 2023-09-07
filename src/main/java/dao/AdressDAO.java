package dao;

import config.HibernateConfig;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import model.Address;
import model.City;

public class AdressDAO {
    EntityManagerFactory emf = HibernateConfig.getEntityManagerFactoryConfig();

    public Address persistAddress(model.Address address) {
        try (EntityManager em = emf.createEntityManager()) {
            em.getTransaction().begin();
             em.persist(address);
            em.getTransaction().commit();
            return address;
        }
    }
}
