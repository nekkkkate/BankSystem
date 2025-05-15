package application.service;

import application.contracts.UserOperation;
import application.models.Account;
import application.models.User;
import application.repositories.UserRepository;

import java.util.List;
import java.util.UUID;

/**
 * This class provides user-related operations such as creating, updating, deleting, and retrieving user information.
 * It interacts with the {@link UserRepository} to perform these operations.
 */
public class UserService implements UserOperation {

    /**
     * The repository for user-related operations.
     */
    public UserRepository userRepository;

    /**
     * Constructs a new UserService instance with the given user repository.
     *
     * @param userRepository the repository for user-related operations
     */
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    /**
     * Retrieves a list of accounts associated with the given user.
     *
     * @param user the user whose accounts are to be retrieved
     * @return a list of accounts associated with the given user
     */
    @Override
    public List<Account> getAccounts(User user) {
        return userRepository.getAccounts(user);
    }

    /**
     * Retrieves a user by their unique identifier (UID).
     *
     * @param id the UID of the user to be retrieved
     * @return the user with the given UID, or null if no such user exists
     */
    @Override
    public User getUserByUID(UUID id) {
        return userRepository.getUserByUID(id);
    }

    /**
     * Updates the given user's information, including their username and maximum number of accounts.
     * The update is only performed if the provided password is correct.
     *
     * @param user        the user to be updated
     * @param changedUser the updated user information
     * @param password    the password used to authenticate the update
     */
    @Override
    public void update(User user, User changedUser, String password) {
        if (user.isPasswordCorrect(password)) {
            user.setUsername(changedUser.getUsername());
            user.setMaxNumberOfAccounts(changedUser.getMaxNumberOfAccounts());
            userRepository.save(user);
        }
    }

    /**
     * Changes the given user's password.
     * The change is only performed if the provided new password is not the same as the last password.
     *
     * @param user         the user whose password is to be changed
     * @param lastPassword the user's last password
     * @param newPassword  the new password to be set
     */
    @Override
    public void changePassword(User user, String lastPassword, String newPassword) {
        if (user.isPasswordCorrect(newPassword)) {
            user.setPassword(lastPassword);
            userRepository.save(user);
        }
    }

    /**
     * Creates a new user with the given name, password, and maximum number of accounts.
     *
     * @param name        the username of the new user
     * @param password    the password of the new user
     * @param maxAccounts the maximum number of accounts the new user can have
     * @return the newly created user
     */
    @Override
    public User createUser(String name, String password, int maxAccounts) {
        User user = new User(name, password, maxAccounts);
        userRepository.save(user);
        return user;
    }

    /**
     * Deletes the given user from the system.
     *
     * @param user the user to be deleted
     */
    @Override
    public void delete(User user) {
        userRepository.delete(user);
    }
}