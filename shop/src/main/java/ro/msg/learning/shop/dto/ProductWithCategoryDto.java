package ro.msg.learning.shop.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import ro.msg.learning.shop.model.Product;

import java.math.BigDecimal;

@AllArgsConstructor @Getter
public class ProductWithCategoryDto {
    private final Long id;
    private final String name;
    private final String description;
    private final BigDecimal price;
    private final double weight;
    private final ProductCategoryDto category;

    public ProductWithCategoryDto(Product product) {
        this.id = product.getId();
        this.name = product.getName();
        this.description = product.getDescription();
        this.price = product.getPrice();
        this.weight = product.getWeight();

        if (product.getCategory() != null) {
            this.category = new ProductCategoryDto(product.getCategory());
        } else {
            this.category = null;
        }
    }
}
