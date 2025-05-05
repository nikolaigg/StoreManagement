package exceptions;

public class NoCashierOnPaydeskException extends RuntimeException {
    public NoCashierOnPaydeskException(String message) {
        super(message);
    }
}
