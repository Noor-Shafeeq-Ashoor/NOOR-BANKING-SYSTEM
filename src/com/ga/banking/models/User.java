package com.ga.banking.models;

public abstract class User {
    private int id;
    private String username;
    private String email;

    private String passHash;
    private String passSalt;
    private int Attempts;
    private long lock;

    public User(int id, String username, String email, String passHash, String passSalt) {

        this.id = id;
        this.username = username;
        this.email = email;

        this.passHash = passHash;
        this.passSalt = passSalt;
        this.Attempts = 0;
        this.lock = 0;
    }

    public int getId() {return id;}
    public void setId(int id) {this.id = id;}

    public String getUsername() {return username;}
    public void setUsername(String username) {this.username = username;}

    public String getEmail() {return email;}
    public void setEmail(String email) {this.email = email;}

    public String getPassHash() {return passHash;}
    public void setPassHash(String passHash) {this.passHash = passHash;}

    public String getPassSalt() {return passSalt;}
    public void setPassSalt(String passSalt) {this.passSalt = passSalt;}

    public int getAttempts() {return Attempts;}
    public void setAttempts(int Attempts) {this.Attempts = Attempts;}

    public long getLock() {return lock;}
    public void setLock(long lock) {this.lock = lock;}



    public void increaseAttempts() {
        Attempts++;
    }

    public void resetAttempts() {
        Attempts = 0;
    }

    public boolean isLocked() {
        return System.currentTimeMillis() < lock;
    }

    public void lockOneMin() {
        lock = System.currentTimeMillis() + (60 * 1000);
    }

    public void unlock() {
        lock = 0;
        Attempts = 0;
    }
}