package com.rtcab.spock.introduction.example1

class TotalAmountCalculator {

    Integer calculateAmount(List<Order> orders) {
        orders.sum { Order order -> order.amount } ?: 0 as Integer
    }
}
