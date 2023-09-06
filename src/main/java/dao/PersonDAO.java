package dao;

import config.HibernateConfig;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.TypedQuery;
import model.Hobby;
import model.Person;
import model.Phone;

import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class PersonDAO {

    private static PersonDAO instance;
    EntityManagerFactory emf = HibernateConfig.getEntityManagerFactoryConfig();

    public static PersonDAO getInstance() {
        if (instance == null) {
            instance = new PersonDAO();
        }
        return instance;
    }


    public List<Person> getPersonsByHobby(String hobbyName) {
        try (EntityManager em = emf.createEntityManager()) {
            TypedQuery<Person> query = em.createQuery("SELECT p FROM Person p JOIN p.hobbies h WHERE h.name = :hobbyName", Person.class);
            query.setParameter("hobbyName", hobbyName);
            return query.getResultList();
        }

    }

    public Person getPersonInfoByPhoneNumber(String number) {
        try (EntityManager em = emf.createEntityManager()) {
            TypedQuery<Person> query = em.createQuery("SELECT p FROM Person p JOIN p.phones ph WHERE ph.number = :number", Person.class);
            query.setParameter("number", number);
            return query.getSingleResult();
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

    public Person updatePerson(Person person) {
        try (EntityManager em = emf.createEntityManager()) {
            em.getTransaction().begin();
            Person person1 = em.merge(person);
            em.getTransaction().commit();
            return person1;
        }
    }

    public Person getPersonByName(String name) {
        try (EntityManager em = emf.createEntityManager()) {
            return em.find(Person.class, name);
        }

    }

        public List<Person> findPersonsByCityZip(int zip) {
            try(EntityManager em = emf.createEntityManager()){
            TypedQuery<Person> query = em.createQuery("SELECT p FROM Person p WHERE p.address.city.zip = :zip", Person.class);
            query.setParameter("zip", zip);
            return query.getResultList();
        }

    }


}









