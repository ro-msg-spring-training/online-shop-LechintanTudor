package ro.msg.learning.shop.model;

import lombok.*;
import org.hibernate.Hibernate;
import ro.msg.learning.shop.model.id.StockId;

import javax.persistence.*;
import java.util.Objects;

@Table(name = "stock")
@Entity
@IdClass(StockId.class)
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Stock {
    @Id
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "product_id")
    private Product product;

    @Id
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "location_id")
    private Location location;

    private Long quantity;

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }

        if (obj == null || Hibernate.getClass(this) != Hibernate.getClass(obj)) {
            return false;
        }

        var stock = (Stock) obj;

        return product != null
            && location != null
            && Objects.equals(product, stock.product)
            && Objects.equals(location, stock.location);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}
