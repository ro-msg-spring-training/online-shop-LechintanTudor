package ro.msg.learning.shop.dto.save;

import lombok.Data;
import lombok.NoArgsConstructor;
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
        var customer = new Customer();
        customer.setFirstName(firstName);
        customer.setLastName(lastName);
        customer.setUsername(username);
        customer.setPassword(password);
        customer.setEmail(email);
        return customer;
    }
}
