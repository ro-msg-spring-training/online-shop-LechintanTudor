package ro.msg.learning.shop.model.info;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@NoArgsConstructor
public class ProductInfo {
    private String name;
    private String description;
    private BigDecimal price;
    private double weight;
    private long categoryId;
    private long supplierId;
    private String imageUrl;
}
