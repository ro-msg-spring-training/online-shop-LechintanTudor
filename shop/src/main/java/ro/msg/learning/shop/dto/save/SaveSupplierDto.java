package ro.msg.learning.shop.dto.save;

import lombok.*;
import ro.msg.learning.shop.model.Supplier;

@Data @NoArgsConstructor
public class SaveSupplierDto {
    private String name;

    public Supplier toSupplier() {
        var supplier = new Supplier();
        supplier.setName(name);
        return supplier;
    }
}
