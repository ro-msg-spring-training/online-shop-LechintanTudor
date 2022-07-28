package ro.msg.learning.shop.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.math.BigDecimal;

@AllArgsConstructor @Getter
public class SaveProductDto {
    private final String name;
    private final String description;
    private final BigDecimal price;
    private final double weight;
    private final long productCategory;
    private final long supplier;
    private final String imageUrl;
}
