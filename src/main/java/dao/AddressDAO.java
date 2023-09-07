package dao;

import config.HibernateConfig;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import model.Address;

public class AddressDAO {
    EntityManagerFactory emf = HibernateConfig.getEntityManagerFactoryConfig();

    public Address findAddressById(int id) {
        try (EntityManager em = emf.createEntityManager()) {
            return em.find(Address.class, id);
        }
    }
    public Address persistAddress(model.Address address) {
        try (EntityManager em = emf.createEntityManager()) {
            em.getTransaction().begin();
            em.persist(address);
            em.getTransaction().commit();
            return address;
        }
    }

    public void deleteAddress(model.Address address) {
        try (EntityManager em = emf.createEntityManager()) {
            em.getTransaction().begin();
            em.remove(address);
            em.getTransaction().commit();
        }
    }

    public Address updateAddress(Address address) {
        try (EntityManager em = emf.createEntityManager()) {
            em.getTransaction().begin();
            Address address1 = em.merge(address);
            em.getTransaction().commit();
            return address1;
        }
    }
}
