package com.rtcab.spock.introduction.example3

class Customer {
    String name
    List<Order> orders = []

    CustomerService customerService

    void placeOrder(Order order) {
        customerService.placeOrder(this, order)
    }

    @Override
    String toString() {
        name + " (" + orders*.amount.sum() + ")"
    }
}
