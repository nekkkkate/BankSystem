package infrastructure;

import application.models.Account;
import application.models.User;
import application.repositories.AccountRepository;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.stream.Collectors;

/**
 * This class implements the AccountRepository interface and provides methods for managing accounts in memory.
 */
public class InMemoryAccountRepository implements AccountRepository {

    /**
     * A map to store accounts, using their unique IDs as keys.
     */
    private final Map<UUID, Account> accounts = new HashMap<>();

    @Override
    /**
     * Finds and returns the account with the given ID.
     *
     * @param findByIdHashCode The unique ID of the account to find.
     * @return The account with the given ID, or null if no such account exists.
     */
    public Account findById(UUID accountId) {
        return accounts.get(accountId);
    }

    @Override
    /**
     * Saves the given account to the repository.
     *
     * @param account The account to save.
     */
    public void save(Account account) {
        accounts.put(account.getAccountId(), account);
    }

    @Override
    /**
     * Deletes the given account from the repository.
     *
     * @param account The account to delete.
     */
    public void delete(Account account) {
        accounts.remove(account.getAccountId());
    }

    @Override
    /**
     * Checks if an account with the given ID exists in the repository.
     *
     * @param uuid The unique ID of the account to check.
     * @return True if an account with the given ID exists, false otherwise.
     */
    public boolean exists(UUID uuid) {
        return accounts.containsKey(uuid);
    }

    /**
     * Retrieves a list of accounts belonging to the given user.
     *
     * @param user The user whose accounts to retrieve.
     * @return A list of accounts belonging to the given user.
     */
    public List<Account> getAccountsByUser(User user) {
        return accounts.values().stream()
                .filter(account -> account.getUser().getUserId().equals(user.getUserId()))
                .collect(Collectors.toList());
    }

    /**
     * Deletes all accounts belonging to the given user.
     *
     * @param user The user whose accounts to delete.
     */
    public void deleteAllUsersAccount(User user) {
        accounts.values().removeIf(account -> account.getUser().getUserId().equals(user.getUserId()));
    }

    @Override
    /**
     * Retrieves the balance of the given account.
     *
     * @param account The account whose balance to retrieve.
     * @return The balance of the given account.
     */
    public BigDecimal getBalance(Account account) {
        return account.getBalance();
    }
}