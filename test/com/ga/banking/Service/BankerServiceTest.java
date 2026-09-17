package com.ga.banking.Service;

import com.ga.banking.enums.AccountType;
import com.ga.banking.enums.MasterCardType;
import com.ga.banking.enums.RequestStatus;
import com.ga.banking.models.AccountRequest;
import com.ga.banking.models.Customer;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class BankerServiceTest {

    private Customer createCustomer() {
        return new Customer(
                1,
                "noor",
                "noor@gmail.com",
                "123456"
        );
    }

    private AccountRequest createRequest(int requestId) {
        return new AccountRequest(
                requestId,
                MasterCardType.STANDARD,
                AccountType.CHECKING,
                createCustomer(),
                "123456"
        );
    }


    @Test
    void testApproveRequest() {

        List<AccountRequest> requests = new ArrayList<>();

        AccountRequest request = createRequest(1);
        requests.add(request);

        BankerService service = new BankerService(requests);

        AccountRequest result =
                service.approveRequest(
                        1,
                        MasterCardType.TITANIUM
                );

        assertNotNull(result);
        assertEquals(1, result.getRequestId());
        assertEquals(
                RequestStatus.APPROVED,
                result.getStatus()
        );
        assertEquals(
                MasterCardType.TITANIUM,
                result.getApprovedMC()
        );
    }


    @Test
    void testRejectRequest() {

        List<AccountRequest> requests = new ArrayList<>();

        AccountRequest request = createRequest(2);
        requests.add(request);

        BankerService service = new BankerService(requests);

        service.rejectRequest(2);

        assertEquals(
                RequestStatus.REJECTED,
                request.getStatus()
        );

        assertNull(request.getApprovedMC());
    }


    @Test
    void testApproveRequestNotFound() {

        List<AccountRequest> requests = new ArrayList<>();

        requests.add(createRequest(1));

        BankerService service = new BankerService(requests);

        AccountRequest result =
                service.approveRequest(
                        999,
                        MasterCardType.PLATINUM
                );

        assertNull(result);
    }


    @Test
    void testRejectRequestNotFound() {

        List<AccountRequest> requests = new ArrayList<>();

        AccountRequest request = createRequest(1);
        requests.add(request);

        BankerService service = new BankerService(requests);

        service.rejectRequest(999);

        assertEquals(
                RequestStatus.PENDING,
                request.getStatus()
        );
    }


    @Test
    void testCannotApproveAlreadyProcessedRequest() {

        List<AccountRequest> requests = new ArrayList<>();

        AccountRequest request = createRequest(3);
        requests.add(request);

        BankerService service = new BankerService(requests);

        service.approveRequest(
                3,
                MasterCardType.STANDARD
        );

        AccountRequest result =
                service.approveRequest(
                        3,
                        MasterCardType.PLATINUM
                );

        assertNull(result);

        assertEquals(
                RequestStatus.APPROVED,
                request.getStatus()
        );

        assertEquals(
                MasterCardType.STANDARD,
                request.getApprovedMC()
        );
    }


    @Test
    void testCannotApproveRejectedRequest() {

        List<AccountRequest> requests = new ArrayList<>();

        AccountRequest request = createRequest(4);
        requests.add(request);

        BankerService service = new BankerService(requests);

        service.rejectRequest(4);

        AccountRequest result =
                service.approveRequest(
                        4,
                        MasterCardType.PLATINUM
                );

        assertNull(result);

        assertEquals(
                RequestStatus.REJECTED,
                request.getStatus()
        );

        assertNull(request.getApprovedMC());
    }


    @Test
    void testCannotRejectAlreadyProcessedRequest() {

        List<AccountRequest> requests = new ArrayList<>();

        AccountRequest request = createRequest(5);
        requests.add(request);

        BankerService service = new BankerService(requests);

        service.rejectRequest(5);

        // Try to reject again
        service.rejectRequest(5);

        assertEquals(
                RequestStatus.REJECTED,
                request.getStatus()
        );
    }
}
