package com.example.order.command;

import com.example.api.order.OrderLineDetails;
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


    private final OrderLineDetails details;



    public OrderLine(OrderLineDetails details){
        this.details = details;
    }

    public OrderLineDetails getDetails() {
        return details;
    }
}
