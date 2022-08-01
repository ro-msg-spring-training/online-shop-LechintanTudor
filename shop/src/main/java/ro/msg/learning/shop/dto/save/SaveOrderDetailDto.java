package ro.msg.learning.shop.dto.save;

import lombok.*;

@Data @NoArgsConstructor
public class SaveOrderDetailDto {
    private long product;
    private int quantity;
}
