package ro.msg.learning.shop.controller.mapper;

import org.springframework.stereotype.Component;
import ro.msg.learning.shop.dto.LocationDto;
import ro.msg.learning.shop.model.Location;

@Component
public class LocationMapper {
    public Location toLocation(LocationDto locationDto) {
        return Location.builder()
            .id(locationDto.getId())
            .name(locationDto.getName())
            .address(locationDto.getAddress())
            .build();
    }

    public LocationDto toLocationDto(Location location) {
        return LocationDto.builder()
            .id(location.getId())
            .name(location.getName())
            .address(location.getAddress())
            .build();
    }
}
