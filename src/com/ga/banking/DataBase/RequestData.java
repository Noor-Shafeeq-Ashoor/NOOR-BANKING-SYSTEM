package com.ga.banking.DataBase;

import com.ga.banking.enums.AccountType;
import com.ga.banking.enums.MasterCardType;
import com.ga.banking.enums.RequestStatus;
import com.ga.banking.models.AccountRequest;
import com.ga.banking.models.Customer;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

public class RequestData {

    private static final Path FILE =
            Path.of("DataBase", "requests.txt");


    // =========================
    // SAVE REQUESTS
    // =========================

    public static void saveRequests(
            List<AccountRequest> requests) {

        try {

            Files.createDirectories(
                    FILE.getParent()
            );

            List<String> lines =
                    new ArrayList<>();

            for (AccountRequest request : requests) {

                String approvedMC =
                        request.getApprovedMC() == null
                                ? "null"
                                : request.getApprovedMC().name();

                String line =
                        request.getRequestId() + "," +
                                request.getCustomer().getId() + "," +
                                request.getAccountType().name() + "," +
                                request.getRequestedMC().name() + "," +
                                approvedMC + "," +
                                request.getStatus().name() + "," +
                                request.getAccountPasswordHash() + "," +
                                request.getAccountPasswordSalt();

                lines.add(line);
            }

            Files.write(FILE, lines);

        } catch (IOException e) {

            System.out.println(
                    "Error saving requests: "
                            + e.getMessage()
            );
        }
    }


    // =========================
    // LOAD REQUESTS
    // =========================

    public static List<AccountRequest> loadRequests(
            List<Customer> customers) {

        List<AccountRequest> requests =
                new ArrayList<>();

        try {

            if (!Files.exists(FILE)) {
                return requests;
            }

            List<String> lines =
                    Files.readAllLines(FILE);

            for (String line : lines) {

                if (line.isBlank()) {
                    continue;
                }

                String[] data =
                        line.split(",");

                if (data.length < 8) {

                    System.out.println(
                            "Skipping invalid request record: "
                                    + line
                    );

                    continue;
                }

                int requestId =
                        Integer.parseInt(data[0]);

                int customerId =
                        Integer.parseInt(data[1]);

                AccountType accountType =
                        AccountType.valueOf(data[2]);

                MasterCardType requestedMC =
                        MasterCardType.valueOf(data[3]);

                MasterCardType approvedMC =
                        data[4].equals("null")
                                ? null
                                : MasterCardType.valueOf(data[4]);

                RequestStatus status =
                        RequestStatus.valueOf(data[5]);

                String passwordHash =
                        data[6];

                String passwordSalt =
                        data[7];


                // =========================
                // FIND CUSTOMER
                // =========================

                Customer customer = null;

                for (Customer c : customers) {

                    if (c.getId() == customerId) {
                        customer = c;
                        break;
                    }
                }

                if (customer == null) {

                    System.out.println(
                            "Customer not found for request ID: "
                                    + requestId
                    );

                    continue;
                }


                // =========================
                // REBUILD REQUEST
                // =========================

                AccountRequest request =
                        new AccountRequest(
                                requestId,
                                requestedMC,
                                accountType,
                                customer,
                                passwordHash,
                                passwordSalt,
                                approvedMC,
                                status
                        );

                requests.add(request);
            }

        } catch (
                IOException |

                IllegalArgumentException e) {

            System.out.println(
                    "Error loading requests: "
                            + e.getMessage()
            );
        }

        return requests;
    }


    // =========================
    // READ RAW FILE
    // =========================

    public static List<String> readRequests() {

        try {

            if (!Files.exists(FILE)) {
                return new ArrayList<>();
            }

            return Files.readAllLines(FILE);

        } catch (IOException e) {

            System.out.println(
                    "Error reading requests: "
                            + e.getMessage()
            );

            return new ArrayList<>();
        }
    }
}