package com.example.api.order;

/**
 * Created with IntelliJ IDEA.
 * User: magnus.smith
 * Date: 19/02/14
 * Time: 16:17
 *
 */
public class OrderLineAddedEvent {

    private final OrderLineId orderLineId;
    private final OrderId orderId;
    private final OrderLineEntry details;

    public OrderLineAddedEvent(OrderLineId orderLineId, OrderId orderId, OrderLineEntry details) {
        this.orderLineId = orderLineId;
        this.orderId = orderId;
        this.details = details;
    }



}
