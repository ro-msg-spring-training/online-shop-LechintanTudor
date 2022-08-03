package ro.msg.learning.shop.service.order;

import org.springframework.stereotype.Component;
import ro.msg.learning.shop.model.Location;
import ro.msg.learning.shop.model.ProductOrder;

import java.util.List;

@Component
public interface ProductOrderStrategy {
    List<DeliveryStock> findDeliveryStocks(ProductOrder order, List<Location> locations);
}
