package ro.msg.learning.shop.model;

import lombok.*;

import javax.persistence.Embeddable;
import java.io.Serializable;

@Embeddable
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class Address implements Serializable {
    private String country;
    private String city;
    private String county;
    private String street;
}
