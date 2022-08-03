package ro.msg.learning.shop.service;

import org.springframework.stereotype.Service;
import ro.msg.learning.shop.model.Customer;
import ro.msg.learning.shop.repository.CustomerRepository;
import ro.msg.learning.shop.service.exception.NullEntityException;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
public class CustomerService {
    private final CustomerRepository customerRepository;

    public CustomerService(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    public Customer saveCustomer(Customer customer) {
        if (customer == null) {
            throw new NullEntityException(Customer.class);
        }

        return customerRepository.save(customer);
    }

    public Optional<Customer> findCustomerById(Long customerId) {
        return customerRepository.findById(customerId);
    }

    public List<Customer> findAllCustomers() {
        return customerRepository.findAll();
    }

    @Transactional
    public void deleteCustomerById(Long customerId) {
        if (customerRepository.existsById(customerId)) {
            customerRepository.deleteById(customerId);
        }
    }
}
