package com.example.api.order;

import com.example.api.product.ProductId;
import com.example.order.query.OrderLineEntry;

import java.math.BigDecimal;

/**
 * Created with IntelliJ IDEA.
 * User: magnus.smith
 * Date: 28/02/14
 * Time: 15:46
 * Copyright Advanced Computer Software Group 2014
 */
public class OrderLineFactory {

    public static OrderLineDetails create(ProductId productId, String description, BigDecimal price, Integer quantity) {
        return new OrderLineEntry(productId, description, price, quantity);
    }
}
