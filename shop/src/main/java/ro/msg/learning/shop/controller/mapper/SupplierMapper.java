package ro.msg.learning.shop.controller.mapper;

import org.springframework.stereotype.Component;
import ro.msg.learning.shop.dto.SupplierDto;
import ro.msg.learning.shop.model.Supplier;

@Component
public class SupplierMapper {
    public Supplier toSupplier(SupplierDto supplierDto) {
        return Supplier.builder()
            .id(supplierDto.getId())
            .name(supplierDto.getName())
            .build();
    }

    public SupplierDto toSupplierDto(Supplier supplier) {
        return SupplierDto.builder()
            .id(supplier.getId())
            .name(supplier.getName())
            .build();
    }
}
