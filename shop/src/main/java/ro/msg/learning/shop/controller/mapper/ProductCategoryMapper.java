package ro.msg.learning.shop.controller.mapper;

import org.springframework.stereotype.Component;
import ro.msg.learning.shop.dto.ProductCategoryDto;
import ro.msg.learning.shop.model.ProductCategory;

@Component
public class ProductCategoryMapper {
    public ProductCategory toProductCategory(ProductCategoryDto productCategoryDto) {
        return ProductCategory.builder()
            .id(productCategoryDto.getId())
            .name(productCategoryDto.getName())
            .description(productCategoryDto.getDescription())
            .build();
    }

    public ProductCategoryDto toProductCategoryDto(ProductCategory productCategory) {
        return ProductCategoryDto.builder()
            .id(productCategory.getId())
            .name(productCategory.getName())
            .description(productCategory.getDescription())
            .build();
    }
}
