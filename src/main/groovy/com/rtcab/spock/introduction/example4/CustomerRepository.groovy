package com.rtcab.spock.introduction.example4

import org.springframework.stereotype.Repository

@Repository
class CustomerRepository {

    static final Map customers = [
            "1": new Customer(name: "Waylon Smithers", orders: [new Order(amount: 500)]),
            "2": new Customer(name: "Ralph Wiggum", orders: [new Order(amount: 250)]),
            "3": new Customer(name: "Ned Flanders", orders: [new Order(amount: 175)])
    ]

    Customer findCustomerById(String customerId) {
        customers.get(customerId)
    }
}
