package ro.msg.learning.shop.dto;

import lombok.Getter;
import lombok.ToString;
import ro.msg.learning.shop.model.Product;

import java.math.BigDecimal;

@Getter
@ToString
public class ProductWithCategoryDto {
    private final Long id;
    private final String name;
    private final String description;
    private final BigDecimal price;
    private final double weight;
    private final ProductCategoryDto category;

    public ProductWithCategoryDto(Product product) {
        id = product.getId();
        name = product.getName();
        description = product.getDescription();
        price = product.getPrice();
        weight = product.getWeight();

        if (product.getCategory() != null) {
            category = new ProductCategoryDto(product.getCategory());
        } else {
            category = null;
        }
    }
}
