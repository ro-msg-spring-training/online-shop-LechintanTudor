package ro.msg.learning.shop.controller.mapper;

import org.springframework.stereotype.Component;
import ro.msg.learning.shop.dto.ProductWithCategoryDto;
import ro.msg.learning.shop.model.Product;

@Component
public class ProductWithCategoryMapper {
    private final ProductCategoryMapper productCategoryMapper;

    public ProductWithCategoryMapper(ProductCategoryMapper productCategoryMapper) {
        this.productCategoryMapper = productCategoryMapper;
    }

    public Product toProduct(ProductWithCategoryDto productWithCategoryDto) {
        var category = productCategoryMapper.toProductCategory(productWithCategoryDto.getCategory());

        return Product.builder()
            .id(productWithCategoryDto.getId())
            .name(productWithCategoryDto.getName())
            .description(productWithCategoryDto.getDescription())
            .price(productWithCategoryDto.getPrice())
            .weight(productWithCategoryDto.getWeight())
            .category(category)
            .build();
    }

    public ProductWithCategoryDto toProductWithCategoryDto(Product product) {
        var category = productCategoryMapper.toProductCategoryDto(product.getCategory());

        return ProductWithCategoryDto.builder()
            .id(product.getId())
            .name(product.getName())
            .description(product.getDescription())
            .price(product.getPrice())
            .weight(product.getWeight())
            .category(category)
            .build();
    }
}
