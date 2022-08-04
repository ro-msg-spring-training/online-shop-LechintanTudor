package ro.msg.learning.shop.controller.test;

import org.springframework.context.annotation.Profile;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ro.msg.learning.shop.model.*;
import ro.msg.learning.shop.repository.*;

import javax.transaction.Transactional;
import java.math.BigDecimal;

@RestController
@RequestMapping("/test/product-orders")
@Profile("test")
public class ProductOrderTestController {
    private final CustomerRepository customerRepository;
    private final LocationRepository locationRepository;
    private final ProductCategoryRepository productCategoryRepository;
    private final ProductRepository productRepository;
    private final SupplierRepository supplierRepository;
    private final StockRepository stockRepository;

    public ProductOrderTestController(
        CustomerRepository customerRepository,
        LocationRepository locationRepository,
        ProductCategoryRepository productCategoryRepository,
        ProductRepository productRepository,
        SupplierRepository supplierRepository,
        StockRepository stockRepository
    ) {
        this.customerRepository = customerRepository;
        this.locationRepository = locationRepository;
        this.productCategoryRepository = productCategoryRepository;
        this.productRepository = productRepository;
        this.supplierRepository = supplierRepository;
        this.stockRepository = stockRepository;
    }

    @PostMapping
    @Transactional
    public void populateData() {
        var customer = customerRepository.save(
            Customer.builder()
                .firstName("John")
                .lastName("Doe")
                .username("doej")
                .password("password")
                .email("doesj@gmail.com")
                .build()
        );

        var location = locationRepository.save(
            Location.builder()
                .name("Sediul .msg")
                .address(
                    Address.builder()
                        .country("Romania")
                        .city("Cluj-Napoca")
                        .county("Cluj")
                        .street("Croitorilor")
                        .build()
                )
                .build()
        );

        var productCategory = productCategoryRepository.save(
            ProductCategory.builder()
                .name("Laptops")
                .description("Portable computers")
                .build()
        );

        var supplier = supplierRepository.save(
            Supplier.builder()
                .name("eMAG")
                .build()
        );

        var product1 = productRepository.save(
            Product.builder()
                .name("Thinkpad X200")
                .description("A laptop blessed by Luke Smith")
                .category(productCategory)
                .supplier(supplier)
                .price(new BigDecimal(80))
                .weight(2.0)
                .imageUrl("undefined")
                .build()
        );

        var product2 = productRepository.save(
            Product.builder()
                .name("MacBook Pro")
                .description("Overpriced piece of junk")
                .category(productCategory)
                .supplier(supplier)
                .price(new BigDecimal(99999999))
                .weight(1.0)
                .imageUrl("undefined")
                .build()
        );

        stockRepository.save(
            Stock.builder()
                .location(location)
                .product(product1)
                .quantity(5L)
                .build()
        );

        stockRepository.save(
            Stock.builder()
                .location(location)
                .product(product2)
                .quantity(5L)
                .build()
        );
    }

    @DeleteMapping
    @Transactional
    public void depopulateData() {
        stockRepository.deleteAll();
        productRepository.deleteAll();
        supplierRepository.deleteAll();
        productCategoryRepository.deleteAll();
        locationRepository.deleteAll();
        customerRepository.deleteAll();
    }
}
