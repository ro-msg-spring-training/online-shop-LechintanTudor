package ro.msg.learning.shop.dto.save;

import lombok.*;

@Data @NoArgsConstructor
public class SaveProductOrderDetailDto {
    private long productId;
    private long quantity;
}
