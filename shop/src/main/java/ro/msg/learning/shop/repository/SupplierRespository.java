package ro.msg.learning.shop.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ro.msg.learning.shop.model.Supplier;

public interface SupplierRespository extends JpaRepository<Supplier, Long> {

}
