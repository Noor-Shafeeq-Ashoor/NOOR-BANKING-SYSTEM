package com.ga.banking.DataBase;

import com.ga.banking.models.User;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class UserData {

    private final String FILE_NAME = "users.txt";
    public void saveUser(User user) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_NAME, true))) {

            writer.write(
                    user.getId() + "," +
                            user.getName() + "," +
                            user.getUsername() + "," +
                            user.getPassword()
            );

            writer.newLine();

            System.out.println("User saved successfully.");

        } catch (IOException e) {

            System.out.println("Error saving user.");
            e.printStackTrace();
        }
    }

    public List<String> readUsers() {

        List<String> users = new ArrayList<>();

        File file = new File(FILE_NAME);

        if (!file.exists()) {
            return users;
        }

        try (BufferedReader reader =
                     new BufferedReader(new FileReader(FILE_NAME))) {

            String line;

            while ((line = reader.readLine()) != null) {

                users.add(line);
            }

        } catch (IOException e) {

            System.out.println("Error reading users.");
            e.printStackTrace();
        }

        return users;
    }


    public String findUser(
            String username,
            String password) {

        List<String> users = readUsers();

        for (String user : users) {

            String[] data = user.split(",");

            String savedUsername = data[2];
            String savedPassword = data[3];

            if (savedUsername.equals(username)
                    && savedPassword.equals(password)) {

                return user;
            }
        }

        return null;
    }
}