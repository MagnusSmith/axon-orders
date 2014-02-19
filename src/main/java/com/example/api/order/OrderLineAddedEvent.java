package com.example.api.order;

import com.example.api.product.ProductId;

import java.math.BigDecimal;

/**
 * Created with IntelliJ IDEA.
 * User: magnus.smith
 * Date: 19/02/14
 * Time: 16:17
 *
 */
public class OrderLineAddedEvent {

    private final OrderLineId orderLineId;
    private final ProductId productId;
    private final OrderId orderId;
    private final String description;
    private final BigDecimal price;
    private final Integer quantity;

    public OrderLineAddedEvent(OrderLineId orderLineId, ProductId productId, OrderId orderId, String description, BigDecimal price, Integer quantity) {
        this.orderLineId = orderLineId;
        this.productId = productId;
        this.orderId = orderId;
        this.description = description;
        this.price = price;
        this.quantity = quantity;
    }
}
