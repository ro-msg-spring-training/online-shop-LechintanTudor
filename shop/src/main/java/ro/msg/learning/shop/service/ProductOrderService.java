package ro.msg.learning.shop.service;

import org.springframework.stereotype.Service;
import ro.msg.learning.shop.dto.save.SaveProductOrderDto;
import ro.msg.learning.shop.model.ProductOrder;
import ro.msg.learning.shop.model.ProductOrderDetail;
import ro.msg.learning.shop.repository.*;

import javax.transaction.Transactional;
import java.util.stream.Collectors;

@Service
public class ProductOrderService {
    private final LocationRepository locations;
    private final CustomerRepository customers;
    private final ProductRepository products;
    private final ProductOrderDetailRepository orderDetails;
    private final ProductOrderRepository orders;

    public ProductOrderService(
        LocationRepository locations,
        CustomerRepository customers,
        ProductRepository products,
        ProductOrderDetailRepository orderDetails,
        ProductOrderRepository orders
    ) {
        this.locations = locations;
        this.customers = customers;
        this.products = products;
        this.orderDetails = orderDetails;
        this.orders = orders;
    }

    @Transactional
    public ProductOrder saveProductOrder(SaveProductOrderDto orderDto) {
        var customer = customers.getReferenceById(orderDto.getCustomerId());
        var location = locations.getReferenceById(1L);

        // Save order without details
        var order = new ProductOrder();
        order.setShippedFrom(location);
        order.setCustomer(customer);
        order.setCreatedAt(orderDto.getCreatedAt());
        order.setAddress(orderDto.getAddress());
        orders.save(order);

        // Save order details
        var details = orderDto.getDetails().stream()
            .map(detailDto -> {
                var product = products.getReferenceById(detailDto.getProductId());

                var detail = new ProductOrderDetail();
                detail.setOrder(order);
                detail.setProduct(product);
                detail.setQuantity(detailDto.getQuantity());
                return detail;
            })
            .collect(Collectors.toSet());

        orderDetails.saveAll(details);

        // Save order with details
        order.setDetails(details);
        return orders.save(order);
    }
}
