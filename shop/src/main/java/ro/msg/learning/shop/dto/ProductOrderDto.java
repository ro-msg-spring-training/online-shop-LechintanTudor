package ro.msg.learning.shop.dto;

import lombok.Getter;
import lombok.ToString;
import ro.msg.learning.shop.model.Address;
import ro.msg.learning.shop.model.ProductOrder;

import java.time.LocalDateTime;
import java.util.Set;
import java.util.stream.Collectors;

@Getter @ToString
public class ProductOrderDto {
    private final Long id;
    private final Long customerId;
    private final LocalDateTime createdAt;
    private final Address address;
    private final Set<ProductOrderDetailDto> details;

    public ProductOrderDto(ProductOrder order) {
        this.id = order.getId();
        this.customerId = order.getCustomer().getId();
        this.createdAt = order.getCreatedAt();
        this.address = order.getAddress();
        this.details = order.getDetails().stream().map(ProductOrderDetailDto::new).collect(Collectors.toSet());
    }
}
