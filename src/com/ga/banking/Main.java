
package com.ga.banking;
import com.ga.banking.DataBase.AccountData;
import com.ga.banking.DataBase.RequestData;
import com.ga.banking.DataBase.TransactionData;
import com.ga.banking.DataBase.UserData;
import com.ga.banking.Service.Authentication;
import com.ga.banking.Service.CustomerService;
import com.ga.banking.models.AccountRequest;
import com.ga.banking.models.Banker;
import com.ga.banking.models.Customer;
import com.ga.banking.models.User;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);


        // =========================================================
        // LOAD USERS
        // =========================================================

        List<User> users =
                UserData.loadUsers();


        // =========================================================
        // BUILD CUSTOMER LIST
        // =========================================================

        List<Customer> customers =
                new ArrayList<>();

        for (User user : users) {

            if (user instanceof Customer) {

                customers.add(
                        (Customer) user
                );
            }
        }


        // =========================================================
        // PRINT LOADED DATA
        // =========================================================

        System.out.println(
                "Users loaded: "
                        + users.size()
        );

        System.out.println(
                "Customers loaded: "
                        + customers.size()
        );


        for (Customer customer : customers) {

            System.out.println(
                    "Customer ID: "
                            + customer.getId()
                            + " | Username: "
                            + customer.getUsername()
            );
        }


        // =========================================================
        // LOAD ACCOUNTS
        // =========================================================

        AccountData.loadAccounts(
                customers
        );


        // =========================================================
        // LOAD TRANSACTIONS
        // =========================================================

        TransactionData.loadTransactions(
                customers
        );


        // =========================================================
        // SERVICES
        // =========================================================

        Authentication authenticationService =
                new Authentication(users);

        CustomerService customerService =
                new CustomerService(users);


        // =========================================================
        // LOAD ACCOUNT REQUESTS
        // =========================================================

        List<AccountRequest> requests =
                RequestData.loadRequests(
                        customers
                );


        // =========================================================
        // MAIN PROGRAM
        // =========================================================

        boolean running = true;


        while (running) {

            System.out.println();

            System.out.println(
                    "===== BANKING SYSTEM ====="
            );

            System.out.println("1. Customer");
            System.out.println("2. Banker");
            System.out.println("3. Exit");

            System.out.print("Choose: ");

            int choice =
                    scanner.nextInt();

            scanner.nextLine();


            // =====================================================
            // CUSTOMER
            // =====================================================

            if (choice == 1) {

                boolean customerMenuRunning =
                        true;


                while (customerMenuRunning) {

                    System.out.println();

                    System.out.println(
                            "===== CUSTOMER ====="
                    );

                    System.out.println("1. Login");
                    System.out.println("2. Register");
                    System.out.println("3. Back");

                    System.out.print("Choose: ");

                    int customerChoice =
                            scanner.nextInt();

                    scanner.nextLine();


                    // =================================================
                    // CUSTOMER LOGIN
                    // =================================================

                    if (customerChoice == 1) {

                        System.out.println();

                        System.out.println(
                                "===== CUSTOMER LOGIN ====="
                        );

                        System.out.print("Email: ");

                        String email =
                                scanner.nextLine();

                        System.out.print("Password: ");

                        String password =
                                scanner.nextLine();


                        User loggedInUser =
                                authenticationService.login(
                                        email,
                                        password
                                );


                        if (loggedInUser != null
                                && loggedInUser instanceof Customer) {

                            Customer loggedInCustomer =
                                    (Customer) loggedInUser;


                            System.out.println();

                            System.out.println(
                                    "Login successful!"
                            );

                            System.out.println(
                                    "Welcome "
                                            + loggedInCustomer
                                            .getUsername()
                            );


                            CMenu customerMenu =
                                    new CMenu(
                                            loggedInCustomer,
                                            scanner,
                                            requests,
                                            customers
                                    );

                            customerMenu.show();


                        } else {

                            System.out.println(
                                    "Invalid email or password."
                            );
                        }


                        // =================================================
                        // CUSTOMER REGISTER
                        // =================================================

                    } else if (customerChoice == 2) {

                        System.out.println();

                        System.out.println(
                                "===== CREATE CUSTOMER ====="
                        );


                        System.out.print("Name: ");

                        String name =
                                scanner.nextLine();


                        System.out.print("Username: ");

                        String username =
                                scanner.nextLine();


                        System.out.print("Email: ");

                        String email =
                                scanner.nextLine();


                        if (customerService.usernameExists(
                                username
                        )) {

                            System.out.println();

                            System.out.println(
                                    "Username already exists."
                            );

                            continue;
                        }


                        if (customerService.emailExists(
                                email
                        )) {

                            System.out.println();

                            System.out.println(
                                    "Email already exists."
                            );

                            continue;
                        }


                        System.out.print("Password: ");

                        String password =
                                scanner.nextLine();


                        if (!Authentication.isValidPassword(
                                password
                        )) {

                            System.out.println();

                            System.out.println(
                                    "Invalid password!"
                            );

                            System.out.println(
                                    "Password must contain:"
                            );

                            System.out.println(
                                    "- At least 8 characters"
                            );

                            System.out.println(
                                    "- At least 1 uppercase letter"
                            );

                            System.out.println(
                                    "- At least 1 lowercase letter"
                            );

                            System.out.println(
                                    "- At least 1 number"
                            );

                            System.out.println(
                                    "- At least 1 special character"
                            );

                            continue;
                        }


                        // =================================================
                        // CREATE CUSTOMER
                        // =================================================

                        int id = users.stream()
                                .mapToInt(User::getId)
                                .max()
                                .orElse(0) + 1;


                        Customer newCustomer =
                                customerService.createCustomer(
                                        id,
                                        username,
                                        email,
                                        password
                                );





                        // Add to customers
                        customers.add(
                                newCustomer
                        );


                        // Save users
                        UserData.saveUsers(
                                users
                        );


                        System.out.println();

                        System.out.println(
                                "Customer created successfully!"
                        );

                        System.out.println(
                                "Welcome "
                                        + newCustomer
                                        .getUsername()
                        );


                        // =================================================
                        // BACK
                        // =================================================

                    } else if (customerChoice == 3) {

                        customerMenuRunning =
                                false;

                        System.out.println(
                                "Back..."
                        );


                    } else {

                        System.out.println(
                                "Invalid choice."
                        );
                    }
                }


                // =====================================================
                // BANKER
                // =====================================================

            } else if (choice == 2) {

                System.out.println();

                System.out.println(
                        "===== BANKER LOGIN ====="
                );


                System.out.print("Email: ");

                String email =
                        scanner.nextLine();


                System.out.print("Password: ");

                String password =
                        scanner.nextLine();


                User loggedInUser =
                        authenticationService.login(
                                email,
                                password
                        );


                if (loggedInUser != null
                        && loggedInUser instanceof Banker) {

                    System.out.println();

                    System.out.println(
                            "Banker login successful!"
                    );

                    System.out.println(
                            "Welcome "
                                    + loggedInUser
                                    .getUsername()
                    );


                    BMenu bankerMenu =
                            new BMenu(
                                    scanner,
                                    requests
                            );

                    bankerMenu.show();


                } else {

                    System.out.println(
                            "Invalid email or password."
                    );
                }


                // =====================================================
                // EXIT
                // =====================================================

            } else if (choice == 3) {

                running = false;

                System.out.println();

                System.out.println(
                        "Thank you for using the Banking System!"
                );


            } else {

                System.out.println(
                        "Invalid choice."
                );
            }
        }


        scanner.close();
    }
}
