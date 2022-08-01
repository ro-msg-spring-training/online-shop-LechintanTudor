package ro.msg.learning.shop.dto.save;

import lombok.*;
import ro.msg.learning.shop.model.ProductCategory;

import java.util.HashSet;

@Data @NoArgsConstructor
public class SaveProductCategoryDto {
    private String name;
    private String description;

    public ProductCategory toProductCategory() {
        return new ProductCategory(null, name, description, new HashSet<>());
    }
}
