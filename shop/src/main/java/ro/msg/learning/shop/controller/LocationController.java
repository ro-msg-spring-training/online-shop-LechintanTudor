package ro.msg.learning.shop.controller;

import org.springframework.web.bind.annotation.*;
import ro.msg.learning.shop.dto.LocationDto;
import ro.msg.learning.shop.dto.save.SaveLocationDto;
import ro.msg.learning.shop.exception.EntityNotFoundException;
import ro.msg.learning.shop.model.Location;
import ro.msg.learning.shop.service.LocationService;

import java.util.List;

@RestController
@RequestMapping("/locations")
public class LocationController {
    private final LocationService locationService;

    public LocationController(LocationService locationService) {
        this.locationService = locationService;
    }

    @PostMapping
    public LocationDto saveLocation(@RequestBody SaveLocationDto location) {
        System.out.printf("%s\n", location.toString());
        return new LocationDto(locationService.saveLocation(location));
    }

    @GetMapping
    public List<LocationDto> findAllLocations() {
        return locationService.findAllLocations().stream().map(LocationDto::new).toList();
    }

    @GetMapping("/{id}")
    public LocationDto findLocationById(@PathVariable Long id) {
        return locationService.findLocationById(id)
            .map(LocationDto::new)
            .orElseThrow(() -> new EntityNotFoundException(Location.class, id));
    }
}
