package prestntation;

import application.contracts.AccountOperations;
import application.contracts.TransactionsOperations;
import application.contracts.UserOperation;
import application.exceptions.MaxAccountsReachedException;
import application.exceptions.NotEnoughFundsException;
import application.models.Account;
import application.models.User;
import application.models.transactions.Transaction;
import application.repositories.AccountRepository;
import application.repositories.TransactionRepository;
import application.repositories.UserRepository;
import application.service.AccountService;
import application.service.TransactionService;
import application.service.UserService;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

/**
 * This class represents the main functionality of the banking system.
 * It provides methods for user management, account operations, and transaction handling.
 *
 * @author ekaterina
 */
public class BankSystem {

    private final AccountOperations accountService;
    private final TransactionsOperations transactionService;
    private final UserOperation userService;
    private final String admin;

    /**
     * Constructs a new BankSystem instance.
     *
     * @param admin The name of the system administrator.
     * @param accountRepository The repository for managing accounts.
     * @param transactionRepository The repository for managing transactions.
     * @param userRepository The repository for managing users.
     */
    public BankSystem(String admin, AccountRepository accountRepository, TransactionRepository transactionRepository, UserRepository userRepository) {
        this.accountService = new AccountService(accountRepository);
        this.transactionService = new TransactionService(transactionRepository, accountRepository);
        this.userService = new UserService(userRepository);
        this.admin = admin;
    }

    /**
     * Creates a new user with the given name, password, and maximum number of accounts.
     *
     * @param name The name of the user.
     * @param password The password of the user.
     * @param maxAccounts The maximum number of accounts the user can have.
     * @return The created user.
     */
    public User createUser(String name, String password, int maxAccounts) {
        return userService.createUser(name, password, maxAccounts);
    }

    /**
     * Deletes the given user from the system.
     *
     * @param user The user to be deleted.
     */
    public void deleteUser(User user) {
        userService.delete(user);
    }

    /**
     * Creates a new account for the given user.
     *
     * @param user The user for whom the account is being created.
     * @return The created account.
     */
    public Account createAccount(User user) throws MaxAccountsReachedException {
        return accountService.createAccount(user);
    }

    /**
     * Deposits the given amount into the specified account.
     *
     * @param account The account into which the deposit is being made.
     * @param amount The amount to be deposited.
     */
    public void deposit(Account account, BigDecimal amount) {
        transactionService.deposit(account, amount);
    }

    /**
     * Withdraws the given amount from the specified account.
     *
     * @param account The account from which the withdrawal is being made.
     * @param amount The amount to be withdrawn.
     */
    public void withdrawal(Account account, BigDecimal amount) throws NotEnoughFundsException {
        transactionService.withdrawal(account, amount);
    }

    /**
     * Transfers the given amount from the specified account to the recipient account.
     *
     * @param account The account from which the transfer is being made.
     * @param recipient The account to which the transfer is being made.
     * @param amount The amount to be transferred.
     */
    public void transfer(Account account, Account recipient, BigDecimal amount) throws NotEnoughFundsException {
        transactionService.transfer(account, recipient, amount);
    }

    /**
     * Retrieves the current balance of the specified account.
     *
     * @param account The account whose balance is being retrieved.
     * @return The current balance of the account.
     */
    public BigDecimal getBalance(Account account) {
        return transactionService.getBalance(account);
    }

    /**
     * Retrieves the history of all transactions.
     *
     * @return A list of all transactions.
     */
    public List<Transaction> getHistoryOfTransaction() {
        return transactionService.getAllTransactions();
    }

    /**
     * Retrieves the name of the system administrator.
     *
     * @return The name of the system administrator.
     */
    public String getAdmin() {
        return admin;
    }

    /**
     * Retrieves the unique identifier of the specified account.
     *
     * @param account The account whose unique identifier is being retrieved.
     * @return The unique identifier of the account.
     */
    public UUID getAccountId(Account account){
        return account.getAccountId();
    }

    /**
     * Retrieves the account with the given unique identifier.
     *
     * @param id The unique identifier of the account.
     * @return The account with the given unique identifier.
     */
    public Account getAccountByIdHashCode(UUID id){
        return accountService.getAccountByUid(id);
    }
}