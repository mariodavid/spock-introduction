package com.rtcab.spock.introduction.example2

class TopThreeCustomerFinder {

    List<Customer> findTopCustomers(List<Customer> customers) {
        def customersByAmount = customers.sort { Customer customer -> customer.orders*.amount.sum() }
        customersByAmount.reverse().take(3)
    }
}
