package com.example.cqrs.concrete.order.query;

import com.example.cqrs.api.order.OrderDetails;
import com.example.cqrs.api.order.OrderId;
import com.example.cqrs.api.order.OrderLineDetails;

import java.util.Date;
import java.util.List;

/**
 * Created by magnus on 01/03/14.
 */
public class OrderFactory {

    public static OrderDetails create(OrderId orderId, Date dateTimeOfSubmission, List<? extends OrderLineDetails> lines) {
        return new OrderEntry(orderId, dateTimeOfSubmission, (List<OrderLineEntry>) lines);

    }

}
