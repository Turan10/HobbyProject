package dao;

import config.HibernateConfig;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.TypedQuery;
import model.Person;

import java.util.List;

public class PersonDAOImpl {

    private EntityManagerFactory factory;

    public PersonDAOImpl() {
        this.factory = HibernateConfig.getEntityManagerFactoryConfig();
    }

    public List<Person> findPersonsByCityZip(int zip) {
        EntityManager entityManager = factory.createEntityManager();
        entityManager.getTransaction().begin();
        TypedQuery<Person> query = entityManager.createQuery("SELECT p FROM Person p WHERE p.address.city.zip = :zip", Person.class);
        query.setParameter("zip", zip);
        List<Person> results = query.getResultList();
        entityManager.getTransaction().commit();
        entityManager.close();
        return results;
    }
}
