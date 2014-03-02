package com.example.web.rest;

import com.example.api.order.*;
import com.google.common.base.Function;
import com.google.common.collect.FluentIterable;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


/**
 * Created with IntelliJ IDEA.
 * User: magnus.smith
 * Date: 27/02/14
 * Time: 13:04
 *
 */
public class Order implements Serializable {

    private Date dateTimeOfSubmission;

    private List<OrderLine> lines = new ArrayList<>();

    private String identifier;

    public void addLine(String productIdentifier, String description, BigDecimal price, int quantity){
        OrderLine line = new OrderLine(productIdentifier, description, price, quantity);
        lines.add(line);
    }

    public void submit(){
        dateTimeOfSubmission = new Date();
    }

    public Date getDateTimeOfSubmission() {
        return dateTimeOfSubmission;
    }

    public List<OrderLine> getLines() {
        return lines;
    }

    public String getIdentifier() {
        return identifier;
    }

    public OrderDetails toOrderDetails(){
        List<? extends OrderLineDetails> detailLines = FluentIterable.from(lines).transform(new Function<OrderLine, OrderLineDetails>() {
            @Override
            public OrderLineDetails apply(OrderLine line) {
                return line.toOrderLineDetails();
            }
        }).toList();

        if (identifier != null) {
            return OrderFactory.create(new OrderId(identifier), dateTimeOfSubmission, detailLines);
        } else {
            return OrderFactory.create(new OrderId(), dateTimeOfSubmission, detailLines);
        }

    }

    public static Order fromOrderDetails(OrderDetails details){
        Order order = new Order();
        order.identifier = details.getOrderId().toString();
        order.dateTimeOfSubmission = details.getDateTimeOfSubmission();
        order.lines = FluentIterable.from(details.getLines()).transform(new Function<OrderLineDetails, OrderLine>(){
            @Override
            public OrderLine apply(OrderLineDetails line) {
                return OrderLine.fromOrderLineDetails(line);
            }
        }).toList();
        return order;
    }
}
