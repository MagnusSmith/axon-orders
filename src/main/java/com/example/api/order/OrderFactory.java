package com.example.api.order;

import com.example.order.query.OrderEntry;
import com.example.order.query.OrderLineEntry;

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
