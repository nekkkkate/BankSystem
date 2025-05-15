package application.service;

import application.contracts.AccountOperations;
import application.exceptions.MaxAccountsReachedException;
import application.models.Account;
import application.models.User;
import application.repositories.AccountRepository;

import java.math.BigDecimal;
import java.rmi.server.UID;
import java.util.UUID;

/**
 * This class provides services related to account operations.
 * It implements the {@link AccountOperations} interface.
 */
public class AccountService implements AccountOperations {

    private final AccountRepository accountRepository;

    /**
     * Constructs a new instance of {@link AccountService}.
     *
     * @param accountRepository the repository for managing accounts
     */
    public AccountService(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

    /**
     * Creates a new account for the given user.
     *
     * @param user the user for whom the account is being created
     * @return the newly created account
     */
    @Override
    public Account createAccount(User user) throws MaxAccountsReachedException {
        Account account = new Account(user);
        accountRepository.save(account);
        return account;
    }

    /**
     * Retrieves an account by its unique identifier (UID).
     *
     * @param uid the UID of the account to retrieve
     * @return the account with the specified UID, or {@code null} if no such account exists
     */
    @Override
    public Account getAccountByUid(UUID uid) {
        return accountRepository.findById(uid);
    }

    /**
     * Deletes an account from the repository.
     *
     * @param account the account to be deleted
     */
    @Override
    public void delete(Account account) {
        accountRepository.delete(account);
    }

    /**
     * Checks if an account with the specified UID exists in the repository.
     *
     * @param uuid the UID of the account to check
     * @return {@code true} if an account with the specified UID exists, {@code false} otherwise
     */
    @Override
    public boolean exists(UUID uuid) {
        return accountRepository.exists(uuid);
    }

    /**
     * Retrieves the balance of an account by its unique identifier (UID).
     *
     * @param uuid the UID of the account
     * @return the balance of the account with the specified UID
     */
    @Override
    public BigDecimal getBalanceByAccountID(UUID uuid) {
        Account account = accountRepository.findById(uuid);
        return account.getBalance();
    }
}