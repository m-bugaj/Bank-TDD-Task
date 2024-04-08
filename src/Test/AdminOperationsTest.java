package Test;

import App.Admin;
import App.Customer;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.*;

public class AdminOperationsTest {
    @Test
    public void admin_addCustomer_shouldSucceed() {
        // Test dodawania klienta przez administratora
        Admin admin = new Admin();

        admin.addCustomer("Jan Kowalski", "jankowalski@example.com", "4321432143214321", "123123123");

        Customer customer = admin.getCustomerByMail("jankowalski@example.com");
        String regex = "\\d{16}"; // Format 16 cyfr
        assertTrue(customer.getAccountNumber().matches(regex));

        assertTrue(admin.customerExist("jankowalski@example.com"));
    }

    @Test
    public void admin_removeCustomer_shouldSucceed() {
        // Test usuwania klienta przez administratora
        Admin admin = new Admin();

        admin.removeCustomer("jankowalski@example.com");

        assertFalse(admin.customerExist("jankowalski@example.com"));
    }

    @Test
    public void admin_modifyCustomerSettings_shouldSucceed() {
        // Test modyfikowania ustawień klienta przez administratora
        // Sprawdź, czy ustawienia klienta zostały zmienione poprawnie
        Admin admin = new Admin();

        admin.addCustomer("Jan Kowalski", "jankowalski@example.com", "4321432143214321", "123123123");

        Customer customer = admin.getCustomerByMail("jankowalski@example.com");

        admin.modifyCustomerSettings(customer, "Jan Nowak", "321321321");

        customer = admin.getCustomerByMail("jankowalski@example.com");

        assertEquals("Jan Nowak", customer.getName());
        assertEquals("321321321", customer.getPhoneNumber());
    }

    @Test
    public void admin_generateCustomerReport_shouldSucceed() {
        // Test raportu operacji wybranego klienta przez administratora
        Admin admin = new Admin();

        admin.addCustomer("Jan Kowalski", "jankowalski@example.com", "4321432143214321", "123123123");

        Customer customer = admin.getCustomerByMail("jankowalski@example.com");

        List<String> report = customer.getReport();

        assertNotNull(report);
    }
}
