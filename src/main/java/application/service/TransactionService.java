package application.service;

import application.contracts.TransactionsOperations;
import application.exceptions.NotEnoughFundsException;
import application.models.Account;
import application.models.transactions.Transaction;
import application.models.transactions.TransactionType;
import application.repositories.AccountRepository;
import application.repositories.TransactionRepository;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

/**
 * This class provides services related to transactions, such as deposit, withdrawal, transfer, and balance inquiry.
 * It interacts with the repositories to perform operations on accounts and transactions.
 */
public class TransactionService implements TransactionsOperations {

    private final TransactionRepository transactionRepository;
    private final AccountRepository accountRepository;

    /**
     * Constructs a new instance of TransactionService.
     *
     * @param transactionRepository the repository for managing transactions
     * @param accountRepository     the repository for managing accounts
     */
    public TransactionService(TransactionRepository transactionRepository, AccountRepository accountRepository) {
        this.transactionRepository = transactionRepository;
        this.accountRepository = accountRepository;
    }

    @Override
    public void createTransaction(Account account, BigDecimal amount, TransactionType type) {
        Transaction transaction = new Transaction(account, amount, type);
        transactionRepository.save(transaction);
    }

    @Override
    public void createTransaction(Account account, BigDecimal amount, TransactionType type, Account recipient) {
        transactionRepository.save(new Transaction(account, amount, type, recipient));
    }

    public void createTransaction(Account account, TransactionType type) {
        transactionRepository.save(new Transaction(account, type));
    }

    @Override
    public void deposit(Account account, BigDecimal amount) {
        createTransaction(account, amount, TransactionType.DEPOSIT);
        account.deposit(amount);
        accountRepository.save(account);
    }

    @Override
    public void withdrawal(Account account, BigDecimal amount) throws NotEnoughFundsException {
        createTransaction(account, amount, TransactionType.WITHDRAWAL);
        account.withdrawal(amount);
        accountRepository.save(account);
    }

    @Override
    public void transfer(Account account, Account recipient, BigDecimal amount) throws NotEnoughFundsException {
        createTransaction(account, amount, TransactionType.TRANSFER, recipient);
        account.withdrawal(amount);
        recipient.deposit(amount);
        accountRepository.save(account);
        accountRepository.save(recipient);
    }

    @Override
    public BigDecimal getBalance(Account account) {
        createTransaction(account, TransactionType.GET_BALANCE);
        return accountRepository.getBalance(account);
    }

    @Override
    public List<Transaction> getAccountsTransactions(Account account) {
        return transactionRepository.getAccountTransactions(account);
    }

    @Override
    public List<Transaction> getAllTransactions() {
        return transactionRepository.getAllTransactions();
    }

    /**
     * Retrieves a transaction by its unique identifier.
     *
     * @param id the unique identifier of the transaction
     * @return the transaction with the specified ID, or null if not found
     */
    Transaction getTransactionByUID(UUID id) {
        return transactionRepository.getTransactionByID(id);
    }
}