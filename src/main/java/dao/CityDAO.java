package dao;

import jakarta.persistence.EntityManager;
import model.City;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;



public class CityDAO {
    private static final System.Logger logger = System.getLogger(CityDAO.class.getName());
    public void populateDatabase(EntityManager em, String fileName) {
        if (em == null) {
            throw new IllegalArgumentException("EntityManager cannot be null");
        }
        if (fileName == null) {
            throw new IllegalArgumentException("File name cannot be null");
        }
        File file = new File(fileName);
        if (!file.exists() || !file.isFile()) {
            throw new IllegalArgumentException("File does not exist: " + fileName);
        }

        try (BufferedReader br = new BufferedReader(new FileReader(fileName))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] values = line.split(",");
                City city = new City();
                city.setZip(Integer.parseInt(values[0]));
                city.setCityName(values[1]);
                city.setRegionName(values[2]);
                city.setMunicipalityName(values[3]);
                em.persist(city);
            }
        } catch (IOException e) {
            logger.log(System.Logger.Level.ERROR, "Error populating database", e);
            throw new RuntimeException("Error populating database", e);
        }
    }
}
