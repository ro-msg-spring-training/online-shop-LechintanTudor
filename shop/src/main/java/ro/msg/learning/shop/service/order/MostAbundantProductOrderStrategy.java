package ro.msg.learning.shop.service.order;

import ro.msg.learning.shop.exception.NoSuitableDeliveryStocksFoundException;
import ro.msg.learning.shop.model.Location;
import ro.msg.learning.shop.model.ProductOrder;

import java.util.ArrayList;
import java.util.List;

public class MostAbundantProductOrderStrategy implements ProductOrderStrategy {
    @Override
    public List<DeliveryStock> findDeliveryStocks(ProductOrder order, List<Location> locations) {
        var deliveryStocks = new ArrayList<DeliveryStock>();

        for (var orderDetail : order.getDetails()) {
            var selectedStock = orderDetail.getProduct().getStocks().stream()
                .filter(stock -> orderDetail.getQuantity() <= stock.getQuantity())
                .findFirst()
                .orElseThrow(NoSuitableDeliveryStocksFoundException::new);

            deliveryStocks.add(new DeliveryStock(selectedStock, orderDetail.getQuantity()));
        }

        return deliveryStocks;
    }
}
