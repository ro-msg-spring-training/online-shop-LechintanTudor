package ro.msg.learning.shop.dto.save;

import lombok.*;

import java.math.BigDecimal;

@Data @NoArgsConstructor
public class SaveProductDto {
    private String name;
    private String description;
    private BigDecimal price;
    private double weight;
    private long productCategory;
    private long supplier;
    private String imageUrl;
}
