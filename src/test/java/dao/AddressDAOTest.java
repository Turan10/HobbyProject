package dao;

import model.Address;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;

public class AddressDAOTest {
    private AddressDAO addressDAO = new AddressDAO();

    @Test
    public void testPersistAddress() {
        Address existingAddress = addressDAO.findAddressById(1);
        assertNotNull(existingAddress);
        assertEquals("Amagerbrogade 25", existingAddress.getStreet());
    }

    @Test
    public void testDeleteAddress() {
        Address existingAddress = addressDAO.findAddressById(2);
        addressDAO.deleteAddress(existingAddress);

        Address deletedAddress = addressDAO.findAddressById(2);
        assertNull(deletedAddress);
    }

    @Test
    public void testUpdateAddress() {
        Address existingAddress = addressDAO.findAddressById(3);
        existingAddress.setStreet("Nyvejtest");
        Address updatedAddress = addressDAO.updateAddress(existingAddress);
        assertEquals("Nyvejtest", updatedAddress.getStreet());
    }

    @Test
    public void testFindAddressById() {
        Address existingAddress = addressDAO.findAddressById(1);
        assertNotNull(existingAddress);
        assertEquals("Amagerbrogade 25", existingAddress.getStreet());

    }
}