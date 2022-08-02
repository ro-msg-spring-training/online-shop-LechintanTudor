package ro.msg.learning.shop.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

@AllArgsConstructor
@Getter
@ToString
public class AddressDto {
    private final String country;
    private final String city;
    private final String county;
    private final String street;
}
