package com.rtcab.spock.introduction.example2

class Customer {
    String name
    List<Order> orders

    @Override
    String toString() {
        name + " (" + orders*.amount.sum() + ")"
    }
}
