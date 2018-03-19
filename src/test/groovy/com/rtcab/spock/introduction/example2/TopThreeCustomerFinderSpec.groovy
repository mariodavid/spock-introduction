package com.rtcab.spock.introduction.example2

import spock.lang.Specification


/**
 * Example 2:
 *
 * TopThreeCustomerFinder find the top 10 customers ordered by their total turnover
 *
 * - no dependencies
 * - side effect free
 * - data driven test
 *
 */
class TopThreeCustomerFinderSpec extends Specification {


    def 'TopThreeCustomerFinderSpec sorts customers by total turnover'() {
        given:
        def sut = new TopThreeCustomerFinder()

        and:
        def poorMario = customerWithTurnover("Mario", 5)

        def abu = customerWithTurnover("Abu", 120)
        def moe = customerWithTurnover("Moe", 110)
        def edna = customerWithTurnover("Edna", 100)

        def customers = [moe, edna, abu, poorMario]

        when:
        def topThreeCustomers = sut.findTopCustomers(customers)

        then:
        topThreeCustomers.size() == 3

        and:
        topThreeCustomers == [abu, moe, edna]
    }

    private Customer customerWithTurnover(String name, int totalTurnover) {
        new Customer(name: name, orders: [order(totalTurnover)])
    }

    private Order order(int amount) {
        new Order(amount: amount)
    }

}
