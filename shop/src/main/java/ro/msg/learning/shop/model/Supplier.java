package ro.msg.learning.shop.model;

import lombok.*;
import org.hibernate.Hibernate;

import javax.persistence.*;
import java.util.Objects;
import java.util.Set;

@Table(name = "supplier")
@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Supplier {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @OneToMany(mappedBy = "supplier", fetch = FetchType.LAZY)
    private Set<Product> products;

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }

        if (obj == null || Hibernate.getClass(this) != Hibernate.getClass(obj)) {
            return false;
        }

        var supplier = (Supplier) obj;
        return id != null && Objects.equals(id, supplier.id);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}
