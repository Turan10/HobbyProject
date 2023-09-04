package dao;

import config.HibernateConfig;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.TypedQuery;
import model.Person;

import java.util.List;

public class PersonDAO {
    EntityManagerFactory emf = HibernateConfig.getEntityManagerFactoryConfig();


    public List<Person> getPersonsByHobby(String hobbyName) {
        try (EntityManager em = emf.createEntityManager()) {
            TypedQuery<Person> query = em.createQuery("SELECT p FROM Person p JOIN p.hobbies h WHERE h.name = :hobbyName", Person.class);
            query.setParameter("hobbyName", hobbyName);
            return query.getResultList();
        }

    }


    public void addPerson(Person person) {
        try (EntityManager em = emf.createEntityManager()) {
            em.getTransaction().begin();
            em.persist(person);
            em.getTransaction().commit();

        }

    }

}

