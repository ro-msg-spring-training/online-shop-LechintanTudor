package ro.msg.learning.shop.dto;

import lombok.Getter;
import lombok.ToString;
import ro.msg.learning.shop.model.ProductCategory;

@Getter @ToString
public class ProductCategoryDto {
    private final Long id;
    private final String name;
    private final String description;

    public ProductCategoryDto(ProductCategory productCategory) {
        id = productCategory.getId();
        name = productCategory.getName();
        description = productCategory.getDescription();
    }
}
