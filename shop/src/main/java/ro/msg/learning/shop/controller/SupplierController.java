package ro.msg.learning.shop.controller;

import org.springframework.web.bind.annotation.*;
import ro.msg.learning.shop.controller.mapper.SupplierMapper;
import ro.msg.learning.shop.dto.SupplierDto;
import ro.msg.learning.shop.exception.EntityNotFoundException;
import ro.msg.learning.shop.model.Supplier;
import ro.msg.learning.shop.service.SupplierService;

import java.util.List;

@RestController
@RequestMapping("/suppliers")
public class SupplierController {
    private final SupplierService supplierService;
    private final SupplierMapper supplierMapper;

    public SupplierController(SupplierService supplierService, SupplierMapper supplierMapper) {
        this.supplierService = supplierService;
        this.supplierMapper = supplierMapper;
    }

    @PostMapping
    public SupplierDto saveSupplier(@RequestBody SupplierDto supplierDto) {
        var supplier = supplierService.saveSupplier(supplierMapper.toSupplier(supplierDto));
        return supplierMapper.toSupplierDto(supplier);
    }

    @GetMapping
    public List<SupplierDto> findAllSuppliers() {
        return supplierService.findAllSuppliers().stream()
            .map(supplierMapper::toSupplierDto)
            .toList();
    }

    @GetMapping("/{id}")
    public SupplierDto findSupplierById(@PathVariable Long id) {
        return supplierService.findSupplierById(id)
            .map(supplierMapper::toSupplierDto)
            .orElseThrow(() -> new EntityNotFoundException(Supplier.class, id));
    }
}
