package ro.msg.learning.shop.service.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import ro.msg.learning.shop.service.order.MostAbundantProductOrderStrategy;
import ro.msg.learning.shop.service.order.ProductOrderStrategy;
import ro.msg.learning.shop.service.order.ProductOrderStrategyType;
import ro.msg.learning.shop.service.order.SingleLocationProductOrderStrategy;

@Configuration
public class ProductOrderServiceConfig {
    @Value("${productOrderStrategyType}")
    private ProductOrderStrategyType productOrderStrategyType;

    @Bean
    public ProductOrderStrategy productOrderStrategy() {
        return switch (productOrderStrategyType) {
            case SINGLE_LOCATION -> new SingleLocationProductOrderStrategy();
            case MOST_ABUNDANT -> new MostAbundantProductOrderStrategy();
        };
    }
}
