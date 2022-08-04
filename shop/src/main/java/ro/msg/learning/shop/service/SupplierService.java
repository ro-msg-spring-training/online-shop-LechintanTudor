package ro.msg.learning.shop.service;

import org.springframework.stereotype.Service;
import ro.msg.learning.shop.exception.NullEntityException;
import ro.msg.learning.shop.model.Supplier;
import ro.msg.learning.shop.repository.SupplierRepository;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
public class SupplierService {
    private final SupplierRepository supplierRepository;

    public SupplierService(SupplierRepository supplierRepository) {
        this.supplierRepository = supplierRepository;
    }

    @Transactional
    public Supplier saveSupplier(Supplier supplier) {
        if (supplier == null) {
            throw new NullEntityException(Supplier.class);
        }

        return supplierRepository.save(supplier);
    }

    public Optional<Supplier> findSupplierById(Long supplierId) {
        return supplierRepository.findById(supplierId);
    }

    public List<Supplier> findAllSuppliers() {
        return supplierRepository.findAll();
    }
}
