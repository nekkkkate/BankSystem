package prestntation.main;

import application.exceptions.NotEnoughFundsException;
import prestntation.UsingBankSystem;

public class Main {

    public static void main(String[] args) throws NotEnoughFundsException {
        System.out.println("Добро пожаловать в приложение банка!");
        UsingBankSystem system = new UsingBankSystem();
        system.start();
    }
}
