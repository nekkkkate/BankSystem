package prestntation;

import application.exceptions.MaxAccountsReachedException;
import application.exceptions.NotEnoughFundsException;
import application.models.Account;
import application.models.User;
import infrastructure.InMemoryAccountRepository;
import infrastructure.InMemoryTransactionRepository;
import infrastructure.InMemoryUserRepository;

import java.math.BigDecimal;
import java.util.Scanner;
import java.util.UUID;

public class UsingBankSystem {

    private final Scanner scanner = new Scanner(System.in);
    private final InMemoryAccountRepository accountRepository = new InMemoryAccountRepository();
    private final InMemoryUserRepository userRepository = new InMemoryUserRepository(accountRepository);
    private final InMemoryTransactionRepository transactionRepository = new InMemoryTransactionRepository();


    /**
     * This function starts the banking system application and provides a menu for users to interact with the system.
     * It initializes the necessary components, such as the bank system, user repository, account repository, and transaction repository.
     * The function continuously prompts the user to choose an action from the menu, such as creating a new account, transferring funds,
     * withdrawing money, depositing money, checking balance, or exiting the application.
     *
     * @throws NoClassDefFoundError If any required classes, such as BankSystem, User, Account, InMemoryAccountRepository,
     *                              InMemoryTransactionRepository, or InMemoryUserRepository, are not found in the classpath.
     * @throws Exception            If any other unexpected error occurs during the execution of the function.
     */
    public void start() throws NotEnoughFundsException {
        System.out.println("Приветствуем вас в системе банка!");
        System.out.println("Пожалуйста введите имя:");
        String userName = scanner.nextLine();
        BankSystem kateBank = new BankSystem(userName, accountRepository, transactionRepository, userRepository);
        User admin = new User("kate", "nekkkkate", 1000);

        System.out.println("Добро пожаловать в прилоежние банка! пожалуйста выберите дальнейшие действия!");

        while (true) {

            System.out.println("1. Создать новый счет");
            System.out.println("2. Перевести деньги между счетами");
            System.out.println("3. Снять деньги со счета");
            System.out.println("4. Пополнить баланс счета");
            System.out.println("5. Посмотреть баланс");
            System.out.println("6. Выйти из приложения");

            int choice = scanner.nextInt();
            switch (choice) {
                case 1 -> {
                    System.out.println("Создание нового счёта");
                    Account account = null;
                    try {
                        account = kateBank.createAccount(admin);
                    } catch (MaxAccountsReachedException e) {
                        System.out.println("Достигнут максимальный лимит");
                        continue;
                    }
                    System.out.println("Счёт был успешно создан, ваш ID: " + kateBank.getAccountId(account));
                }
                case 2 -> {
                    System.out.println("Перевод денег между счетами");
                    System.out.println("Введите счет получателя:");
                    UUID recipientUid = UUID.fromString(scanner.next());
                    System.out.println("Введите счет отправителя:");
                    UUID senderId = UUID.fromString(scanner.next());
                    System.out.println("Введите сумму перевода:");
                    BigDecimal amount = scanner.nextBigDecimal();
                    kateBank.transfer(kateBank.getAccountByIdHashCode(senderId), kateBank.getAccountByIdHashCode(recipientUid), amount);
                }
                case 3 -> {
                    System.out.println("Снятие деняк со счета");
                    System.out.println("Введите счет отправителя:");
                    UUID yourId = UUID.fromString(scanner.next());
                    System.out.println("Введите сумму снятия:");
                    BigDecimal amount = scanner.nextBigDecimal();
                    kateBank.withdrawal(kateBank.getAccountByIdHashCode(yourId), amount);
                }
                case 4 -> {
                    System.out.println("Пополнение баланса счета");
                    System.out.println("Введите счет отправителя:");
                    UUID yourId = UUID.fromString(scanner.next());
                    System.out.println("Введите сумму пополнения:");
                    BigDecimal amount = scanner.nextBigDecimal();
                    kateBank.deposit(kateBank.getAccountByIdHashCode(yourId), amount);
                }
                case 5 -> {
                    System.out.println("Просмотр баланса");
                    System.out.println("Введите счет проверки:");
                    UUID yourId = UUID.fromString(scanner.next());
                    BigDecimal balance = kateBank.getBalance(kateBank.getAccountByIdHashCode(yourId));
                    System.out.println("Ваш баланс: " + balance);
                }

                case 6 -> {
                    System.out.println("Спасибо, что выбрали нас:) До новых встреч!!");
                    return;
                }
            }
        }
    }
}