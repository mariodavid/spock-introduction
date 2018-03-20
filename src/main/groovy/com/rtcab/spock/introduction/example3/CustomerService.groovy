package com.rtcab.spock.introduction.example3

class CustomerService {

    void placeOrder(Customer customer, Order order) {
        if (someHeavyBusinessRulesSayYes(order)) {
            customer.orders << order
        }
    }

    private boolean someHeavyBusinessRulesSayYes(Order order) {
        order.amount > 100
    }
}
