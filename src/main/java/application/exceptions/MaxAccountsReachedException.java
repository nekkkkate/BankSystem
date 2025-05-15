package application.exceptions;

public class MaxAccountsReachedException extends Exception {
    public MaxAccountsReachedException(String message) {
        super(message);
    }
}
