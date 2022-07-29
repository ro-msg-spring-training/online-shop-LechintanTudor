package ro.msg.learning.shop.controller;

import org.springframework.web.bind.annotation.*;
import ro.msg.learning.shop.dto.CustomerDto;
import ro.msg.learning.shop.dto.save.SaveCustomerDto;
import ro.msg.learning.shop.exception.EntityNotFoundException;
import ro.msg.learning.shop.model.Customer;
import ro.msg.learning.shop.service.CustomerService;

import java.util.List;

@RestController
public class CustomerController {
    private final CustomerService customerService;

    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }

    @PostMapping("/customers")
    public CustomerDto saveCustomer(@RequestBody SaveCustomerDto customer) {
        return new CustomerDto(this.customerService.saveCustomer(customer));
    }

    @GetMapping("/customers")
    public List<CustomerDto> findAllCustomers() {
        return this.customerService.findAllCustomers().stream().map(CustomerDto::new).toList();
    }

    @GetMapping("/customers/{id}")
    public CustomerDto findCustomer(@PathVariable Long id) {
        return this.customerService.findCustomerById(id)
            .map(CustomerDto::new)
            .orElseThrow(() -> new EntityNotFoundException(Customer.class, id));
    }

    @DeleteMapping("/customers/{id}")
    public void deleteCustomer(@PathVariable Long id) {
       this.customerService.deleteCustomerById(id);
    }
}
