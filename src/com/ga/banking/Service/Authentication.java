package com.ga.banking.Service;

import com.ga.banking.DataBase.BankerData;
import com.ga.banking.models.Banker;
import com.ga.banking.models.User;

import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.SecureRandom;
import java.util.Base64;
import java.util.List;

public class Authentication {

    private List<User> users;

    private static final int SALT_LENGTH = 16;
    private static final int ITERATIONS = 600_000;
    private static final int KEY_LENGTH = 256;
    private static final int MAX_FAILED_ATTEMPTS = 3;
    private static final long LOCK_TIME = 60 * 1000;

    public Authentication(List<User> users) {
        this.users = users;}


    public User login(String email, String password) {

        User user = findUserByEmail(email);

        if (user == null) {
            System.out.println("Invalid email or password.");
            return null;}

        if (user.isLocked()) {

            long remaining = user.getLock() - System.currentTimeMillis();
            long secondsRemaining = (remaining / 1000) + 1;

            System.out.println("Account is locked. Try again in " + secondsRemaining + " seconds");
            return null;
        }

        if (user.getLock() > 0) {user.unlock();}


        boolean correctPassword = verifyPassword(password, user.getPassHash(), user.getPassSalt());

        if (correctPassword) {
            user.resetAttempts();
            return user;
        }

        user.increaseAttempts();
        System.out.println("Invalid username/email or password.");
        System.out.println("Failed attempts: " + user.getAttempts());

        if (user.getAttempts() >= MAX_FAILED_ATTEMPTS) {
            user.lockOneMin();
            System.out.println("Account locked for 1 minute.");
        }
        return null;
    }

    private User findUserByEmail(String Email){

        for (User user : users) {

            if ( user.getEmail().equalsIgnoreCase(Email)) {
                return user;
            }
        }
        for (Banker banker : BankerData.getBankers()) {

            if (banker.getEmail().equalsIgnoreCase(Email)) {
                return banker;
            }
        }
        return null;
    }

    public static boolean isValidPassword(String password) {

        if (password == null || password.length() < 8) {
            return false;
        }

        boolean hasUppercase = false;
        boolean hasLowercase = false;
        boolean hasNumber = false;
        boolean hasSpecial = false;

        for (char pass : password.toCharArray()) {

            if (Character.isUpperCase(pass)) {

                hasUppercase = true;

            }
            else if (Character.isLowerCase(pass)) {

                hasLowercase = true;

            }
            else if (Character.isDigit(pass)) {

                hasNumber = true;

            }
            else {

                hasSpecial = true;
            }
        }

        return hasUppercase && hasLowercase && hasNumber && hasSpecial;
    }


    public static String generateSalt() {

        SecureRandom secureRandom = new SecureRandom();
        byte[] salt = new byte[SALT_LENGTH];
        secureRandom.nextBytes(salt);
        return Base64.getEncoder().encodeToString(salt);
    }

    public static String hashPassword(String password, String salt) {

        try {
            byte[] saltBytes = Base64.getDecoder().decode(salt);
            PBEKeySpec spec = new PBEKeySpec(password.toCharArray(), saltBytes, ITERATIONS, KEY_LENGTH);
            SecretKeyFactory factory = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA256");

            byte[] hash = factory.generateSecret(spec).getEncoded();
            spec.clearPassword();
            return Base64.getEncoder().encodeToString(hash);

        }
        catch (Exception e) {
            throw new RuntimeException("Error hashing password.", e);
        }
    }


    public static boolean verifyPassword(String password, String storedHash, String storedSalt) {

        String calculatedHash = hashPassword(password, storedSalt);

        return MessageDigest.isEqual(calculatedHash.getBytes(StandardCharsets.UTF_8), storedHash.getBytes(StandardCharsets.UTF_8));
    }
}