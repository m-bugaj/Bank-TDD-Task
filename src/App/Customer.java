package App;

import java.util.ArrayList;
import java.util.List;

public class Customer {
    private final String name;
    private final String email;
    private final String accountNumber;
    private final String phoneNumber;
    private final List<String> report;
    private float balance;

    public Customer(String name, String email, String accountNumber, String phoneNumber, float initialBalance) {
        this.name = name;
        this.email = email;
        this.accountNumber = accountNumber;
        this.phoneNumber = phoneNumber;
        this.report = new ArrayList<>();
        this.balance = initialBalance;
    }

    public String getEmail() {
        return this.email;
    }

    public String getAccountNumber() {
        return this.accountNumber;
    }

    public String getName() {
        return this.name;
    }

    public String getPhoneNumber() {
        return this.phoneNumber;
    }

    public List<String> getReport() {
        return this.report;
    }

    public float getBalance() {
        return balance;
    }

    public void deposit(float depositAmount) {
        this.balance += depositAmount;
        report.add("Depozyt: +" + depositAmount + " zł");
    }

    public void sendMoney(Customer recipient, float transferAmount) {
        recipient.balance += transferAmount;
        this.balance -= transferAmount;

        report.add("Wykonano przelew do użytkownika " + recipient.getEmail() + ": -" + transferAmount + " zł");
        recipient.report.add("Odebrano przelew do użytkownika " + recipient.getEmail() + ": +" + transferAmount + " zł");
    }

    public float takeCredit(float creditAmount, float interestRate) {
        float balanceAfter = this.balance + creditAmount;
        this.balance -= creditAmount + interestRate * creditAmount;
        report.add("Wzięto pożyczkę wysokości: +" + creditAmount + " zł");
        report.add("Spłacono pożyczkę wraz z odsetkami: -" + (creditAmount * (1 + interestRate)) + " zł");
        return balanceAfter;
    }

    public void clearReport() {
        this.report.clear();
    }
}
