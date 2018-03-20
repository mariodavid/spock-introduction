package com.rtcab.spock.introduction.example4

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping(produces = 'application/json')
class CustomerController {

    @Autowired
    private CustomerService service

    @GetMapping('/customers/{customerId}')
    Customer getCustomer(@PathVariable String customerId) {
        service.getCustomer(customerId)
    }
}
