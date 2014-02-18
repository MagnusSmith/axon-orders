package com.example.orders.api;

/**
 * Created with IntelliJ IDEA.
 * User: magnus.smith
 * Date: 10/02/14
 * Time: 11:55
 *
 */
public class OrderCreatedEvent {

    private final OrderId orderId;
    private final String productId;

    public OrderCreatedEvent(OrderId orderId, String productId) {
        this.orderId = orderId;
        this.productId = productId;
    }

    public String getProductId() {
        return productId;
    }

    public OrderId getOrderId() {
        return orderId;
    }

}
