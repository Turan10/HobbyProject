package dao;

import config.HibernateConfig;
import dto.HobbyWithCount;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.TypedQuery;
import model.Hobby;
import model.Person;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;

public class HobbyDAO {
    EntityManagerFactory emf = HibernateConfig.getEntityManagerFactoryConfig();

    public List<HobbyWithCount> GetHobbiesWithCount() {
        try (EntityManager em = emf.createEntityManager()) {
            TypedQuery<HobbyWithCount> query = em.createQuery("Select new dto.HobbyWithCount(h, count (p)) from Hobby h left join h.persons p group by h", HobbyWithCount.class);
            return query.getResultList();
        }

    }

    public void persistHobby(Hobby hobby) {
        try (EntityManager em = emf.createEntityManager()) {
            em.getTransaction().begin();
            em.persist(hobby);
            em.getTransaction().commit();
        }
    }

    public void deleteHobby(Hobby hobby) {
        try (EntityManager em = emf.createEntityManager()) {
            em.getTransaction().begin();
            em.remove(hobby);
            em.getTransaction().commit();
        }
    }

    public Hobby updateHobby(Hobby hobby) {
        try (EntityManager em = emf.createEntityManager()) {
            em.getTransaction().begin();
            Hobby hobby1 = em.merge(hobby);
            em.getTransaction().commit();
            return hobby1;
        }
    }

    public Hobby getHobbyByName(String name) {
        try (EntityManager em = emf.createEntityManager()) {
            return em.find(Hobby.class, name);
        }
    }


}