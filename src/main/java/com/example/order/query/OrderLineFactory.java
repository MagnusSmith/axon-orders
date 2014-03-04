package com.example.order.query;

import com.example.api.order.OrderLineDetails;
import com.example.api.order.OrderLineId;
import com.example.api.product.ProductId;
import com.example.order.query.OrderLineEntry;

import java.math.BigDecimal;

/**
 * Created with IntelliJ IDEA.
 * User: magnus.smith
 * Date: 28/02/14
 * Time: 15:46
 *
 */
public class OrderLineFactory {

    public static OrderLineDetails create(OrderLineId lineId, ProductId productId, String description, BigDecimal price, Integer quantity) {
        return new OrderLineEntry(lineId, productId, description, price, quantity);
    }

    public static OrderLineDetails create(ProductId productId, String description, BigDecimal price, Integer quantity) {
        return new OrderLineEntry(productId, description, price, quantity);
    }
}
