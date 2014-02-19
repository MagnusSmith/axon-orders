package com.example.order.command;

import com.example.api.order.OrderLineId;
import com.example.api.product.ProductId;
import org.axonframework.eventsourcing.annotation.AbstractAnnotatedEntity;

import java.math.BigDecimal;

/**
 * Created with IntelliJ IDEA.
 * User: magnus.smith
 * Date: 19/02/14
 * Time: 16:08
 *
 */
public class OrderLine extends AbstractAnnotatedEntity {

    private final OrderLineId orderLineId;
    private final ProductId productId;
    private final String description;
    private final BigDecimal price;
    private final Integer quantity;


    public OrderLine(OrderLineId orderLineId, ProductId productId, String description, BigDecimal price, int quantity){
        this.productId = productId;
        this.price = price;
        this.quantity = quantity;

        this.orderLineId = orderLineId;
        this.description = description;
    }

    public OrderLineId getOrderLineId() {
        return orderLineId;
    }

    public String getDescription() {
        return description;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public ProductId getProductId() {
        return productId;
    }
}
