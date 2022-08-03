package ro.msg.learning.shop.service.order;

import lombok.AllArgsConstructor;
import lombok.Data;
import ro.msg.learning.shop.model.Stock;

@Data
@AllArgsConstructor
public class DeliveryStock {
    private Stock stock;
    private Long quantity;
}
