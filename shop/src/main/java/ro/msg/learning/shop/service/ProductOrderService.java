package ro.msg.learning.shop.service;

import org.springframework.stereotype.Service;
import ro.msg.learning.shop.exception.EntityNotFoundException;
import ro.msg.learning.shop.model.Customer;
import ro.msg.learning.shop.model.Product;
import ro.msg.learning.shop.model.ProductOrder;
import ro.msg.learning.shop.repository.*;

import javax.transaction.Transactional;

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
    public ProductOrder saveProductOrder(ProductOrder order) {
        var customerId = order.getCustomer().getId();
        var customer = customerRepository
            .findById(customerId)
            .orElseThrow(() -> new EntityNotFoundException(Customer.class, customerId));

        var shippedFrom = locationRepository.getReferenceById(1L);

        order.setCustomer(customer);
        order.setShippedFrom(shippedFrom);

        order.getDetails().forEach(detail -> {
            var productId = detail.getProduct().getId();
            var product = productRepository
                .findById(productId)
                .orElseThrow(() -> new EntityNotFoundException(Product.class, productId));

            detail.setOrder(order);
            detail.setProduct(product);
        });

        var savedOrder = orderRepository.save(order);
        orderDetailRepository.saveAll(order.getDetails());
        return savedOrder;
    }
}
