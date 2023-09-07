package dao;

import config.HibernateConfig;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import model.Type;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;


public class TypeDAO {

    EntityManagerFactory emf = HibernateConfig.getEntityManagerFactoryConfig();

    private static TypeDAO instance;


    public static TypeDAO getInstance() {
        if (instance == null) {
            instance = new TypeDAO();
        }
        return instance;
    }

    public void populateTypes(EntityManager em, String fileName) {
        if (em == null) {
            throw new IllegalArgumentException("EntityManager cannot be null");
        }
        if (fileName == null) {
            throw new IllegalArgumentException("File name cannot be null");
        }

        try (BufferedReader br = new BufferedReader(new FileReader(fileName))) {
            String line;
            em.getTransaction().begin();

            while ((line = br.readLine()) != null) {
                String[] values = line.split(",");
                String typeName = values[6];

                //fjerner de unødvendige tegn
                typeName = typeName.replaceAll("['();]", "");

                //tjek om typen findes for at undgå dubletter
                Type existingType = em.find(Type.class, typeName);

                if (existingType == null) {
                    Type type = new Type(typeName);
                    em.persist(type);
                }
            }
            em.getTransaction().commit();
        } catch (IOException e) {
            em.getTransaction().rollback();
        }
    }

    public void persistType(Type type) {
        try (EntityManager em = emf.createEntityManager()) {
            em.getTransaction().begin();
            em.persist(type);
            em.getTransaction().commit();
        }
    }

}
