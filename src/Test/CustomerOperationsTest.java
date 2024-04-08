package Test;

import App.Admin;
import App.Customer;
import org.junit.Test;
import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CustomerOperationsTest {
    @Test
    public void customer_deposit_shouldSucceed() {
        // Test zasilenia konta przez klienta w bankomacie
        // Sprawdź, czy konto zostało zasilone poprawnie
        Admin admin = new Admin();

        admin.addCustomer("Jan Kowalski", "jankowalski@example.com", "4321432143214321", "123123123");

        Customer customer = admin.getCustomerByMail("jankowalski@example.com");

        float balanceBefore = customer.getBalance();
        float testDeposit = 20F;
        customer.deposit(testDeposit);

        assertEquals(customer.getBalance(), balanceBefore + testDeposit, 0);
    }

    @Test
    public void customer_outgoingAndIncomingTransfer_shouldSucceed() {
        // Test przelewu wychodzącego przez klienta
        // Sprawdź, czy przelew został wykonany poprawnie
        Admin admin = new Admin();

        admin.addCustomer("Jan Kowalski", "jankowalski@example.com", "4321432143214321", "123123123");
        admin.addCustomer("Jan Nowak", "jannowak@example.com", "1234123412341234", "111111222");

        Customer customer1 = admin.getCustomerByMail("jankowalski@example.com");
        Customer customer2 = admin.getCustomerByMail("jannowak@example.com");

        String regex = "\\d{16}"; // Format 16 cyfr
        assertTrue(customer1.getAccountNumber().matches(regex));
        assertTrue(customer2.getAccountNumber().matches(regex));

        float balance1Before = customer1.getBalance();
        float balance2Before = customer2.getBalance();

        float transferAmount = 1000F; // Przyjmujemy kwotę przelewu równą 1000
        customer1.sendMoney(customer2, transferAmount);

        assertEquals(balance1Before - transferAmount, customer1.getBalance(), 0);
        assertEquals(balance2Before + transferAmount, customer2.getBalance(), 0);
    }

    @Test
    public void customer_credit_shouldSucceed() {
        // Test udzielenia kredytu klientowi
        // Sprawdź, czy konto zostało zasilone o odpowiednią kwotę
        Admin admin = new Admin();

        admin.addCustomer("Jan Kowalski", "jankowalski@example.com", "4321432143214321", "123123123");

        Customer customer = admin.getCustomerByMail("jankowalski@example.com");

        float balanceBefore = customer.getBalance();
        System.out.print(balanceBefore);
        float creditAmount = 100F;
        float interestRate = 0.2F;

        // Metoda takeCredit() powinna zwrócić saldo po pożyczce a następnie zmodyfikować saldo do stanu po zwróceniu pożyczki
        float balanceAfter = customer.takeCredit(creditAmount, interestRate);
        customer = admin.getCustomerByMail("jankowalski@example.com");

        assertEquals(balanceAfter, balanceBefore + creditAmount, 0);
        assertEquals(customer.getBalance(), balanceBefore - (creditAmount + creditAmount * interestRate), 0);
    }

    @Test
    public void customer_viewTransactionHistory_shouldSucceed() {
        // Test przeglądania historii operacji przez klienta
        Admin admin = new Admin();

        admin.addCustomer("Jan Kowalski", "jankowalski@example.com", "4321432143214321", "123123123");
        admin.addCustomer("Jan Nowak", "jannowak@example.com", "1234123412341234", "111111222");

        Customer customer1 = admin.getCustomerByMail("jankowalski@example.com");
        Customer customer2 = admin.getCustomerByMail("jannowak@example.com");

        String regex = "\\d{16}"; // Format 16 cyfr
        assertTrue(customer1.getAccountNumber().matches(regex));
        assertTrue(customer2.getAccountNumber().matches(regex));

        // Czyszczenie raportu na potrzeby testowania
        customer1.clearReport();

        List<String> sampleReport = new ArrayList<>();

        float depositAmount = 2000F;
        customer1.deposit(depositAmount);
        sampleReport.add("Depozyt: 2000.0 zł");

        float transferAmount = 1000F;
        customer1.sendMoney(customer2, transferAmount);
        sampleReport.add("Wykonano przelew do użytkownika jannowak@example.com: -1000.0 zł");

        float creditAmount = 100F;
        float interestRate = 0.2F;
        // Metoda takeCredit() powinna zwrócić saldo po pożyczce a następnie zmodyfikować saldo do stanu po zwróceniu pożyczki
        float balanceAfter = customer1.takeCredit(creditAmount, interestRate);
        sampleReport.add("Wzięto pożyczkę wysokości: 100.0 zł");
        sampleReport.add("Spłacono pożyczkę wraz z odsetkami: -" + (creditAmount * (1 + interestRate)) + " zł");

        // Pobieramy historię transakcji klienta
        List<String> transactionHistory = customer1.getReport();

        for (int i=0;i<transactionHistory.size();i++) {
            // Sprawdzamy, czy historia transakcji zawiera oczekiwane operacje
            assertEquals(transactionHistory.get(i), sampleReport.get(i));
        }
    }
}
