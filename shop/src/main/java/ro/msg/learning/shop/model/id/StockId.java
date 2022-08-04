package ro.msg.learning.shop.model.id;

import lombok.Data;

import java.io.Serializable;

@Data
public class StockId implements Serializable {
    Long product;
    Long location;
}
