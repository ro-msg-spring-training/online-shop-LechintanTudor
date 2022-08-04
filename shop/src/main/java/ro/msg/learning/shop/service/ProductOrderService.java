package ro.msg.learning.shop.service;

import org.springframework.stereotype.Service;
import ro.msg.learning.shop.exception.EntityNotFoundException;
import ro.msg.learning.shop.exception.NullEntityException;
import ro.msg.learning.shop.model.Customer;
import ro.msg.learning.shop.model.Product;
import ro.msg.learning.shop.model.ProductOrder;
import ro.msg.learning.shop.repository.ProductOrderDetailRepository;
import ro.msg.learning.shop.repository.ProductOrderRepository;
import ro.msg.learning.shop.service.order.ProductOrderStrategy;

import javax.transaction.Transactional;

@Service
public class ProductOrderService {
    private final ProductOrderDetailRepository orderDetailRepository;
    private final ProductOrderRepository orderRepository;
    private final LocationService locationService;
    private final CustomerService customerService;
    private final ProductService productService;
    private final StockService stockService;
    private final ProductOrderStrategy orderStrategy;

    public ProductOrderService(
        ProductOrderDetailRepository orderDetailRepository,
        ProductOrderRepository orderRepository,
        LocationService locationService,
        CustomerService customerService,
        ProductService productService,
        StockService stockService,
        ProductOrderStrategy orderStrategy
    ) {
        this.orderDetailRepository = orderDetailRepository;
        this.orderRepository = orderRepository;
        this.locationService = locationService;
        this.customerService = customerService;
        this.productService = productService;
        this.stockService = stockService;
        this.orderStrategy = orderStrategy;
    }

    @Transactional
    public ProductOrder saveProductOrder(ProductOrder order) {
        if (order == null) {
            throw new NullEntityException(ProductOrder.class);
        }

        var customerId = order.getCustomer().getId();
        var customer = customerService
            .findCustomerById(customerId)
            .orElseThrow(() -> new EntityNotFoundException(Customer.class, customerId));

        order.setCustomer(customer);

        // Fill the order details
        order.getDetails().forEach(detail -> {
            var productId = detail.getProduct().getId();
            var product = productService
                .findProductById(productId)
                .orElseThrow(() -> new EntityNotFoundException(Product.class, productId));

            detail.setOrder(order);
            detail.setProduct(product);
        });

        // Set the first stock location as the shipped from location, so we don't have to change the database schema
        var deliveryStocks = orderStrategy.findDeliveryStocks(order, locationService.findAllLocations());
        order.setShippedFrom(deliveryStocks.get(0).getStock().getLocation());

        var savedOrder = orderRepository.save(order);
        orderDetailRepository.saveAll(order.getDetails());

        // Subtract the products from the affected stocks
        for (var deliveryStock : deliveryStocks) {
            var updatedStock = deliveryStock.getStock();
            updatedStock.setQuantity(updatedStock.getQuantity() - deliveryStock.getQuantity());
            stockService.saveStock(updatedStock);
        }

        return savedOrder;
    }
}
