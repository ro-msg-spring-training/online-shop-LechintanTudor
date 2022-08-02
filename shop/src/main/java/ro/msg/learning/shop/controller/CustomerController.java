package ro.msg.learning.shop.controller;

import org.springframework.web.bind.annotation.*;
import ro.msg.learning.shop.controller.mapper.CustomerMapper;
import ro.msg.learning.shop.dto.CustomerDto;
import ro.msg.learning.shop.exception.EntityNotFoundException;
import ro.msg.learning.shop.model.Customer;
import ro.msg.learning.shop.service.CustomerService;

import java.util.List;

@RestController
@RequestMapping("/customers")
public class CustomerController {
    private final CustomerMapper customerMapper;
    private final CustomerService customerService;

    public CustomerController(CustomerMapper customerMapper, CustomerService customerService) {
        this.customerMapper = customerMapper;
        this.customerService = customerService;
    }

    @PostMapping
    public CustomerDto saveCustomer(@RequestBody CustomerDto customerDto) {
        var customer = customerMapper.toCustomer(customerDto);
        return customerMapper.toCustomerDto(customerService.saveCustomer(customer));
    }

    @GetMapping
    public List<CustomerDto> findAllCustomers() {
        return customerService.findAllCustomers().stream()
            .map(customerMapper::toCustomerDto)
            .toList();
    }

    @GetMapping("/{id}")
    public CustomerDto findCustomer(@PathVariable Long id) {
        return this.customerService.findCustomerById(id)
            .map(customerMapper::toCustomerDto)
            .orElseThrow(() -> new EntityNotFoundException(Customer.class, id));
    }

    @DeleteMapping("/{id}")
    public void deleteCustomer(@PathVariable Long id) {
        this.customerService.deleteCustomerById(id);
    }
}
