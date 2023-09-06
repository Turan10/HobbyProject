package dao;

import config.HibernateConfig;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.TypedQuery;
import model.Phone;

import java.util.List;

public class PhoneDAO {

    EntityManagerFactory emf = HibernateConfig.getEntityManagerFactoryConfig();
    public List<Phone> phoneNumberByPerson(int id) {
        try (EntityManager em = emf.createEntityManager()) {
            TypedQuery<Phone> query = em.createQuery("SELECT p FROM Phone p JOIN p.person ps WHERE ps.id = :id", Phone.class);
            query.setParameter("id", id);
            return query.getResultList();
        }
    }

    public void addPhone(Phone phone) {
        try (EntityManager em = emf.createEntityManager()) {
            em.getTransaction().begin();
            em.persist(phone);
            em.getTransaction().commit();
        }
    }

    public void deletePhone(Phone phone) {
        try (EntityManager em = emf.createEntityManager()) {
            em.getTransaction().begin();
            em.remove(phone);
            em.getTransaction().commit();
        }
    }

    public Phone updatePhone(Phone phone) {
        try (EntityManager em = emf.createEntityManager()) {
            em.getTransaction().begin();
            Phone phone1 = em.merge(phone);
            em.getTransaction().commit();
            return phone1;
        }
    }

    public Phone getPhoneByNumber(String number) {
        try (EntityManager em = emf.createEntityManager()) {
            return em.find(Phone.class, number);
        }
    }
    
}
