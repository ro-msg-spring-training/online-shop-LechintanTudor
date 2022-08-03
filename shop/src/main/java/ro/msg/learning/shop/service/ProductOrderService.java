package ro.msg.learning.shop.service;

import org.springframework.stereotype.Service;
import ro.msg.learning.shop.exception.EntityNotFoundException;
import ro.msg.learning.shop.model.Customer;
import ro.msg.learning.shop.model.Product;
import ro.msg.learning.shop.model.ProductOrder;
import ro.msg.learning.shop.repository.*;
import ro.msg.learning.shop.service.order.ProductOrderStrategy;

import javax.transaction.Transactional;

@Service
public class ProductOrderService {
    private final LocationRepository locationRepository;
    private final CustomerRepository customerRepository;
    private final ProductRepository productRepository;
    private final ProductOrderDetailRepository orderDetailRepository;
    private final ProductOrderRepository orderRepository;
    private final StockRepository stockRepository;
    private final ProductOrderStrategy orderStrategy;

    public ProductOrderService(
        LocationRepository locationRepository,
        CustomerRepository customerRepository,
        ProductRepository productRepository,
        ProductOrderDetailRepository orderDetailRepository,
        ProductOrderRepository orderRepository,
        StockRepository stockRepository,
        ProductOrderStrategy orderStrategy
    ) {
        this.locationRepository = locationRepository;
        this.customerRepository = customerRepository;
        this.productRepository = productRepository;
        this.orderDetailRepository = orderDetailRepository;
        this.orderRepository = orderRepository;
        this.stockRepository = stockRepository;
        this.orderStrategy = orderStrategy;
    }

    @Transactional
    public ProductOrder saveProductOrder(ProductOrder order) {
        var customerId = order.getCustomer().getId();
        var customer = customerRepository
            .findById(customerId)
            .orElseThrow(() -> new EntityNotFoundException(Customer.class, customerId));

        order.setCustomer(customer);

        order.getDetails().forEach(detail -> {
            var productId = detail.getProduct().getId();
            var product = productRepository
                .findById(productId)
                .orElseThrow(() -> new EntityNotFoundException(Product.class, productId));

            detail.setOrder(order);
            detail.setProduct(product);
        });

        // Set the first stock location as the shipped from location so we don't have to change the database
        var deliveryStocks = orderStrategy.findDeliveryStocks(order, locationRepository.findAll());
        order.setShippedFrom(deliveryStocks.get(0).getStock().getLocation());

        var savedOrder = orderRepository.save(order);
        orderDetailRepository.saveAll(order.getDetails());

        // Substract the products from the affected stocks
        for (var deliveryStock : deliveryStocks) {
            var updatedStock = deliveryStock.getStock();
            updatedStock.setQuantity(updatedStock.getQuantity() - deliveryStock.getQuantity());
            stockRepository.save(updatedStock);
        }

        return savedOrder;
    }
}
