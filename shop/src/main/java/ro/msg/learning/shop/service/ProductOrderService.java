package ro.msg.learning.shop.service;

import org.springframework.stereotype.Service;
import ro.msg.learning.shop.model.ProductOrder;
import ro.msg.learning.shop.model.ProductOrderDetail;
import ro.msg.learning.shop.model.info.ProductOrderInfo;
import ro.msg.learning.shop.repository.*;

import javax.transaction.Transactional;
import java.util.stream.Collectors;

@Service
public class ProductOrderService {
    private final LocationRepository locationRepository;
    private final CustomerRepository customerRepository;
    private final ProductRepository productRepository;
    private final ProductOrderDetailRepository orderDetailRepository;
    private final ProductOrderRepository orderRepository;

    public ProductOrderService(
        LocationRepository locationRepository,
        CustomerRepository customerRepository,
        ProductRepository productRepository,
        ProductOrderDetailRepository orderDetailRepository,
        ProductOrderRepository orderRepository
    ) {
        this.locationRepository = locationRepository;
        this.customerRepository = customerRepository;
        this.productRepository = productRepository;
        this.orderDetailRepository = orderDetailRepository;
        this.orderRepository = orderRepository;
    }

    @Transactional
    public ProductOrder saveProductOrder(ProductOrderInfo saveOrder) {
        var customer = customerRepository.getReferenceById(saveOrder.getCustomerId());
        var location = locationRepository.getReferenceById(1L);

        // Save order without details
        var order = new ProductOrder();
        order.setShippedFrom(location);
        order.setCustomer(customer);
        order.setCreatedAt(saveOrder.getCreatedAt());
        order.setAddress(saveOrder.getAddress());
        orderRepository.save(order);

        // Save order details
        var details = saveOrder.getDetails().stream()
            .map(detailDto -> {
                var product = productRepository.getReferenceById(detailDto.getProductId());

                var detail = new ProductOrderDetail();
                detail.setOrder(order);
                detail.setProduct(product);
                detail.setQuantity(detailDto.getQuantity());
                return detail;
            })
            .collect(Collectors.toSet());

        orderDetailRepository.saveAll(details);

        // Save order with details
        order.setDetails(details);
        return orderRepository.save(order);
    }
}
