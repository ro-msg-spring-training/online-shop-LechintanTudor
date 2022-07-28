package ro.msg.learning.shop.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import ro.msg.learning.shop.model.Supplier;

import java.util.HashSet;

@AllArgsConstructor @Getter
public class SupplierDto {
    private final Long id;
    private final String name;

    public SupplierDto(Supplier supplier) {
        this.id = supplier.getId();
        this.name = supplier.getName();
    }

    public Supplier toSupplier() {
        return new Supplier(id, name, new HashSet<>());
    }
}
