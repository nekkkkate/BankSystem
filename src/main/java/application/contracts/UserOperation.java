package application.contracts;

import application.models.Account;
import application.models.User;

import java.util.List;
import java.util.UUID;

/**
 * This interface defines operations related to user management.
 */
public interface UserOperation {

    /**
     * Retrieves a list of accounts associated with a given user.
     *
     * @param user The user whose accounts are to be retrieved.
     * @return A list of accounts associated with the given user.
     */
    List<Account> getAccounts(User user);

    /**
     * Retrieves a user based on their unique identifier (UID).
     *
     * @param id The unique identifier of the user to be retrieved.
     * @return The user with the given UID, or null if no such user exists.
     */
    User getUserByUID(UUID id);

    /**
     * Updates the information of an existing user.
     *
     * @param user        The user whose information is to be updated.
     * @param changedUser The updated user information.
     * @param password    The password of the user for authentication.
     */
    void update(User user, User changedUser, String password);

    /**
     * Changes the password of a user.
     *
     * @param user         The user whose password is to be changed.
     * @param lastPassword The user's current password.
     * @param newPassword  The new password to be set.
     */
    void changePassword(User user, String lastPassword, String newPassword);

    /**
     * Creates a new user with the given name, password, and maximum number of accounts.
     *
     * @param name        The name of the new user.
     * @param password    The password of the new user.
     * @param maxAccounts The maximum number of accounts the new user can have.
     * @return The newly created user.
     */
    User createUser(String name, String password, int maxAccounts);

    /**
     * Deletes a user from the system.
     *
     * @param user The user to be deleted.
     */
    void delete(User user);
}