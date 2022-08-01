package ro.msg.learning.shop.service;

import org.springframework.stereotype.Service;
import ro.msg.learning.shop.dto.save.SaveLocationDto;
import ro.msg.learning.shop.model.Location;
import ro.msg.learning.shop.repository.LocationRepository;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
public class LocationService {
    private final LocationRepository locations;

    public LocationService(LocationRepository locations) {
        this.locations = locations;
    }

    public Location saveLocation(SaveLocationDto location) {
        return locations.save(location.toLocation());
    }

    public Optional<Location> findLocationById(Long locationId) {
        return locations.findById(locationId);
    }

    public List<Location> findAllLocations() {
        return locations.findAll();
    }

    @Transactional
    public void deleteLocationById(Long locationId) {
        if (locations.existsById(locationId)) {
            locations.deleteById(locationId);
        }
    }
}
