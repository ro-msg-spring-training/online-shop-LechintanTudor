package ro.msg.learning.shop.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class AddressDto {
    private final String country;
    private final String city;
    private final String county;
    private final String street;
}
