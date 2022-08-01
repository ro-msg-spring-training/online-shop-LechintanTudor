package ro.msg.learning.shop.dto.save;

import lombok.*;
import ro.msg.learning.shop.model.Address;

import java.time.LocalDateTime;

@Data @NoArgsConstructor
public class SaveOrderDto {
    private long shippedFrom;
    private long customer;
    private LocalDateTime createdAt;
    private Address address;
}
