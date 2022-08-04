package ro.msg.learning.shop.controller.mapper;

import org.springframework.stereotype.Component;
import ro.msg.learning.shop.dto.ProductDto;
import ro.msg.learning.shop.model.Product;
import ro.msg.learning.shop.model.ProductCategory;
import ro.msg.learning.shop.model.Supplier;

@Component
public class ProductMapper {
    public Product toProduct(ProductDto productDto) {
        var category = ProductCategory.builder()
            .id(productDto.getCategoryId())
            .build();

        var supplier = Supplier.builder()
            .id(productDto.getSupplierId())
            .build();

        return Product.builder()
            .id(productDto.getId())
            .name(productDto.getName())
            .description(productDto.getDescription())
            .price(productDto.getPrice())
            .weight(productDto.getWeight())
            .category(category)
            .supplier(supplier)
            .imageUrl(productDto.getImageUrl())
            .build();
    }

    public ProductDto toProductDto(Product product) {
        var categoryId = product.getCategory() != null ? product.getCategory().getId() : null;
        var supplierId = product.getSupplier() != null ? product.getSupplier().getId() : null;

        return ProductDto.builder()
            .id(product.getId())
            .name(product.getName())
            .description(product.getDescription())
            .price(product.getPrice())
            .weight(product.getWeight())
            .categoryId(categoryId)
            .supplierId(supplierId)
            .build();
    }
}
