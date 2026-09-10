package com.ga.banking.Service;

import com.ga.banking.models.User;

import java.util.List;

public class Authentication {

        private List<User> users;

        public Authentication(List<User> users) {
            this.users = users;
        }

        public User login(String username, String password) {

            for (User user : users) {

                if (user.getUsername().equals(username) && user.getPassword().equals(password)) {

                    return user;
                }
            }

            return null;
        }
}
