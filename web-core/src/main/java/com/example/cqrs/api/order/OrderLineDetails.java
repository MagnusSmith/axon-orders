package com.example.cqrs.api.order;

import com.example.cqrs.api.product.ProductId;

import java.math.BigDecimal;

/**
 * Created with IntelliJ IDEA.
 * User: magnus.smith
 * Date: 28/02/14
 * Time: 15:45
 *
 */
public interface OrderLineDetails {
    OrderLineId getLineId();

    ProductId getProductId();

    String getDescription();

    BigDecimal getPrice();

    Integer getQuantity();
}
