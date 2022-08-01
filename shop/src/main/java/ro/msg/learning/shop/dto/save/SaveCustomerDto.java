package ro.msg.learning.shop.dto.save;

import lombok.*;
import ro.msg.learning.shop.model.Customer;

@Data
@NoArgsConstructor
public class SaveCustomerDto {
    private String firstName;
    private String lastName;
    private String username;
    private String password;
    private String email;

    public Customer toCustomer() {
        return new Customer(null, firstName, lastName, username, password, email);
    }
}
