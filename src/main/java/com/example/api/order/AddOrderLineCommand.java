package com.example.api.order;

import com.example.api.product.ProductId;
import org.axonframework.commandhandling.annotation.TargetAggregateIdentifier;

import java.math.BigDecimal;

/**
 * Created with IntelliJ IDEA.
 * User: magnus.smith
 * Date: 19/02/14
 * Time: 16:25
 *
 */
public class AddOrderLineCommand {


    private final OrderLineId orderLineId;
    private final ProductId productId;


    @TargetAggregateIdentifier
    private final OrderId orderId;
    private final String description;
    private final BigDecimal price;
    private final Integer quantity;

    public AddOrderLineCommand(OrderLineId orderLineId, ProductId productId, OrderId orderId, String description, BigDecimal price, Integer quantity) {
        this.orderLineId = orderLineId;
        this.productId = productId;
        this.orderId = orderId;
        this.description = description;
        this.price = price;
        this.quantity = quantity;
    }

    public OrderLineId getOrderLineId() {
        return orderLineId;
    }

    public ProductId getProductId() {
        return productId;
    }

    public OrderId getOrderId() {
        return orderId;
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
}
