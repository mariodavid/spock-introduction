package com.rtcab.spock.introduction.example1

import spock.lang.Specification


class TotalAmountCalculatorSpec extends Specification {


    def 'TotalAmountCalculator sums up the amount of orders amounts'() {
        given:
        def sut = new TotalAmountCalculator()

        and:
        def orders = [
                new Order(amount: 50),
                new Order(amount: 25),
                new Order(amount: 25),
        ]

        when:
        int totalAmount = sut.calculateAmount(orders)

        then:
        totalAmount == 100
    }

    def 'TotalAmountCalculator returns zero for an empty list'() {
        given:
        def sut = new TotalAmountCalculator()

        and:
        def orders = []

        when:
        int totalAmount = sut.calculateAmount(orders)

        then:
        totalAmount == 0
    }
}
