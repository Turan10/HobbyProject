package dao;

import config.HibernateConfig;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import model.City;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CityDAOTest {






    @Test
    void persistCity() {
        City city = new City(2225, "København N", "Københavns Kommune", "Hovedstaden");
        CityDAO cityDAO = new CityDAO();
        cityDAO.persistCity(city);

        assertEquals(2225, city.getZip());

    }

    @Test
    void deleteCity() {
        int cityZip = 2225;
        CityDAO cityDAO = new CityDAO();
        City city = cityDAO.getCityByZip(cityZip);
        cityDAO.deleteCity(city);
        assertNull(cityDAO.getCityByZip(cityZip));

    }

    @Test
    void updateCity() {
        int cityZip = 2700;
        CityDAO cityDAO = new CityDAO();
        City city = cityDAO.getCityByZip(cityZip);
        city.setCityName("København NV");
        cityDAO.updateCity(city);
        assertEquals("København NV", cityDAO.getCityByZip(cityZip).getCityName());
    }

    @Test
    void getCityByZip() {
        int cityZip = 2700;
        CityDAO cityDAO = new CityDAO();
        City city = cityDAO.getCityByZip(cityZip);
        assertEquals("København NV", city.getCityName());
    }

    @Test
    void getAllPostcodesAndCityNamesInDenmark() {
        CityDAO cityDAO = new CityDAO();
        assertEquals(1100, cityDAO.getAllPostcodesAndCityNamesInDenmark().size());
    }
}