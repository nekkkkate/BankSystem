package application.repositories;

import application.models.Account;
import application.models.User;

import java.util.List;
import java.util.UUID;

/**
 * This interface defines the contract for interacting with the User data.
 */
public interface UserRepository {

    /**
     * Retrieves a list of accounts associated with the given user.
     *
     * @param user The user whose accounts are to be retrieved.
     * @return A list of accounts associated with the given user.
     */
    List<Account> getAccounts(User user);

    /**
     * Retrieves a user based on the provided unique identifier (UID).
     *
     * @param id The unique identifier of the user to be retrieved.
     * @return The user with the specified UID, or null if no such user exists.
     */
    User getUserByUID(UUID id);

    /**
     * Saves the given user to the data store.
     *
     * @param user The user to be saved.
     */
    void save(User user);

    /**
     * Deletes the given user from the data store.
     *
     * @param user The user to be deleted.
     */
    void delete(User user);
}