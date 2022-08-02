package ro.msg.learning.shop.dto.save;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@NoArgsConstructor
public class SaveProductDto {
    private String name;
    private String description;
    private BigDecimal price;
    private double weight;
    private long categoryId;
    private long supplierId;
    private String imageUrl;
}
