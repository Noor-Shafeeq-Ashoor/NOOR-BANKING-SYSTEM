package com.ga.banking.Service;

import com.ga.banking.models.Customer;
import com.ga.banking.models.User;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class AuthenticationTest {

    // =========================
    // PASSWORD VALIDATION
    // =========================

    @Test
    void testValidPassword() {

        assertTrue(
                Authentication.isValidPassword("Password1!")
        );
    }


    @Test
    void testInvalidPasswordTooShort() {

        assertFalse(
                Authentication.isValidPassword("Pass1!")
        );
    }


    @Test
    void testInvalidPasswordWithoutUppercase() {

        assertFalse(
                Authentication.isValidPassword("password1!")
        );
    }


    @Test
    void testInvalidPasswordWithoutLowercase() {

        assertFalse(
                Authentication.isValidPassword("PASSWORD1!")
        );
    }


    @Test
    void testInvalidPasswordWithoutNumber() {

        assertFalse(
                Authentication.isValidPassword("Password!")
        );
    }


    @Test
    void testInvalidPasswordWithoutSpecialCharacter() {

        assertFalse(
                Authentication.isValidPassword("Password1")
        );
    }


    @Test
    void testNullPassword() {

        assertFalse(
                Authentication.isValidPassword(null)
        );
    }


    // =========================
    // SALT
    // =========================

    @Test
    void testGenerateSalt() {

        String salt = Authentication.generateSalt();

        assertNotNull(salt);
        assertFalse(salt.isEmpty());
    }


    @Test
    void testGenerateDifferentSalts() {

        String salt1 = Authentication.generateSalt();
        String salt2 = Authentication.generateSalt();

        assertNotEquals(salt1, salt2);
    }


    // =========================
    // HASHING
    // =========================

    @Test
    void testHashPassword() {

        String password = "Password1!";
        String salt = Authentication.generateSalt();

        String hash =
                Authentication.hashPassword(password, salt);

        assertNotNull(hash);
        assertFalse(hash.isEmpty());
    }


    @Test
    void testSamePasswordAndSaltProducesSameHash() {

        String password = "Password1!";
        String salt = Authentication.generateSalt();

        String hash1 =
                Authentication.hashPassword(password, salt);

        String hash2 =
                Authentication.hashPassword(password, salt);

        assertEquals(hash1, hash2);
    }


    @Test
    void testDifferentPasswordProducesDifferentHash() {

        String salt = Authentication.generateSalt();

        String hash1 =
                Authentication.hashPassword(
                        "Password1!",
                        salt
                );

        String hash2 =
                Authentication.hashPassword(
                        "Password2!",
                        salt
                );

        assertNotEquals(hash1, hash2);
    }


    // =========================
    // VERIFY PASSWORD
    // =========================

    @Test
    void testVerifyCorrectPassword() {

        String password = "Password1!";
        String salt = Authentication.generateSalt();

        String hash =
                Authentication.hashPassword(
                        password,
                        salt
                );

        assertTrue(
                Authentication.verifyPassword(
                        password,
                        hash,
                        salt
                )
        );
    }


    @Test
    void testVerifyWrongPassword() {

        String password = "Password1!";
        String salt = Authentication.generateSalt();

        String hash =
                Authentication.hashPassword(
                        password,
                        salt
                );

        assertFalse(
                Authentication.verifyPassword(
                        "WrongPassword1!",
                        hash,
                        salt
                )
        );
    }


    // =========================
    // LOGIN
    // =========================

    @Test
    void testSuccessfulLogin() {

        List<User> users = new ArrayList<>();

        Customer customer = new Customer(
                1,
                "noor",
                "noor@gmail.com",
                "Password1!"
        );

        users.add(customer);

        Authentication authentication =
                new Authentication(users);

        User result =
                authentication.login(
                        "noor@gmail.com",
                        "Password1!"
                );

        assertNotNull(result);
        assertEquals(customer, result);
        assertEquals(0, customer.getAttempts());
    }


    @Test
    void testLoginWithWrongPassword() {

        List<User> users = new ArrayList<>();

        Customer customer = new Customer(
                1,
                "noor",
                "noor@gmail.com",
                "Password1!"
        );

        users.add(customer);

        Authentication authentication =
                new Authentication(users);

        User result =
                authentication.login(
                        "noor@gmail.com",
                        "WrongPassword1!"
                );

        assertNull(result);
        assertEquals(1, customer.getAttempts());
    }


    @Test
    void testLoginWithUnknownEmail() {

        List<User> users = new ArrayList<>();

        Customer customer = new Customer(
                1,
                "noor",
                "noor@gmail.com",
                "Password1!"
        );

        users.add(customer);

        Authentication authentication =
                new Authentication(users);

        User result =
                authentication.login(
                        "unknown@gmail.com",
                        "Password1!"
                );

        assertNull(result);
    }


    // =========================
    // ACCOUNT LOCK
    // =========================

    @Test
    void testAccountLocksAfterThreeFailedAttempts() {

        List<User> users = new ArrayList<>();

        Customer customer = new Customer(
                1,
                "noor",
                "noor@gmail.com",
                "Password1!"
        );

        users.add(customer);

        Authentication authentication =
                new Authentication(users);

        authentication.login(
                "noor@gmail.com",
                "WrongPassword1!"
        );

        authentication.login(
                "noor@gmail.com",
                "WrongPassword1!"
        );

        authentication.login(
                "noor@gmail.com",
                "WrongPassword1!"
        );

        assertEquals(3, customer.getAttempts());
        assertTrue(customer.isLocked());
    }


    @Test
    void testLockedAccountCannotLogin() {

        List<User> users = new ArrayList<>();

        Customer customer = new Customer(
                1,
                "noor",
                "noor@gmail.com",
                "Password1!"
        );

        users.add(customer);

        Authentication authentication =
                new Authentication(users);

        customer.lockOneMin();

        User result =
                authentication.login(
                        "noor@gmail.com",
                        "Password1!"
                );

        assertNull(result);
        assertTrue(customer.isLocked());
    }


    @Test
    void testSuccessfulLoginResetsAttempts() {

        List<User> users = new ArrayList<>();

        Customer customer = new Customer(
                1,
                "noor",
                "noor@gmail.com",
                "Password1!"
        );

        users.add(customer);

        Authentication authentication =
                new Authentication(users);

        authentication.login(
                "noor@gmail.com",
                "WrongPassword1!"
        );

        assertEquals(1, customer.getAttempts());

        User result =
                authentication.login(
                        "noor@gmail.com",
                        "Password1!"
                );

        assertNotNull(result);
        assertEquals(0, customer.getAttempts());
    }
}
