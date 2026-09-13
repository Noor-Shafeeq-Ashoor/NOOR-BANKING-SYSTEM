package com.ga.banking.Service;

import com.ga.banking.enums.MasterCardType;
import com.ga.banking.enums.RequestStatus;
import com.ga.banking.models.AccountRequest;

import java.util.List;

public class BankerService {
    private List<AccountRequest> requests;

    public BankerService(List<AccountRequest> requests) {
        this.requests = requests;
    }

    public void viewRequests() {

        for (AccountRequest request : requests) {

            System.out.println("ACCOUNT REQUEST ");
            System.out.println("Request ID: " + request.getRequestId());
            System.out.println("Customer: " + request.getCustomer().getName());
            System.out.println("Account Type: " + request.getAccountType());
            System.out.println("Requested Mastercard: " + request.getRequestedMC());
            System.out.println("Status: " + request.getStatus());
            System.out.println();
        }
    }

    public AccountRequest approveRequest(
            int requestId,
            MasterCardType approvedMC) {

        for (AccountRequest request : requests) {

            if (request.getRequestId() == requestId) {

                // Request must still be pending
                if (request.getStatus() != RequestStatus.PENDING) {

                    System.out.println(
                            "This request has already been processed."
                    );

                    return null;
                }

                // Approve the request
                boolean approved =
                        request.approve(approvedMC);

                if (approved) {

                    System.out.println(
                            "Request approved."
                    );

                    return request;
                }

                return null;
            }
        }

        System.out.println("Request not found.");

        return null;
    }


    // REJECT REQUEST


    public void rejectRequest(int requestId) {

        for (AccountRequest request : requests) {

            if (request.getRequestId() == requestId) {

                // Request must still be pending
                if (request.getStatus() != RequestStatus.PENDING) {

                    System.out.println(
                            "This request has already been processed."
                    );

                    return;
                }

                // Reject the request
                boolean rejected =
                        request.reject();

                if (rejected) {

                    System.out.println(
                            "Request rejected."
                    );
                }

                return;
            }
        }

        System.out.println("Request not found.");
    }
}
