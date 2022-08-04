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
        return locations.stream()
            .map(location -> findSuitableDeliveryStocks(order, location))
            .filter(Objects::nonNull)
            .findFirst()
            .orElseThrow(NoSuitableDeliveryStocksFoundException::new);
    }

    /**
     * Finds a list of suitable delivery stocks for shipping a product from the given location.
     *
     * @param order    order for which to find delivery stocks
     * @param location location from which the order will be delivered
     * @return list of suitable delivery stocks or null if the products requested in the order are out of stock
     */
    private List<DeliveryStock> findSuitableDeliveryStocks(ProductOrder order, Location location) {
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
                return null;
            }

            deliveryStocks.add(new DeliveryStock(selectedStock.get(), orderDetail.getQuantity()));
        }

        return deliveryStocks;
    }
}
