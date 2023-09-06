package dao;

import config.HibernateConfig;
import dto.HobbyWithCount;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.TypedQuery;
import model.Hobby;

import java.util.List;

public class HobbyDAO {
    private static HobbyDAO instance;
    EntityManagerFactory emf = HibernateConfig.getEntityManagerFactoryConfig();

    public static HobbyDAO getInstance() {
        if (instance == null) {
            instance = new HobbyDAO();
        }
        return instance;
    }

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