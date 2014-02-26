package com.example.api.order;

/**
 * Created with IntelliJ IDEA.
 * User: magnus.smith
 * Date: 24/02/14
 * Time: 09:49
 * Copyright Advanced Computer Software Group 2014
 */
public final class OrderLineRemovedEvent {

    private final OrderLineId orderLineId;

    public OrderLineRemovedEvent(OrderLineId lineId){
        this.orderLineId = lineId;
    }


    public OrderLineId getOrderLineId() {
        return orderLineId;
    }
}
