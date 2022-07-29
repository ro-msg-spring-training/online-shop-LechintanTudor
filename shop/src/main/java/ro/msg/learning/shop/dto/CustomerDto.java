package ro.msg.learning.shop.dto;

import lombok.Getter;
import ro.msg.learning.shop.model.Customer;

@Getter
public class CustomerDto {
    private final Long id;
    private final String firstName;
    private final String lastName;
    private final String username;
    private final String password;
    private final String email;

    public CustomerDto(Customer customer) {
        id = customer.getId();
        firstName = customer.getFirstName();
        lastName = customer.getLastName();
        username = customer.getUsername();
        password = customer.getPassword();
        email = customer.getEmail();
    }
}
