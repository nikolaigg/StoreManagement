package exceptions;

public class InvalidStockRequestException extends RuntimeException {
    public InvalidStockRequestException(String message) {
        super(message);
    }
}
