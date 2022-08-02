package ro.msg.learning.shop.controller.mapper;

import org.springframework.stereotype.Component;
import ro.msg.learning.shop.dto.ProductOrderDetailDto;
import ro.msg.learning.shop.model.Product;
import ro.msg.learning.shop.model.ProductOrderDetail;

@Component
public class ProductOrderDetailMapper {
    public ProductOrderDetail toProductOrderDetail(ProductOrderDetailDto productOrderDetailDto) {
        var product = Product.builder()
            .id(productOrderDetailDto.getProductId())
            .build();

        return ProductOrderDetail.builder()
            .product(product)
            .quantity(productOrderDetailDto.getQuantity())
            .build();
    }

    public ProductOrderDetailDto toProductOrderDetailDto(ProductOrderDetail productOrderDetail) {
        return ProductOrderDetailDto.builder()
            .productId(productOrderDetail.getProduct().getId())
            .quantity(productOrderDetail.getQuantity())
            .build();
    }
}
