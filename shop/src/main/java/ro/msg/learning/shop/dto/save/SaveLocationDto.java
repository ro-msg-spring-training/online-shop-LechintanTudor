package ro.msg.learning.shop.dto.save;

import lombok.Data;
import lombok.NoArgsConstructor;
import ro.msg.learning.shop.model.Address;
import ro.msg.learning.shop.model.Location;

import java.io.Serializable;

@Data
@NoArgsConstructor
public class SaveLocationDto implements Serializable {
    private String name;
    private Address address;

    public Location toLocation() {
        var location = new Location();
        location.setName(name);
        location.setAddress(address);
        return location;
    }
}
