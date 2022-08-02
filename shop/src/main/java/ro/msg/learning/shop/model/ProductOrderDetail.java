package ro.msg.learning.shop.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.Hibernate;

import javax.persistence.*;
import java.util.Objects;

@Table(name = "product_order_detail")
@Entity
@IdClass(ProductOrderDetailId.class)
@NoArgsConstructor
@Getter
@Setter
public class ProductOrderDetail {
    @Id
    @ManyToOne
    @JoinColumn(name = "product_order_id")
    private ProductOrder order;

    @Id
    @ManyToOne
    @JoinColumn(name = "product_id")
    private Product product;

    private Long quantity;

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }

        if (obj == null || Hibernate.getClass(this) != Hibernate.getClass(obj)) {
            return false;
        }

        var details = (ProductOrderDetail) obj;

        return order != null
            && product != null
            && Objects.equals(order, details.order)
            && Objects.equals(product, details.product);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}
