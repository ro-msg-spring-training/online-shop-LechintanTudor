package ro.msg.learning.shop.service.order;

import ro.msg.learning.shop.exception.NoSuitableDeliveryStocksFoundException;
import ro.msg.learning.shop.model.Location;
import ro.msg.learning.shop.model.ProductOrder;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class SingleLocationProductOrderStrategy implements ProductOrderStrategy {
    @Override
    public List<DeliveryStock> findDeliveryStocks(ProductOrder order, List<Location> locations) {
        for (var location : locations) {
            var deliveryStocks = new ArrayList<DeliveryStock>();

            for (var orderDetail : order.getDetails()) {
                // Find a suitable stock from the current location
                var selectedStock = location.getStocks().stream()
                    .filter(stock -> {
                        return
                            Objects.equals(orderDetail.getProduct().getId(), stock.getProduct().getId())
                                && orderDetail.getQuantity() <= stock.getQuantity();
                    })
                    .findFirst();

                // Discard the location if we can't find a suitable stock
                if (selectedStock.isEmpty()) {
                    deliveryStocks = null;
                    break;
                }

                deliveryStocks.add(new DeliveryStock(selectedStock.get(), orderDetail.getQuantity()));
            }

            if (deliveryStocks != null) {
                return deliveryStocks;
            }
        }

        throw new NoSuitableDeliveryStocksFoundException();
    }
}
