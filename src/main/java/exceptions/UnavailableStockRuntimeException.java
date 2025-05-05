package exceptions;

public class UnavailableStockRuntimeException extends RuntimeException {
    public UnavailableStockRuntimeException(String message) {
        super(message);
    }
}
