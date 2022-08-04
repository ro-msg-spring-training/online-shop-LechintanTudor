package ro.msg.learning.shop.dto;

import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;

@Data
@Builder
public class ProductWithCategoryDto {
    private final Long id;
    private final String name;
    private final String description;
    private final BigDecimal price;
    private final Double weight;
    private final ProductCategoryDto category;
}
