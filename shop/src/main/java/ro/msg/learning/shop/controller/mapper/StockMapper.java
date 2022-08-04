package ro.msg.learning.shop.controller.mapper;

import org.springframework.stereotype.Component;
import ro.msg.learning.shop.dto.StockDto;
import ro.msg.learning.shop.model.Location;
import ro.msg.learning.shop.model.Product;
import ro.msg.learning.shop.model.Stock;

@Component
public class StockMapper {
    public Stock toStock(StockDto stockDto) {
        var product = Product.builder().id(stockDto.getProductId()).build();
        var location = Location.builder().id(stockDto.getLocationId()).build();

        return Stock.builder()
            .product(product)
            .location(location)
            .quantity(stockDto.getQuantity())
            .build();
    }

    public StockDto toStockDto(Stock stock) {
        var productId = stock.getProduct() != null ? stock.getProduct().getId() : null;
        var locationId = stock.getLocation() != null ? stock.getLocation().getId() : null;

        return StockDto.builder()
            .productId(productId)
            .locationId(locationId)
            .quantity(stock.getQuantity())
            .build();
    }
}
