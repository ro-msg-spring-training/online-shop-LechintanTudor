package ro.msg.learning.shop.service;

import org.springframework.stereotype.Service;
import ro.msg.learning.shop.dto.save.SaveCustomerDto;
import ro.msg.learning.shop.model.Customer;
import ro.msg.learning.shop.repository.CustomerRepository;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
public class CustomerService {
    private final CustomerRepository customers;

    public CustomerService(CustomerRepository customers) {
        this.customers = customers;
    }

    public Customer saveCustomer(SaveCustomerDto customer) {
        return customers.save(customer.toCustomer());
    }

    public Optional<Customer> findCustomerById(Long customerId) {
        return customers.findById(customerId);
    }

    public List<Customer> findAllCustomers() {
        return customers.findAll();
    }

    @Transactional
    public void deleteCustomerById(Long customerId) {
        if (customers.existsById(customerId)) {
            customers.deleteById(customerId);
        }
    }
}
