package application.models.transactions;

import application.models.Account;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

public class Transaction {

    private final Account account;
    private final BigDecimal amount;
    public TransactionType type;
    private final UUID id = UUID.randomUUID();
    public Account recipient;
    public LocalDateTime timestamp = LocalDateTime.now();

    public Transaction(Account account, BigDecimal amount, TransactionType type) {
        this.account = account;
        this.amount = amount;
        this.type = type;
    }

    public Transaction(Account account, BigDecimal amount, TransactionType type, Account recipient) {
        this.account = account;
        this.amount = amount;
        this.type = type;
        this.recipient = recipient;
    }

    public Transaction(Account account, TransactionType type) {
        this.account = account;
        this.amount = account.getBalance();
        this.type = type;
    }

    public UUID getId() {
        return id;
    }

    public Account getAccount() {
        return account;
    }

    public LocalDateTime getDate() {
        return timestamp;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public TransactionType getType() {
        return type;
    }

    public Account getRecipient() {
        return recipient;
    }
}