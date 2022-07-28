package ro.msg.learning.shop.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import ro.msg.learning.shop.model.ProductCategory;

import java.util.HashSet;

@AllArgsConstructor @Getter
public class ProductCategoryDto {
    private Long id;
    private String name;
    private String description;

    public ProductCategoryDto(ProductCategory productCategory) {
        this.id = productCategory.getId();
        this.name = productCategory.getName();
        this.description = productCategory.getDescription();
    }

    public ProductCategory toProductCategory() {
        return new ProductCategory(id, name, description, new HashSet<>());
    }
}
