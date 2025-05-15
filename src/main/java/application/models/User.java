package application.models;

import application.exceptions.MaxAccountsReachedException;

import java.util.UUID;

/**
 * Represents a user in the application.
 */
public class User {

    private String username;
    private final UUID id = UUID.randomUUID();
    private String password;
    private int numberOfAccounts = 0;
    private int maxNumberOfAccounts;

    /**
     * Constructs a new user with the given username, password, and maximum number of accounts.
     *
     * @param username The username of the user.
     * @param password The password of the user.
     * @param size     The maximum number of accounts this user can have.
     */
    public User(String username, String password, int size) {
        this.username = username;
        this.password = password;
        this.maxNumberOfAccounts = size;
    }

    /**
     * Returns the unique identifier of this user.
     *
     * @return The unique identifier of this user.
     */
    public UUID getUserId() {
        return id;
    }

    /**
     * Returns the username of this user.
     *
     * @return The username of this user.
     */
    public String getUsername() {
        return username;
    }

    /**
     * Sets the username of this user.
     *
     * @param username The new username of this user.
     */
    public void setUsername(String username) {
        this.username = username;
    }

    /**
     * Sets the password of this user.
     *
     * @param password The new password of this user.
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * Returns the number of accounts this user currently has.
     *
     * @return The number of accounts this user currently has.
     */
    public int getNumberOfAccounts() {
        return numberOfAccounts;
    }

    /**
     * Returns the maximum number of accounts this user can have.
     *
     * @return The maximum number of accounts this user can have.
     */
    public int getMaxNumberOfAccounts() {
        return maxNumberOfAccounts;
    }

    /**
     * Sets the maximum number of accounts this user can have.
     *
     * @param numberOfAccounts The new maximum number of accounts this user can have.
     */
    public void setMaxNumberOfAccounts(int numberOfAccounts) {
        this.maxNumberOfAccounts = numberOfAccounts;
    }

    /**
     * Checks if the given password matches the password of this user.
     *
     * @param password The password to check.
     * @return True if the given password matches the password of this user, false otherwise.
     */
    public boolean isPasswordCorrect(String password) {
        return this.password.equals(password);
    }

    /**
     * Adds a new account to this user if the maximum number of accounts has not been reached.
     *
     * @throws MaxAccountsReachedException If the maximum number of accounts has been reached.
     */
    public void addAccount() throws MaxAccountsReachedException {
        if (numberOfAccounts < maxNumberOfAccounts) {
            numberOfAccounts++;
        } else {
            throw new MaxAccountsReachedException("Maximum number of accounts reached. Max allowed: " + maxNumberOfAccounts);
        }
    }
}