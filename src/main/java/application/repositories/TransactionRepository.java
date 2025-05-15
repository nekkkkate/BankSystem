package application.repositories;

import application.models.Account;
import application.models.transactions.Transaction;

import java.util.List;
import java.util.UUID;

/**
 * This interface provides methods for interacting with transaction data.
 */
public interface TransactionRepository {

    /**
     * Saves a transaction to the repository.
     *
     * @param transaction The transaction to be saved.
     */
    void save(Transaction transaction);

    /**
     * Retrieves a list of transactions associated with a specific account.
     *
     * @param account The account for which transactions are to be retrieved.
     * @return A list of transactions associated with the given account.
     */
    List<Transaction> getAccountTransactions(Account account);

    /**
     * Retrieves a transaction by its unique identifier.
     *
     * @param id The unique identifier of the transaction to be retrieved.
     * @return The transaction with the given unique identifier, or null if not found.
     */
    Transaction getTransactionByID(UUID id);

    /**
     * Retrieves all transactions from the repository.
     *
     * @return A list of all transactions in the repository.
     */
    List<Transaction> getAllTransactions();
}