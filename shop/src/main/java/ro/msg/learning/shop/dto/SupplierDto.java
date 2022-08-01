package ro.msg.learning.shop.dto;

import lombok.Getter;
import lombok.ToString;
import ro.msg.learning.shop.model.Supplier;

@Getter @ToString
public class SupplierDto {
    private final Long id;
    private final String name;

    public SupplierDto(Supplier supplier) {
        id = supplier.getId();
        name = supplier.getName();
    }
}
