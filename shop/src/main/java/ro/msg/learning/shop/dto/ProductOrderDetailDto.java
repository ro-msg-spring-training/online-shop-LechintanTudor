package ro.msg.learning.shop.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ProductOrderDetailDto {
    private final Long productId;
    private final Long quantity;
}
