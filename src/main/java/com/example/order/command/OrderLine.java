package com.example.order.command;

import com.example.api.order.OrderLineEntry;
import com.example.api.order.OrderLineId;
import org.axonframework.eventsourcing.annotation.AbstractAnnotatedEntity;

/**
 * Created with IntelliJ IDEA.
 * User: magnus.smith
 * Date: 19/02/14
 * Time: 16:08
 *
 */
public class OrderLine extends AbstractAnnotatedEntity {

    private final OrderLineId orderLineId;
    private final OrderLineEntry details;



    public OrderLine(OrderLineEntry details){
        this.details = details;
        this.orderLineId = new OrderLineId();
    }

    public OrderLineId getOrderLineId() {
        return orderLineId;
    }


    public OrderLineEntry getDetails() {
        return details;
    }
}
