package com.rtcab.spock.introduction.example2

import spock.lang.Shared
import spock.lang.Specification
import spock.lang.Unroll


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
    @Shared
    private Customer moe = customerWithTurnover("Moe", 100)
    @Shared
    private Customer edna = customerWithTurnover("Edna", 110)
    @Shared
    private Customer abu = customerWithTurnover("Abu", 120)
    @Shared
    private Customer milhouse = customerWithTurnover("Milhouse", 175)
    @Shared
    private Customer poorMario = customerWithTurnover("Mario", 5)


    def 'TopThreeCustomerFinderSpec returns only three customers'() {
        given:
        def sut = new TopThreeCustomerFinder()

        and:
        def customers = [moe, edna, abu, poorMario]

        when:
        def topThreeCustomers = sut.findTopCustomers(customers)

        then:
        topThreeCustomers.size() == 3
    }


    @Unroll
    def "TopThreeCustomerFinder takes #expectedResult from #allCustomers"() {
        given:
        def sut = new TopThreeCustomerFinder()

        when:
        def topThreeCustomers = sut.findTopCustomers(allCustomers)

        then:
        topThreeCustomers == expectedResult

        where:
        allCustomers                || expectedResult
        [moe, edna, abu, poorMario] || [abu, edna, moe]
        [moe, edna, abu, milhouse]  || [milhouse, abu, edna]
        [moe, edna, abu]            || [abu, edna, moe]
        [moe, edna]                 || [edna, moe]

    }

    private Customer customerWithTurnover(String name, int totalTurnover) {
        new Customer(name: name, orders: [order(totalTurnover)])
    }

    private Order order(int amount) {
        new Order(amount: amount)
    }

}
