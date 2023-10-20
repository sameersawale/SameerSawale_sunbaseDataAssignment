package com.example.sunbaseData.Service;

import com.example.sunbaseData.Model.Customer;
import com.example.sunbaseData.Repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CustomerService {

    @Autowired
    CustomerRepository customerRepository;

    public Customer addCustomer(Customer customer){
        return customerRepository.save(customer);
    }

    public List<Customer> getCustomerList(){
        return customerRepository.findAll();
    }

    public String deleteCustomer(int id){
        customerRepository.deleteById(id);
        return "deleted successfully...";
    }

    public Customer updateCustomer(int id, Customer customer){
        Customer customer1=customerRepository.findById(id).get();
        customer1.setFirstName(customer.getFirstName());
        customer1.setLastName(customer.getLastName());
        customer1.setCity(customer.getCity());
        customer1.setState(customer.getState());
        customer1.setAddress(customer.getAddress());
        customer1.setStreet(customer.getStreet());
        customer1.setPhone(customer.getPhone());
        customer1.setEmail(customer.getEmail());

        customerRepository.save(customer1);
        return customer1;
    }

    public Customer getCustomer(int id){
        return customerRepository.findById(id).get();
    }
}
