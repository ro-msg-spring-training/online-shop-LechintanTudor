package ro.msg.learning.shop.dto.save;

import lombok.*;
import ro.msg.learning.shop.model.Address;
import ro.msg.learning.shop.model.Location;

import java.io.Serializable;

@Data @NoArgsConstructor
public class SaveLocationDto implements Serializable {
    private String name;
    private Address address;

    public Location toLocation() {
        return new Location(null, name, address);
    }
}
