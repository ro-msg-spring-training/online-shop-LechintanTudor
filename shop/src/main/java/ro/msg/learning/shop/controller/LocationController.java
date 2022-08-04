package ro.msg.learning.shop.controller;

import org.springframework.web.bind.annotation.*;
import ro.msg.learning.shop.controller.mapper.LocationMapper;
import ro.msg.learning.shop.dto.LocationDto;
import ro.msg.learning.shop.exception.EntityNotFoundException;
import ro.msg.learning.shop.model.Location;
import ro.msg.learning.shop.service.LocationService;

import java.util.List;

@RestController
@RequestMapping("/locations")
public class LocationController {
    private final LocationMapper locationMapper;
    private final LocationService locationService;

    public LocationController(LocationMapper locationMapper, LocationService locationService) {
        this.locationMapper = locationMapper;
        this.locationService = locationService;
    }

    @PostMapping
    public LocationDto saveLocation(@RequestBody LocationDto locationDto) {
        var location = locationMapper.toLocation(locationDto);
        return locationMapper.toLocationDto(locationService.saveLocation(location));
    }

    @GetMapping
    public List<LocationDto> findAllLocations() {
        return locationService.findAllLocations().stream()
            .map(locationMapper::toLocationDto)
            .toList();
    }

    @GetMapping("/{id}")
    public LocationDto findLocationById(@PathVariable Long id) {
        return locationService.findLocationById(id)
            .map(locationMapper::toLocationDto)
            .orElseThrow(() -> new EntityNotFoundException(Location.class, id));
    }
}
