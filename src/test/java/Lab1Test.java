import application.exceptions.MaxAccountsReachedException;
import application.exceptions.NotEnoughFundsException;
import application.models.Account;
import application.models.User;
import application.models.transactions.Transaction;
import application.repositories.AccountRepository;
import application.repositories.TransactionRepository;
import application.repositories.UserRepository;
import infrastructure.InMemoryAccountRepository;
import infrastructure.InMemoryTransactionRepository;
import infrastructure.InMemoryUserRepository;
import org.junit.jupiter.api.*;
import prestntation.BankSystem;

import java.math.BigDecimal;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class Lab1Test {

    @Test
    @DisplayName("Trying to create an account upper than max")
    void testAddAccountMaxReached() throws MaxAccountsReachedException {
        User user = new User("testUser", "password", 1);
        new Account(user);

        assertThrows(MaxAccountsReachedException.class, () -> {
            new Account(user);
        }, "MaxAccountsReachedException");
    }

    @Test
    @DisplayName("Trying make transaction but not have enough funds")
    void testWithdrawalInsufficientFunds() throws MaxAccountsReachedException {
        User user = new User("testUser", "password", 1);
        Account account = new Account(user);

        assertThrows(NotEnoughFundsException.class, () -> {
            account.withdrawal(new BigDecimal("101"));
        }, "InsufficientFundsException");
    }

    @Test
    @DisplayName("Trying to create an account upper than max with BankSystem")
    void testAddAccountMaxReachedInBankSystem() throws MaxAccountsReachedException {
        AccountRepository accountRepository = new InMemoryAccountRepository();
        TransactionRepository transactionRepository = new InMemoryTransactionRepository();
        UserRepository userRepository = new InMemoryUserRepository(accountRepository);

        BankSystem accountSystem = new BankSystem("kate", accountRepository, transactionRepository, userRepository);
        User Kate = accountSystem.createUser("kate", "password", 1);
        Account Kateee = accountSystem.createAccount(Kate);
        accountSystem.deposit(Kateee, new BigDecimal("100"));

        assertThrows(MaxAccountsReachedException.class, () -> {
            accountSystem.createAccount(Kate);
        }, "MaxAccountsReachedException");
    }

    @Test
    @DisplayName("Trying make transaction but not have enough funds with BankSystem")
    void testWithdrawalInsufficientFundsInBankSystem() throws MaxAccountsReachedException {
        AccountRepository accountRepository = new InMemoryAccountRepository();
        TransactionRepository transactionRepository = new InMemoryTransactionRepository();
        UserRepository userRepository = new InMemoryUserRepository(accountRepository);

        BankSystem accountSystem = new BankSystem("kate", accountRepository, transactionRepository, userRepository);
        User Kate = accountSystem.createUser("kate", "password", 1);
        Account Kateee = accountSystem.createAccount(Kate);
        accountSystem.deposit(Kateee, new BigDecimal("100"));

        assertThrows(NotEnoughFundsException.class, () -> {
            accountSystem.withdrawal(Kateee, new BigDecimal("101"));
        }, "InsufficientFundsException");
    }

    @Test
    @DisplayName("Delete user and check if accounts are deleted")
    void testDeleteUser() throws MaxAccountsReachedException {
        AccountRepository accountRepository = new InMemoryAccountRepository();
        TransactionRepository transactionRepository = new InMemoryTransactionRepository();
        UserRepository userRepository = new InMemoryUserRepository(accountRepository);

        BankSystem accountSystem = new BankSystem("kate", accountRepository, transactionRepository, userRepository);
        User user = accountSystem.createUser("kate", "password", 2);
        Account account = accountSystem.createAccount(user);

        accountSystem.deleteUser(user);

        assertNull(userRepository.getUserByUID(user.getUserId()), "User should be deleted");
        assertNull(accountRepository.findById(account.getAccountId()), "Account should be deleted");
    }

    @Test
    @DisplayName("Transfer funds between accounts")
    void testTransfer() throws NotEnoughFundsException, MaxAccountsReachedException {
        AccountRepository accountRepository = new InMemoryAccountRepository();
        TransactionRepository transactionRepository = new InMemoryTransactionRepository();
        UserRepository userRepository = new InMemoryUserRepository(accountRepository);

        BankSystem accountSystem = new BankSystem("kate", accountRepository, transactionRepository, userRepository);
        User user = accountSystem.createUser("kate", "password", 2);
        Account account1 = accountSystem.createAccount(user);
        Account account2 = accountSystem.createAccount(user);

        accountSystem.deposit(account1, new BigDecimal("200"));
        accountSystem.transfer(account1, account2, new BigDecimal("100"));

        assertEquals(new BigDecimal("100"), accountSystem.getBalance(account1), "Balance of account1 should be 100");
        assertEquals(new BigDecimal("100"), accountSystem.getBalance(account2), "Balance of account2 should be 100");
    }

    @Test
    @DisplayName("Check account balance")
    void testGetBalance() throws MaxAccountsReachedException {
        AccountRepository accountRepository = new InMemoryAccountRepository();
        TransactionRepository transactionRepository = new InMemoryTransactionRepository();
        UserRepository userRepository = new InMemoryUserRepository(accountRepository);

        BankSystem accountSystem = new BankSystem("kate", accountRepository, transactionRepository, userRepository);
        User user = accountSystem.createUser("kate", "password", 1);
        Account account = accountSystem.createAccount(user);

        accountSystem.deposit(account, new BigDecimal("150"));
        BigDecimal balance = accountSystem.getBalance(account);

        assertEquals(new BigDecimal("150"), balance, "Balance should be 150");
    }

    @Test
    @DisplayName("Check transaction history")
    void testGetHistoryOfTransaction() throws MaxAccountsReachedException, NotEnoughFundsException {
        AccountRepository accountRepository = new InMemoryAccountRepository();
        TransactionRepository transactionRepository = new InMemoryTransactionRepository();
        UserRepository userRepository = new InMemoryUserRepository(accountRepository);

        BankSystem accountSystem = new BankSystem("kate", accountRepository, transactionRepository, userRepository);
        User user = accountSystem.createUser("kate", "password", 1);
        Account account = accountSystem.createAccount(user);

        accountSystem.deposit(account, new BigDecimal("10000"));
        accountSystem.withdrawal(account, new BigDecimal("50"));

        List<Transaction> transactions = accountSystem.getHistoryOfTransaction();

        assertEquals(2, transactions.size(), "There should be 2 transactions in history");
    }

    @Test
    @DisplayName("Check admin name")
    void testGetAdmin() {
        AccountRepository accountRepository = new InMemoryAccountRepository();
        TransactionRepository transactionRepository = new InMemoryTransactionRepository();
        UserRepository userRepository = new InMemoryUserRepository(accountRepository);

        BankSystem accountSystem = new BankSystem("kate", accountRepository, transactionRepository, userRepository);

        assertEquals("kate", accountSystem.getAdmin(), "Admin name should be 'kate'");
    }
}