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
 * - exception handling in tests
 *
 */
class TopThreeCustomerFinderSpec extends Specification {
    @Shared
    private Customer moe = customerWithTurnover("Moe", 100)
    @Shared
    private Customer edna = customerWithTurnover("Edna Krabappel", 110)
    @Shared
    private Customer apu = customerWithTurnover("Apu Nahasapeemapetilon", 120)
    @Shared
    private Customer milhouse = customerWithTurnover("Milhouse van Houten", 175)
    @Shared
    private Customer poorMario = customerWithTurnover("Mario David", 5)
    private sut


    void setup() {
        sut = new TopThreeCustomerFinder()
    }

    def 'TopThreeCustomerFinder returns only three customers'() {
        given:
        def customers = [moe, edna, apu, poorMario]

        when:
        def topThreeCustomers = sut.findTopCustomers(customers)

        then:
        topThreeCustomers.size() == 3
    }


    @Unroll
    def "TopThreeCustomerFinder takes #expectedResult from #allCustomers"() {
        when:
        def topThreeCustomers = sut.findTopCustomers(allCustomers)

        then:
        topThreeCustomers == expectedResult

        where:
        allCustomers                || expectedResult
        [moe, edna, apu, poorMario] || [apu, edna, moe]
        [moe, edna, apu, milhouse]  || [milhouse, apu, edna]
        [moe, edna, apu]            || [apu, edna, moe]

    }


    def "TopThreeCustomerFinder throws a BadBusinessException in case there are less than three customers available"() {
        when:
        sut.findTopCustomers([apu, milhouse])
        then:
        thrown(BadBusinessException)
    }


    private Customer customerWithTurnover(String name, int totalTurnover) {
        new Customer(name: name, orders: [order(totalTurnover)])
    }

    private Order order(int amount) {
        new Order(amount: amount)
    }

}
