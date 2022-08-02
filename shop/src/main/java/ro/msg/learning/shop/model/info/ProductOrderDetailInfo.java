package ro.msg.learning.shop.model.info;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class ProductOrderDetailInfo {
    private long productId;
    private long quantity;
}
