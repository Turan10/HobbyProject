package dao;

import jakarta.persistence.EntityManager;
import model.Hobby;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class HobbyDAO {
    private static final System.Logger logger = System.getLogger(HobbyDAO.class.getName());

    public void populateHobbies(EntityManager em, String fileName) {
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
                Hobby hobby = new Hobby(values[0], values[1], values[2], values[3]);
                em.persist(hobby);
            }

            em.getTransaction().commit();
        } catch (IOException e) {
            em.getTransaction().rollback();
            logger.log(System.Logger.Level.ERROR, "Error populating database", e);
            throw new RuntimeException("Error populating database", e);
        }
    }
}
