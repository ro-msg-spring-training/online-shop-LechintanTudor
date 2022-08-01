package ro.msg.learning.shop.model;

import lombok.Data;
import java.io.Serializable;

@Data
public class ProductOrderDetailId implements Serializable {
    private ProductOrder order;
    private Product product;
}
