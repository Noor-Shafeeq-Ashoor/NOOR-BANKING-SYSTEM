package com.ga.banking.models;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class UserTest {

    private Customer createUser() {
        return new Customer(
                1,
                "noor",
                "noor@gmail.com",
                "123456"
        );
    }


    @Test
    void testUserConstructor() {

        Customer user = createUser();

        assertEquals(1, user.getId());
        assertEquals("noor", user.getUsername());
        assertEquals("noor@gmail.com", user.getEmail());

        assertNotNull(user.getPassHash());
        assertNotNull(user.getPassSalt());

        assertEquals(0, user.getAttempts());
        assertEquals(0, user.getLock());
    }


    @Test
    void testSettersAndGetters() {

        Customer user = createUser();

        user.setId(10);
        user.setUsername("newUser");
        user.setEmail("new@gmail.com");
        user.setPassHash("hash");
        user.setPassSalt("salt");
        user.setAttempts(3);
        user.setLock(12345);

        assertEquals(10, user.getId());
        assertEquals("newUser", user.getUsername());
        assertEquals("new@gmail.com", user.getEmail());
        assertEquals("hash", user.getPassHash());
        assertEquals("salt", user.getPassSalt());
        assertEquals(3, user.getAttempts());
        assertEquals(12345, user.getLock());
    }


    @Test
    void testIncreaseAttempts() {

        Customer user = createUser();

        assertEquals(0, user.getAttempts());

        user.increaseAttempts();
        assertEquals(1, user.getAttempts());

        user.increaseAttempts();
        assertEquals(2, user.getAttempts());
    }


    @Test
    void testResetAttempts() {

        Customer user = createUser();

        user.increaseAttempts();
        user.increaseAttempts();
        user.increaseAttempts();

        assertEquals(3, user.getAttempts());

        user.resetAttempts();

        assertEquals(0, user.getAttempts());
    }


    @Test
    void testLockOneMinute() {

        Customer user = createUser();

        user.lockOneMin();

        assertTrue(user.isLocked());
        assertTrue(user.getLock() > System.currentTimeMillis());
    }


    @Test
    void testUnlock() {

        Customer user = createUser();

        user.increaseAttempts();
        user.increaseAttempts();
        user.lockOneMin();

        assertTrue(user.isLocked());

        user.unlock();

        assertFalse(user.isLocked());
        assertEquals(0, user.getAttempts());
        assertEquals(0, user.getLock());
    }


    @Test
    void testUserIsNotLockedInitially() {

        Customer user = createUser();

        assertFalse(user.isLocked());
    }
}
