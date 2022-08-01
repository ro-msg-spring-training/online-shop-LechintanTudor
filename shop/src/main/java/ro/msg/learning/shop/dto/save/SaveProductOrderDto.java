package ro.msg.learning.shop.dto.save;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;
import ro.msg.learning.shop.model.Address;

import java.time.LocalDateTime;
import java.util.List;

@Data @NoArgsConstructor
public class SaveProductOrderDto {
    private long customerId;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm", shape = JsonFormat.Shape.STRING)
    private LocalDateTime createdAt;

    private Address address;

    private List<SaveProductOrderDetailDto> details;
}
