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


}