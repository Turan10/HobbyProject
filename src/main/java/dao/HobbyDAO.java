package dao;

import config.HibernateConfig;
import dto.HobbyWithCount;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.NoResultException;
import jakarta.persistence.TypedQuery;
import model.Hobby;
import model.Type;


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

    /*public Hobby getHobbyByName(String name) {
        try (EntityManager em = emf.createEntityManager()) {
            return em.find(Hobby.class, name);
        }
    }*/
    public Hobby getHobbyByName(String name) {
        try (EntityManager em = emf.createEntityManager()) {
            TypedQuery<Hobby> query = em.createQuery("SELECT h FROM Hobby h WHERE h.name = :name", Hobby.class);
            query.setParameter("name", name);
            List<Hobby> hobby = query.getResultList();
            if (!hobby.isEmpty()) {
                return hobby.get(0);
            }
            return null;
        }
    }

    public Hobby getHobbyById(int id) {
        EntityManager em = null;
        try {
            em = emf.createEntityManager();
            return em.find(Hobby.class, id);
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public Type getTypeByName(String name) {
        EntityManager em = null;
        try {
            em = emf.createEntityManager();
            TypedQuery<Type> query = em.createQuery("SELECT t FROM Type t WHERE t.name = :name", Type.class);
            query.setParameter("name", name);
            return query.getSingleResult();
        } catch (NoResultException nre) {
            return null;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }
    public void updateHobbyType(int hobbyId, String newTypeName) {
        EntityManager em = null;
        try {
            em = emf.createEntityManager();
            em.getTransaction().begin();

            Hobby hobby = em.find(Hobby.class, hobbyId);
            if (hobby != null) {
                Type newType = getTypeByName(newTypeName);
                if (newType == null) {
                    if (newType == null) {
                        newType = new Type();
                        newType.setName(newTypeName);
                        em.persist(newType);  // Persist the new Type object
                    }
                }
                hobby.setType(newType);
            }

            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

}