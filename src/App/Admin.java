package App;

import java.util.ArrayList;
import java.util.List;

public class Admin {
    private final List<Customer> customers;

    public Admin() {
        this.customers = new ArrayList<>();
    }

    public void addCustomer(String name, String email, String accountNumber, String phoneNumber) {
        customers.add(new Customer(name, email, accountNumber, phoneNumber, 0F));
    }

    public boolean customerExist(String email) {
        for(Customer customer : customers) {
            if (customer.getEmail().equals(email)) return true;
        }
        return false;
    }

    public Customer getCustomerByMail(String email) {
        for(Customer customer : customers) {
            if (customer.getEmail().equals(email)) return customer;
        }
        return null;
    }

    public Customer getCustomerByAccountNumber(String accountNumber) {
        for(Customer customer : customers) {
            if (customer.getAccountNumber().equals(accountNumber)) return customer;
        }
        return null;
    }

    public void removeCustomer(String email) {
        customers.removeIf(customer -> customer.getEmail().equals(email));
    }

    public void modifyCustomerSettings(Customer customer, String fullName, String phoneNumber) {
        Customer updatedCustomer = new Customer(fullName, customer.getEmail(), customer.getAccountNumber(), phoneNumber, customer.getBalance());
        customers.replaceAll(c -> c.equals(customer) ? updatedCustomer : c);
    }
}
