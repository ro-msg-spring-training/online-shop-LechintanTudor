package ro.msg.learning.shop.model.info;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import lombok.NoArgsConstructor;
import ro.msg.learning.shop.model.Address;

import java.time.LocalDateTime;
import java.util.List;

@Data
@NoArgsConstructor
public class ProductOrderInfo {
    private long customerId;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm", shape = JsonFormat.Shape.STRING)
    private LocalDateTime createdAt;

    private Address address;

    private List<ProductOrderDetailInfo> details;
}
