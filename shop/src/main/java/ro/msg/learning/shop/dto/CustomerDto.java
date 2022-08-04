package ro.msg.learning.shop.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class CustomerDto {
    private final Long id;
    private final String firstName;
    private final String lastName;
    private final String username;
    private final String password;
    private final String email;
}
