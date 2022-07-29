package ro.msg.learning.shop.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import ro.msg.learning.shop.model.Supplier;

@AllArgsConstructor @Getter
public class SupplierDto {
    private final Long id;
    private final String name;

    public SupplierDto(Supplier supplier) {
        id = supplier.getId();
        name = supplier.getName();
    }
}
