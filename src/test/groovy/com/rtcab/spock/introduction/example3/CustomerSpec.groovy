package com.rtcab.spock.introduction.example3

import spock.lang.Specification


/**
 * Example 3:
 *
 * Customer allows to add an order through using the OrderPlacementService
 *
 * - dependencies & mocking
 * - side effect free
 *
 */
class CustomerSpec extends Specification {


    def "placeOrder adds an order to the customer"() {

        given:
        def orderPlacementService = Mock(OrderPlacementService)

        orderPlacementService.placeOrder(_,_) >> {Customer customer, Order order -> customer.orders << order }

        and:
        Customer sut = new Customer(customerService: orderPlacementService)
        Order order = new Order()

        when:
        sut.placeOrder(order)

        then:
        sut.orders.contains(order)
    }

    def "placeOrder calls the customerService for doing the heavy lifting"() {

        given:
        def orderPlacementService = Mock(OrderPlacementService)

        and:
        Customer sut = new Customer(customerService: orderPlacementService)
        Order order = new Order()

        when:
        sut.placeOrder(order)

        then:
        1 * orderPlacementService.placeOrder(sut, order)
    }
}
