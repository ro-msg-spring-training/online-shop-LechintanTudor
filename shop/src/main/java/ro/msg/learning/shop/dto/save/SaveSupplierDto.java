package ro.msg.learning.shop.dto.save;

import lombok.AllArgsConstructor;
import lombok.Getter;
import ro.msg.learning.shop.model.Supplier;

import java.util.HashSet;

@AllArgsConstructor @Getter
public class SaveSupplierDto {
    private final String name;

    public Supplier toSupplier() {
        return new Supplier(null, name, new HashSet<>());
    }
}
