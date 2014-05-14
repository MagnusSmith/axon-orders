package com.example.web.ui.order;

import com.example.cqrs.api.order.OrderDetails;
import com.example.cqrs.api.order.OrderId;
import com.example.cqrs.api.order.OrderLineDetails;
import com.example.cqrs.concrete.order.query.OrderFactory;
import com.google.common.base.Function;
import com.google.common.base.Predicate;
import com.google.common.collect.FluentIterable;
import com.google.common.collect.Iterables;

import javax.validation.constraints.NotNull;
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

    @NotNull
    private Date date;

    private List<OrderLine> lines = new ArrayList<>();

    private String identifier;

    public Order(){
         date = new Date();
    }

    public void addLine(String productIdentifier, String description, BigDecimal price, int quantity){
        OrderLine line = new OrderLine(productIdentifier, description, price, quantity);
        lines.add(line);
    }

    void addLine(OrderLine line){
        lines.add(line);
    }

    void removeLine(final String lineIdentifier){
            final boolean found = Iterables.removeIf(lines, new Predicate<OrderLine>() {
                @Override
                public boolean apply(OrderLine line) {
                    return line.getIdentifier().equals(lineIdentifier);
                }
            });
    }

    void removeLine(final int index){
        lines.remove(index);
    }

    public void submit(){
        date = new Date();
    }

    public Date getOrderDate() {
        return date;
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
            return OrderFactory.create(new OrderId(identifier), date, detailLines);
        } else {
            return OrderFactory.create(new OrderId(), date, detailLines);
        }

    }

    public static Order fromOrderDetails(OrderDetails details){
        Order order = new Order();
        order.identifier = details.getOrderId().toString();
        order.date = details.getDateTimeOfSubmission();
        order.lines = FluentIterable.from(details.getLines()).transform(new Function<OrderLineDetails, OrderLine>(){
            @Override
            public OrderLine apply(OrderLineDetails line) {
                return OrderLine.fromOrderLineDetails(line);
            }
        }).toList();
        return order;
    }


    @Override
    public String toString() {
       return "Order [identifier=" + identifier + ", date=" + date + ", lines=" + lines + "]";
    }
}
