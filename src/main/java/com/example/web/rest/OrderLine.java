package com.example.web.rest;

import com.example.api.order.OrderLineDetails;
import com.example.api.order.OrderLineFactory;
import com.example.api.order.OrderLineId;
import com.example.api.product.ProductId;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * Created with IntelliJ IDEA.
 * User: magnus.smith
 * Date: 28/02/14
 * Time: 14:18
 */
public class OrderLine implements Serializable {

    private String description;

    private BigDecimal price;

    private String productIdentifier;

    private int quantity;

    private String identifier;


    private OrderLine() {
    }

    ;

    public OrderLine(String productIdentifier, String description, BigDecimal price, int quantity) {
        this.description = description;
        this.price = price;
        this.productIdentifier = productIdentifier;
        this.quantity = quantity;

    }


    public String getDescription() {
        return description;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public String getProductIdentifier() {
        return productIdentifier;
    }

    public int getQuantity() {
        return quantity;
    }


    public OrderLineDetails toOrderLineDetails() {
        if (identifier != null) {
            return OrderLineFactory.create(new OrderLineId(identifier), new ProductId(productIdentifier), description, price, quantity);
        } else {
            return OrderLineFactory.create(new ProductId(productIdentifier), description, price, quantity);
        }
    }

    public static OrderLine fromOrderLineDetails(OrderLineDetails details) {
        OrderLine line = new OrderLine();
        line.description = details.getDescription();
        line.identifier = details.getLineId().toString();
        line.productIdentifier = details.getProductId().toString();
        line.price = details.getPrice();
        line.quantity = details.getQuantity();
        return line;
    }
}
