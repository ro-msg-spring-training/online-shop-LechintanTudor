package ro.msg.learning.shop.dto.save;

import lombok.Data;
import lombok.NoArgsConstructor;
import ro.msg.learning.shop.model.ProductCategory;

@Data
@NoArgsConstructor
public class SaveProductCategoryDto {
    private String name;
    private String description;

    public ProductCategory toProductCategory() {
        var category = new ProductCategory();
        category.setName(name);
        category.setDescription(description);
        return category;
    }
}
