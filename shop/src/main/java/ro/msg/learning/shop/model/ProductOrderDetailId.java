package ro.msg.learning.shop.model;

import lombok.Data;
import java.io.Serializable;

@Data
public class ProductOrderDetailId implements Serializable {
    private Long order;
    private Long product;
}
