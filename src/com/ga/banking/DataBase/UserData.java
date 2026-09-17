package com.ga.banking.DataBase;

import com.ga.banking.models.Customer;
import com.ga.banking.models.User;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

public class UserData {
    private static final Path FILE = Path.of("DataBase", "users.txt");

    public static void saveUsers(List<User> users) {

        try {
            Files.createDirectories(FILE.getParent());
            List<String> lines = new ArrayList<>();

            for (User user : users) {

                // Only customers are stored in users.txt
                if (!(user instanceof Customer)) {
                    continue;
                }

                String line =
                        user.getId()
                                + ","
                                + user.getUsername()
                                + ","
                                + user.getEmail()
                                + ","
                                + user.getPassHash()
                                + ","
                                + user.getPassSalt()
                                + ",CUSTOMER";

                lines.add(line);
            }

            Files.write(FILE, lines);

        } catch (IOException e) {

            System.out.println(
                    "Error saving users: " + e.getMessage()
            );
        }
    }


    // =========================================================
    // LOAD USERS
    // =========================================================

    public static List<User> loadUsers() {

        List<User> users = new ArrayList<>();

        System.out.println(
                "Looking for: " + FILE.toAbsolutePath()
        );

        System.out.println(
                "File exists: " + Files.exists(FILE)
        );

        try {

            if (!Files.exists(FILE)) {
                return users;
            }

            List<String> lines =
                    Files.readAllLines(FILE);


            for (String line : lines) {

                // Ignore empty lines
                if (line.isBlank()) {
                    continue;
                }


                // Split the record
                String[] data =
                        line.split(",", -1);


                // Validate record
                if (data.length < 6) {

                    System.out.println(
                            "Skipping invalid user record: "
                                    + line
                    );

                    continue;
                }


                // Remove unwanted spaces
                int id =
                        Integer.parseInt(
                                data[0].trim()
                        );

                String username =
                        data[1].trim();

                String email =
                        data[2].trim();

                String passHash =
                        data[3].trim();

                String passSalt =
                        data[4].trim();

                String role =
                        data[5].trim();


                // Create customer
                if (role.equals("CUSTOMER")) {

                    Customer customer =
                            new Customer(
                                    id,
                                    username,
                                    email,
                                    passHash,
                                    passSalt
                            );

                    users.add(customer);
                }
            }

        } catch (IOException | NumberFormatException e) {

            System.out.println(
                    "Error loading users: "
                            + e.getMessage()
            );
        }

        return users;
    }
}
