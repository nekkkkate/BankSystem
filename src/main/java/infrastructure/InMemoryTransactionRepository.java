package infrastructure;

import application.models.Account;
import application.models.transactions.Transaction;
import application.repositories.TransactionRepository;

import java.util.*;

/**
 * This class implements the TransactionRepository interface and provides methods for managing transactions in memory.
 */
public class InMemoryTransactionRepository implements TransactionRepository {

    /**
     * A map to store transactions by their unique IDs.
     */
    private final Map<UUID, Transaction> transactions = new HashMap<>();

    /**
     * A list to store all transactions.
     */
    private final List<Transaction> transactionList = new ArrayList<>();

    /**
     * Saves a transaction to the repository.
     *
     * @param transaction The transaction to be saved.
     */
    @Override
    public void save(Transaction transaction) {
        transactions.put(transaction.getId(), transaction);
        transactionList.add(transaction);
    }

    /**
     * Retrieves all transactions associated with a specific account.
     *
     * @param account The account for which transactions are to be retrieved.
     * @return A list of transactions associated with the given account.
     */
    @Override
    public List<Transaction> getAccountTransactions(Account account) {
        List<Transaction> result = new ArrayList<>();
        for (Transaction transaction : transactionList) {
            if (transaction.getAccount().equals(account)) {
                result.add(transaction);
            }
        }
        return result;
    }

    /**
     * Retrieves a transaction by its unique ID.
     *
     * @param id The unique ID of the transaction to be retrieved.
     * @return The transaction with the given ID, or null if no such transaction exists.
     */
    @Override
    public Transaction getTransactionByID(UUID id) {
        return transactions.get(id);
    }

    /**
     * Retrieves all transactions stored in the repository.
     *
     * @return A list of all transactions.
     */
    @Override
    public List<Transaction> getAllTransactions() {
        return transactionList;
    }
}