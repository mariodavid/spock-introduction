package com.rtcab.spock.introduction.example4

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class CustomerService {

    @Autowired
    CustomerRepository customerRepository

    Customer getCustomer(String customerId) {
        customerRepository.findCustomerById(customerId)
    }
}
