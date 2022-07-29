package ro.msg.learning.shop.dto.save;

import lombok.AllArgsConstructor;
import lombok.Getter;
import ro.msg.learning.shop.model.Customer;

@AllArgsConstructor @Getter
public class SaveCustomerDto {
    private final String firstName;
    private final String lastName;
    private final String username;
    private final String password;
    private final String email;

    public Customer toCustomer() {
        return new Customer(null, firstName, lastName, username, password, email);
    }
}
