package com.rtcab.spock.introduction.example2

class TopThreeCustomerFinder {
    private int TOP_X = 3

    List<Customer> findTopCustomers(List<Customer> customers) {
        def customersByAmount = customers.sort { Customer customer -> customer.orders*.amount.sum() }
        def orderedCustomersByAmount = customersByAmount.reverse()

        if (orderedCustomersByAmount.size() < TOP_X) {
            throw new BadBusinessException()
        }
        orderedCustomersByAmount.take(TOP_X)
    }
}
