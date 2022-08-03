package ro.msg.learning.shop.service;

import org.springframework.stereotype.Service;
import ro.msg.learning.shop.exception.EntityNotFoundException;
import ro.msg.learning.shop.model.Location;
import ro.msg.learning.shop.model.Product;
import ro.msg.learning.shop.model.Stock;
import ro.msg.learning.shop.repository.LocationRepository;
import ro.msg.learning.shop.repository.ProductRepository;
import ro.msg.learning.shop.repository.StockRepository;
import ro.msg.learning.shop.service.exception.NullEntityException;

import javax.transaction.Transactional;
import java.util.List;

@Service
public class StockService {
    private final ProductRepository productRepository;
    private final LocationRepository locationRepository;
    private final StockRepository stockRepository;

    public StockService(
        ProductRepository productRepository,
        LocationRepository locationRepository,
        StockRepository stockRepository
    ) {
        this.productRepository = productRepository;
        this.locationRepository = locationRepository;
        this.stockRepository = stockRepository;
    }

    @Transactional
    public Stock saveStock(Stock stock) {
        if (stock == null) {
            throw new NullEntityException(Stock.class);
        }

        var productId = stock.getProduct().getId();
        var product = productRepository
            .findById(productId)
            .orElseThrow(() -> new EntityNotFoundException(Product.class, productId));

        var locationId = stock.getLocation().getId();
        var location = locationRepository
            .findById(locationId)
            .orElseThrow(() -> new EntityNotFoundException(Location.class, locationId));

        stock.setProduct(product);
        stock.setLocation(location);

        return stockRepository.save(stock);
    }

    public List<Stock> findAllStocks() {
        return stockRepository.findAll();
    }
}
