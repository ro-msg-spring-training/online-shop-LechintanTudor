package ro.msg.learning.shop.dto;

import lombok.Getter;
import lombok.ToString;
import ro.msg.learning.shop.model.Address;
import ro.msg.learning.shop.model.Location;

@Getter
@ToString
public class LocationDto {
    private final Long id;
    private final String name;
    private final Address address;

    public LocationDto(Location location) {
        id = location.getId();
        name = location.getName();
        address = location.getAddress();
    }
}
