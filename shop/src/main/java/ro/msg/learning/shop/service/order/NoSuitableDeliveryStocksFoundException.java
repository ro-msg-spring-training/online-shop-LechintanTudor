package ro.msg.learning.shop.service.order;

public class NoSuitableDeliveryStocksFoundException extends RuntimeException {
    public NoSuitableDeliveryStocksFoundException() {
        super("No suitable delivery stocks found");
    }
}
