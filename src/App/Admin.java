package App;

import java.util.ArrayList;
import java.util.List;

public class Admin {
    private final List<Customer> customers;

    public Admin() {
        this.customers = new ArrayList<>();
    }

    public void addCustomer(String name, String email) {
        customers.add(new Customer(name, email));
    }

    public boolean customerExist(String email) {
        for(Customer customer : customers) {
            if (customer.getEmail().equals(email)) return true;
        }
        return false;
    }
}
