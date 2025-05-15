package application.contracts;

import application.exceptions.NotEnoughFundsException;
import application.models.Account;
import application.models.transactions.Transaction;
import application.models.transactions.TransactionType;

import java.math.BigDecimal;
import java.util.List;

/**
 * This interface defines the operations related to transactions in a banking application.
 */
public interface TransactionsOperations {

    /**
     * Creates a new transaction of the specified type for the given account with the specified amount.
     *
     * @param account The account for which the transaction is being created.
     * @param amount  The amount of the transaction.
     * @param type    The type of the transaction.
     */
    void createTransaction(Account account, BigDecimal amount, TransactionType type);

    /**
     * Creates a new transaction of the specified type between the given account and recipient with the specified amount.
     *
     * @param account   The account from which the transaction is being created.
     * @param amount    The amount of the transaction.
     * @param type      The type of the transaction.
     * @param recipient The account to which the transaction is being made.
     */
    void createTransaction(Account account, BigDecimal amount, TransactionType type, Account recipient);

    /**
     * Deposits the specified amount into the given account.
     *
     * @param account The account into which the deposit is being made.
     * @param amount  The amount to be deposited.
     */
    void deposit(Account account, BigDecimal amount);

    /**
     * Withdraws the specified amount from the given account.
     *
     * @param account The account from which the withdrawal is being made.
     * @param amount  The amount to be withdrawn.
     */
    void withdrawal(Account account, BigDecimal amount) throws NotEnoughFundsException;

    /**
     * Transfers the specified amount from the given account to the recipient account.
     *
     * @param account   The account from which the transfer is being made.
     * @param recipient The account to which the transfer is being made.
     * @param amount    The amount to be transferred.
     */
    void transfer(Account account, Account recipient, BigDecimal amount) throws NotEnoughFundsException;

    /**
     * Retrieves the current balance of the given account.
     *
     * @param account The account for which the balance is being retrieved.
     * @return The current balance of the account.
     */
    BigDecimal getBalance(Account account);

    /**
     * Retrieves a list of transactions associated with the given account.
     *
     * @param account The account for which the transactions are being retrieved.
     * @return A list of transactions associated with the account.
     */
    List<Transaction> getAccountsTransactions(Account account);

    /**
     * Retrieves a list of all transactions in the system.
     *
     * @return A list of all transactions in the system.
     */
    List<Transaction> getAllTransactions();
}
