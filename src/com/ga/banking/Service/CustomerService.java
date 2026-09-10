package com.ga.banking.Service;

import com.ga.banking.models.Customer;
import com.ga.banking.models.User;

import java.util.List;

public class CustomerService {

        private List<User> users;

        public CustomerService(List<User> users) {
            this.users = users;
        }

        public Customer createCustomer(int id, String name, String username, String password) {
            Customer customer = new Customer(id, name, username, password);
            users.add(customer);
            return customer;
        }
}
