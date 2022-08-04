package ro.msg.learning.shop.service;

import org.springframework.stereotype.Service;
import ro.msg.learning.shop.exception.EntityNotFoundException;
import ro.msg.learning.shop.exception.NullEntityException;
import ro.msg.learning.shop.model.Location;
import ro.msg.learning.shop.model.Product;
import ro.msg.learning.shop.model.Stock;
import ro.msg.learning.shop.repository.StockRepository;

import javax.transaction.Transactional;
import java.util.List;

@Service
public class StockService {
    private final StockRepository stockRepository;
    private final ProductService productService;
    private final LocationService locationService;

    public StockService(
        StockRepository stockRepository,
        ProductService productService,
        LocationService locationService
    ) {
        this.stockRepository = stockRepository;
        this.productService = productService;
        this.locationService = locationService;
    }

    @Transactional
    public Stock saveStock(Stock stock) {
        if (stock == null) {
            throw new NullEntityException(Stock.class);
        }

        var productId = stock.getProduct().getId();
        var product = productService
            .findProductById(productId)
            .orElseThrow(() -> new EntityNotFoundException(Product.class, productId));

        var locationId = stock.getLocation().getId();
        var location = locationService
            .findLocationById(locationId)
            .orElseThrow(() -> new EntityNotFoundException(Location.class, locationId));

        stock.setProduct(product);
        stock.setLocation(location);

        return stockRepository.save(stock);
    }

    public List<Stock> findAllStocks() {
        return stockRepository.findAll();
    }
}
