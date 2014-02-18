package com.example.orders.command;

import com.example.orders.api.OrderCreatedEvent;
import com.example.orders.api.OrderId;
import org.axonframework.eventsourcing.annotation.AbstractAnnotatedAggregateRoot;
import org.axonframework.eventsourcing.annotation.AggregateIdentifier;
import org.axonframework.eventsourcing.annotation.EventSourcingHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
* Created with IntelliJ IDEA.
* User: magnus.smith
* Date: 10/02/14
* Time: 13:55
*
*/
public class Order extends AbstractAnnotatedAggregateRoot {

    private final static Logger logger = LoggerFactory.getLogger(Order.class);

    @AggregateIdentifier
    private OrderId orderId;

     Order(){}

    public Order(OrderId identifier, String productId){
        apply(new OrderCreatedEvent(identifier, productId));
    }


    @EventSourcingHandler
    public void on(OrderCreatedEvent event) {
        this.orderId = event.getOrderId();
    }

    @Override
    public OrderId getIdentifier() {
        return orderId;
    }

}
