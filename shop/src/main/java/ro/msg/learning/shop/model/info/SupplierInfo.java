package ro.msg.learning.shop.model.info;

import lombok.Data;
import lombok.NoArgsConstructor;
import ro.msg.learning.shop.model.Supplier;

@Data
@NoArgsConstructor
public class SupplierInfo {
    private String name;

    public Supplier toSupplier() {
        var supplier = new Supplier();
        supplier.setName(name);
        return supplier;
    }
}
