package com.rtcab.spock.introduction.example4

class Customer {
    String name
    List<com.rtcab.spock.introduction.example4.Order> orders = []

    com.rtcab.spock.introduction.example4.CustomerService customerService

    void placeOrder(com.rtcab.spock.introduction.example4.Order order) {
        customerService.placeOrder(this, order)
    }

    @Override
    String toString() {
        name + " (" + orders*.amount.sum() + ")"
    }
}
