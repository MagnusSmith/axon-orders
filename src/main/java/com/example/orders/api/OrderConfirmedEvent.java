package com.example.orders.api;

/**
 * Created with IntelliJ IDEA.
 * User: magnus.smith
 * Date: 10/02/14
 * Time: 11:58
 *
 */
public class OrderConfirmedEvent  {
    private final OrderId orderId;


    public OrderConfirmedEvent(OrderId orderId) {
        this.orderId = orderId;
    }
}
