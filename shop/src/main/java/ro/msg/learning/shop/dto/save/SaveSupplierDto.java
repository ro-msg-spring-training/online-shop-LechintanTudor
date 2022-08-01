package ro.msg.learning.shop.dto.save;

import lombok.*;
import ro.msg.learning.shop.model.Supplier;

import java.util.HashSet;

@Data @NoArgsConstructor
public class SaveSupplierDto {
    private String name;

    public Supplier toSupplier() {
        return new Supplier(null, name, new HashSet<>());
    }
}
