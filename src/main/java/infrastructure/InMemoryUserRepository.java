package infrastructure;

import application.models.Account;
import application.models.User;
import application.repositories.AccountRepository;
import application.repositories.UserRepository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

/**
 * This class implements the UserRepository interface and provides methods for managing user data in memory.
 * It uses a HashMap to store user objects, with their unique IDs as keys.
 *
 * @author Tabnine team
 * @version 1.0
 */
public class InMemoryUserRepository implements UserRepository {

    /**
     * A HashMap to store user objects, with their unique IDs as keys.
     */
    private final Map<UUID, User> users = new HashMap<>();

    /**
     * The AccountRepository instance used to retrieve and delete accounts associated with users.
     */
    private final AccountRepository accountRepository;

    /**
     * Constructs a new InMemoryUserRepository instance.
     *
     * @param accountRepository The AccountRepository instance to be used.
     */
    public InMemoryUserRepository(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

    /**
     * Retrieves a list of accounts associated with the given user.
     *
     * @param user The user whose accounts are to be retrieved.
     * @return A list of accounts associated with the given user.
     */
    @Override
    public List<Account> getAccounts(User user) {
        return accountRepository.getAccountsByUser(user);
    }

    /**
     * Retrieves a user object based on the given unique ID.
     *
     * @param id The unique ID of the user to be retrieved.
     * @return The user object with the given unique ID, or null if no such user exists.
     */
    @Override
    public User getUserByUID(UUID id) {
        return users.get(id);
    }

    /**
     * Saves a new user object to the repository.
     *
     * @param user The user object to be saved.
     */
    @Override
    public void save(User user) {
        users.put(user.getUserId(), user);
    }

    /**
     * Deletes a user object from the repository and all associated accounts.
     *
     * @param user The user object to be deleted.
     */
    @Override
    public void delete(User user) {
        users.remove(user.getUserId());
        accountRepository.deleteAllUsersAccount(user);
    }
}