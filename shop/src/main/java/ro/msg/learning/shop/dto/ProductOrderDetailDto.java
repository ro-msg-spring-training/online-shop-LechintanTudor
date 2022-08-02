package ro.msg.learning.shop.dto;

import lombok.Getter;
import lombok.ToString;
import ro.msg.learning.shop.model.ProductOrderDetail;

@Getter
@ToString
public class ProductOrderDetailDto {
    private final Long productId;
    private final long quantity;

    public ProductOrderDetailDto(ProductOrderDetail detail) {
        this.productId = detail.getProduct().getId();
        this.quantity = detail.getQuantity();
    }
}
