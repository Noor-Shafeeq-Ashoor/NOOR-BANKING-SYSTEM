package com.ga.banking.Service;

import com.ga.banking.models.Customer;
import com.ga.banking.models.User;

import java.util.List;

public class CustomerService {

        private List<User> users;

        public CustomerService(List<User> users) {
            this.users = users;
        }

    public boolean usernameExists(String username) {
            for (User user : users) {
                if (user.getUsername() .equalsIgnoreCase(username)) {
                    return true; }
            }
            return false; }
     public boolean emailExists(String email) { for (User user : users) { if (user.getEmail() .equalsIgnoreCase(email)) { return true; }
     }
     return false; }

    public Customer createCustomer(int id, String username, String email, String password) {

        Customer customer = new Customer(id, username, email, password);
            users.add(customer);
            return customer;
        }
}
