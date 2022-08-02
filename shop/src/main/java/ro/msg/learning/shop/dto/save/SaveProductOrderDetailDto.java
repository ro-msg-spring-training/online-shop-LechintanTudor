package ro.msg.learning.shop.dto.save;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class SaveProductOrderDetailDto {
    private long productId;
    private long quantity;
}
