package ro.msg.learning.shop;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import ro.msg.learning.shop.dto.*;
import ro.msg.learning.shop.model.*;
import ro.msg.learning.shop.repository.CustomerRepository;
import ro.msg.learning.shop.repository.ProductRepository;

import java.time.LocalDateTime;
import java.util.stream.Collectors;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest(
    webEnvironment = SpringBootTest.WebEnvironment.MOCK,
    properties = { "productOrderStrategyType=MOST_ABUNDANT" }
)
@ActiveProfiles("test")
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
@AutoConfigureMockMvc
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.ANY)
class MostAbundantProductOrderStrategyTests {
    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private ProductRepository productRepository;

    @BeforeEach
    void setUpData() throws Exception {
        var populateRequest = MockMvcRequestBuilders.post("/test/product-orders")
            .contentType("application/json");

        mockMvc.perform(populateRequest).andExpect(status().isOk());
    }

    RequestBuilder prepareProductOrderRequestBuilder(Long quantity) {
        var customerId = customerRepository.findAll().stream()
            .findFirst()
            .map(Customer::getId)
            .orElseThrow();

        var productIds = productRepository.findAll().stream()
            .map(Product::getId)
            .toList();

        var productOrderDetailDtos = productIds.stream()
            .map(productId -> {
                return ProductOrderDetailDto.builder()
                    .productId(productId)
                    .quantity(quantity)
                    .build();
            })
            .collect(Collectors.toSet());

        var productOrderDto = ProductOrderDto.builder()
            .customerId(customerId)
            .createdAt(LocalDateTime.now())
            .address(
                Address.builder()
                    .country("Romania")
                    .city("Cluj-Napoca")
                    .county("Cluj")
                    .street("Croitorilor")
                    .build()
            )
            .details(productOrderDetailDtos)
            .build();

        String content;

        try {
            content = objectMapper.writeValueAsString(productOrderDto);
        } catch (Exception error) {
            throw new RuntimeException(error);
        }

        return MockMvcRequestBuilders.post("/orders")
            .contentType("application/json")
            .content(content);
    }

    @Test
    void productOrderSuccess() throws Exception {
        var request = prepareProductOrderRequestBuilder(5L);
        mockMvc.perform(request).andExpect(status().isOk());
    }

    @Test
    void productOrderError() throws Exception {
        var request = prepareProductOrderRequestBuilder(6L);
        mockMvc.perform(request).andExpect(status().is5xxServerError());
    }
}
