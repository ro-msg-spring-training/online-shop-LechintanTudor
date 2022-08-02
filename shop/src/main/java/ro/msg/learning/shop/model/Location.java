package ro.msg.learning.shop.model;

import lombok.*;
import org.hibernate.Hibernate;

import javax.persistence.*;
import java.util.Objects;
import java.util.Set;

@Table(name = "location")
@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Location {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @Embedded
    private Address address;

    @OneToMany(mappedBy = "shippedFrom", fetch = FetchType.LAZY)
    private Set<ProductOrder> productOrders;

    @OneToMany(mappedBy = "location", fetch = FetchType.LAZY)
    private Set<Stock> stocks;

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }

        if (obj == null || Hibernate.getClass(this) != Hibernate.getClass(obj)) {
            return false;
        }

        var location = (Location) obj;
        return id != null && Objects.equals(id, location.id);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}
