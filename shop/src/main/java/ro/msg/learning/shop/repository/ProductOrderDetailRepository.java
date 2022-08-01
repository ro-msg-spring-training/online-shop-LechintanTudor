package ro.msg.learning.shop.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ro.msg.learning.shop.model.ProductOrderDetail;
import ro.msg.learning.shop.model.ProductOrderDetailId;

public interface ProductOrderDetailRepository extends JpaRepository<ProductOrderDetail, ProductOrderDetailId> {

}
