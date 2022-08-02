package ro.msg.learning.shop.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class SupplierDto {
    private final Long id;
    private final String name;
}
