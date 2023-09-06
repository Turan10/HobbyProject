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

    // populating randos
    public void createRandomPersons(EntityManager em, int numberOfPersons) {
        if (em == null) {
            throw new IllegalArgumentException("EntityManager cannot be null");
        }

        List<Hobby> allHobbies = em.createQuery("SELECT h FROM Hobby h", Hobby.class).getResultList();

        em.getTransaction().begin();

        for (int i = 0; i < numberOfPersons; i++) {

            Collections.shuffle(allHobbies);
            Hobby randomHobby = allHobbies.get(0);


            Person newPerson = new Person("Ben" + i, "Dover" + i, 45 + i);

            Set<Hobby> randomHobbies = new HashSet<>();
            randomHobbies.add(randomHobby);

            newPerson.setHobbies(randomHobbies);


            em.persist(newPerson);
        }

        em.getTransaction().commit();
    }

    }









