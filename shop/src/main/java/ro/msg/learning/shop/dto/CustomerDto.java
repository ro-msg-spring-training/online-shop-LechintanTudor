package ro.msg.learning.shop.dto;

import lombok.Getter;
import lombok.ToString;
import ro.msg.learning.shop.model.Customer;

@Getter
@ToString
public class CustomerDto {
    private final Long id;
    private final String firstName;
    private final String lastName;
    private final String email;

    public CustomerDto(Customer customer) {
        id = customer.getId();
        firstName = customer.getFirstName();
        lastName = customer.getLastName();
        email = customer.getEmail();
    }
}
