package Test;

import App.Admin;
import App.Customer;
import org.junit.Test;
import static org.junit.Assert.*;

public class AdminOperationsTest {
    @Test
    public void admin_addCustomer_shouldSucceed() {
        // Test dodawania klienta przez administratora
        Admin admin = new Admin();

        admin.addCustomer("Jan Kowalski", "jankowalski@example.com", 123123123);

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

        admin.addCustomer("Jan Kowalski", "jankowalski@example.com", 123123123);

        Customer customer = admin.getCustometByMail("jankowalski@example.com");

        admin.modifyCustomerSettings(customer, "Jan Nowak", 321321321);

        assertEquals("Jan Nowak", customer.getName());
        assertEquals(321321321, customer.getPhoneNumber());
    }

    @Test
    public void admin_generateCustomerReport_shouldSucceed() {
        // Test raportu operacji wybranego klienta przez administratora
        // Sprawdź, czy raport zawiera poprawne informacje o operacjach klienta
    }
}
