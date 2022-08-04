package ro.msg.learning.shop.dto;

import lombok.Builder;
import lombok.Data;
import ro.msg.learning.shop.model.Address;

@Data
@Builder
public class LocationDto {
    private final Long id;
    private final String name;
    private final Address address;
}
