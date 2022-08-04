package ro.msg.learning.shop;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import ro.msg.learning.shop.dto.CustomerDto;
import ro.msg.learning.shop.repository.CustomerRepository;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@ActiveProfiles("test")
@AutoConfigureMockMvc
class ShopApplicationTests {
    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private CustomerRepository customerRepository;

    @Test
    void test1() throws Exception {
        var customerDto = CustomerDto.builder()
            .firstName("John")
            .lastName("Doe")
            .username("doej")
            .password("password")
            .email("doej@gmail.com")
            .build();

        var request = MockMvcRequestBuilders.post("/customers")
            .contentType("application/json")
            .content(objectMapper.writeValueAsString(customerDto));

        mockMvc.perform(request).andExpect(status().isOk());
    }
}
