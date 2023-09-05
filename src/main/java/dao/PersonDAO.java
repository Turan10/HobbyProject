package dao;

import config.HibernateConfig;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.TypedQuery;
import model.Hobby;
import model.Person;
import model.Phone;

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

        public void deletePerson(Person person) {
            try (EntityManager em = emf.createEntityManager()) {
                em.getTransaction().begin();
                em.remove(person);
                em.getTransaction().commit();
            }
        }

        public List<Phone> phoneNumberByPerson(int id) {
            try (EntityManager em = emf.createEntityManager()) {
                TypedQuery<Phone> query = em.createQuery("SELECT p FROM Phone p JOIN p.person ps WHERE ps.id = :id", Phone.class);
                query.setParameter("id", id);
                return query.getResultList();
            }
        }



    }









