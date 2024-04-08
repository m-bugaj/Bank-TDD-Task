package Test;

import App.Admin;
import org.junit.Test;
import static org.junit.Assert.*;

public class AdminOperationsTest {
    @Test
    public void admin_addCustomer_shouldSucceed() {
        // Test dodawania klienta przez administratora
        Admin admin = new Admin();

        admin.addCustomer("Jan Kowalski", "jankowalski@example.com");

        assertTrue(admin.customerExist("jankowalski@example.com"));
    }

    @Test
    public void admin_removeCustomer_shouldSucceed() {
        // Test usuwania klienta przez administratora
        // Sprawdź, czy klient został usunięty poprawnie
    }

    @Test
    public void admin_modifyCustomerSettings_shouldSucceed() {
        // Test modyfikowania ustawień klienta przez administratora
        // Sprawdź, czy ustawienia klienta zostały zmienione poprawnie
    }

    @Test
    public void admin_generateCustomerReport_shouldSucceed() {
        // Test raportu operacji wybranego klienta przez administratora
        // Sprawdź, czy raport zawiera poprawne informacje o operacjach klienta
    }
}
