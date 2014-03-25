package com.example.web.ui.order;

import com.example.cqrs.api.order.OrderLineDetails;
import com.example.cqrs.api.order.OrderLineId;
import com.example.cqrs.api.product.ProductId;
import com.example.cqrs.concrete.order.query.OrderLineFactory;

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


    OrderLine() {
        identifier = (new OrderLineId()).toString();
    }

    ;

    public OrderLine(String productIdentifier, String description, BigDecimal price, int quantity) {
        this();
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

    String getIdentifier() { return identifier; }


    void setDescription(String description) {
        this.description = description;
    }

    void setPrice(BigDecimal price) {
        this.price = price;
    }

    void setProductIdentifier(String productIdentifier) {
        this.productIdentifier = productIdentifier;
    }

    void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public OrderLineDetails toOrderLineDetails() {
        return OrderLineFactory.create(new OrderLineId(identifier), new ProductId(productIdentifier), description, price, quantity);
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
