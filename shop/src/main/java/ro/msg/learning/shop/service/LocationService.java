package ro.msg.learning.shop.service;

import org.springframework.stereotype.Service;
import ro.msg.learning.shop.model.Location;
import ro.msg.learning.shop.repository.LocationRepository;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
public class LocationService {
    private final LocationRepository locationRepository;

    public LocationService(LocationRepository locationRepository) {
        this.locationRepository = locationRepository;
    }

    public Location saveLocation(Location location) {
        return locationRepository.save(location);
    }

    public Optional<Location> findLocationById(Long locationId) {
        return locationRepository.findById(locationId);
    }

    public List<Location> findAllLocations() {
        return locationRepository.findAll();
    }

    @Transactional
    public void deleteLocationById(Long locationId) {
        if (locationRepository.existsById(locationId)) {
            locationRepository.deleteById(locationId);
        }
    }
}
