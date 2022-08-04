package ro.msg.learning.shop.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class StockDto {
    private Long productId;
    private Long locationId;
    private Long quantity;
}
