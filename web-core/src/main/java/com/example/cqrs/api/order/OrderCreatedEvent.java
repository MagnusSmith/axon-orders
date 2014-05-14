package com.example.cqrs.api.order;

/**
 * Created with IntelliJ IDEA.
 * User: magnus.smith
 * Date: 10/02/14
 * Time: 11:55
 *
 */
public class OrderCreatedEvent {

    private OrderDetails details;


    public OrderCreatedEvent(OrderDetails details) {
        this.details = details;
    }

    public OrderDetails getOrderDetails() {
        return details;
    }



}
