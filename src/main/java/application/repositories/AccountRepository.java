package application.repositories;

import application.models.Account;
import application.models.User;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

/**
 * This interface provides methods for interacting with account data in the application.
 */
public interface AccountRepository {

    /**
     * Finds an account by its unique identifier.
     *
     * @param accountId The unique identifier of the account to find.
     * @return The account with the specified identifier, or {@code null} if no such account exists.
     */
    Account findById(UUID accountId);

    /**
     * Saves a new account or updates an existing account in the repository.
     *
     * @param account The account to save.
     */
    void save(Account account);

    /**
     * Deletes an account from the repository.
     *
     * @param account The account to delete.
     */
    void delete(Account account);

    /**
     * Checks if an account with the specified identifier exists in the repository.
     *
     * @param accountId The unique identifier of the account to check.
     * @return {@code true} if an account with the specified identifier exists, {@code false} otherwise.
     */
    boolean exists(UUID accountId);

    /**
     * Retrieves a list of accounts associated with a specific user.
     *
     * @param user The user whose accounts to retrieve.
     * @return A list of accounts associated with the specified user.
     */
    List<Account> getAccountsByUser(User user);

    /**
     * Deletes all accounts associated with a specific user.
     *
     * @param user The user whose accounts to delete.
     */
    void deleteAllUsersAccount(User user);

    /**
     * Retrieves the current balance of an account.
     *
     * @param account The account whose balance to retrieve.
     * @return The current balance of the specified account.
     */
    BigDecimal getBalance(Account account);
}