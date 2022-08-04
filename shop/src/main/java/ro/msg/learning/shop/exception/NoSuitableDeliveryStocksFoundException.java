package ro.msg.learning.shop.exception;

public class NoSuitableDeliveryStocksFoundException extends RuntimeException {
    public NoSuitableDeliveryStocksFoundException() {
        super("No suitable delivery stocks found");
    }
}
