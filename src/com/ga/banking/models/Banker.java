package com.ga.banking.models;

import com.ga.banking.Service.Authentication;

public class Banker extends User {
    public Banker(int id, String username, String email, String password) {

        super(id, username, email, "", "");

        String salt = Authentication.generateSalt();
        String hash = Authentication.hashPassword(password, salt);
        setPassSalt(salt);
        setPassHash(hash);
        

}
};