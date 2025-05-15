package application.models;

import application.exceptions.MaxAccountsReachedException;
import application.exceptions.NotEnoughFundsException;

import java.math.BigDecimal;
import java.util.UUID;

/**
 * Represents a bank account.
 */
public class Account {

    private final UUID id = UUID.randomUUID();
    private BigDecimal balance;
    private final User user;

    /**
     * Constructs a new account for the given user.
     *
     * @param user The user to associate with this account.
     */
    public Account(User user) throws MaxAccountsReachedException {
        this.user = user;
        user.addAccount();
        balance = BigDecimal.ZERO;
    }

    /**
     * Deposits the specified amount into the account.
     *
     * @param amount The amount to deposit.
     */
    public void deposit(BigDecimal amount) {
        this.balance = balance.add(amount);
    }

    /**
     * Withdraws the specified amount from the account.
     * Throws a {@link NotEnoughFundsException} if the account does not have sufficient funds.
     *
     * @param amount The amount to withdraw.
     * @throws NotEnoughFundsException If the account does not have sufficient funds.
     */
    public void withdrawal(BigDecimal amount) throws NotEnoughFundsException {
        if (balance.compareTo(amount) >= 0) {
            this.balance = balance.subtract(amount);
        } else {
            throw new NotEnoughFundsException("Not enough funds. Current balance: " + balance);
        }
    }

    /**
     * Returns the unique identifier of this account.
     *
     * @return The unique identifier of this account.
     */
    public UUID getAccountId() {
        return id;
    }

    /**
     * Returns the user associated with this account.
     *
     * @return The user associated with this account.
     */
    public User getUser() {
        return user;
    }

    /**
     * Returns the current balance of this account.
     *
     * @return The current balance of this account.
     */
    public BigDecimal getBalance() {
        return balance;
    }
}