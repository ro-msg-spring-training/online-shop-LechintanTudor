package ro.msg.learning.shop.dto.save;

import lombok.AllArgsConstructor;
import lombok.Getter;
import ro.msg.learning.shop.model.ProductCategory;

import java.util.HashSet;

@AllArgsConstructor @Getter
public class SaveProductCategoryDto {
    private final String name;
    private final String description;

    public ProductCategory toProductCategory() {
        return new ProductCategory(null, name, description, new HashSet<>());
    }
}
