package application.contracts;

import application.exceptions.MaxAccountsReachedException;
import application.models.Account;
import application.models.User;

import java.math.BigDecimal;
import java.util.UUID;

/**
 * This interface defines the operations related to account management.
 */
public interface AccountOperations {

    /**
     * Creates a new account for the given user.
     *
     * @param user The user for whom the account is being created.
     * @return The newly created account.
     */
    Account createAccount(User user) throws MaxAccountsReachedException;

    /**
     * Retrieves the account with the given unique identifier (UID).
     *
     * @param uid The unique identifier of the account to retrieve.
     * @return The account with the given UID, or null if no such account exists.
     */
    Account getAccountByUid(UUID uid);

    /**
     * Deletes the given account from the system.
     *
     * @param account The account to be deleted.
     */
    void delete(Account account);

    /**
     * Checks if an account with the given unique identifier (UID) exists in the system.
     *
     * @param uuid The unique identifier of the account to check.
     * @return True if the account exists, false otherwise.
     */
    boolean exists(UUID uuid);

    /**
     * Retrieves the current balance of the account with the given unique identifier (UID).
     *
     * @param uuid The unique identifier of the account.
     * @return The current balance of the account, or null if no such account exists.
     */
    BigDecimal getBalanceByAccountID(UUID uuid);
}