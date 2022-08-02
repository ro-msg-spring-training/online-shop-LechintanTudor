package ro.msg.learning.shop.controller.mapper;

import org.springframework.stereotype.Component;
import ro.msg.learning.shop.dto.AddressDto;
import ro.msg.learning.shop.model.Address;

@Component
public class AddressMapper {
    public Address toAddress(AddressDto addressDto) {
        return Address.builder()
            .country(addressDto.getCountry())
            .city(addressDto.getCity())
            .county(addressDto.getCounty())
            .street(addressDto.getStreet())
            .build();
    }

    public AddressDto toAddressDto(Address address) {
        return AddressDto.builder()
            .country(address.getCountry())
            .city(address.getCity())
            .county(address.getCounty())
            .street(address.getStreet())
            .build();
    }
}
