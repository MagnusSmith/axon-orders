package com.example.cqrs.concrete.order.query;

import com.example.cqrs.api.order.OrderLineDetails;
import com.example.cqrs.api.order.OrderLineId;
import com.example.cqrs.api.product.ProductId;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.math.BigDecimal;

/**
 * Created with IntelliJ IDEA.
 * User: magnus.smith
 * Date: 19/02/14
 * Time: 13:52
 *
 */
@Entity
public class OrderLineEntry implements OrderLineDetails {

    @Id
    private OrderLineId lineId;
    private ProductId productId;
    private String description;
    private BigDecimal price;
    private Integer quantity;


    private OrderLineEntry() {
    }

    ;

    OrderLineEntry(OrderLineId lineId, ProductId productId, String description, BigDecimal price, Integer quantity) {
        this.productId = productId;
        this.description = description;
        this.price = price;
        this.quantity = quantity;
        this.lineId = lineId;
    }

    OrderLineEntry(ProductId productId, String description, BigDecimal price, Integer quantity) {
        this.productId = productId;
        this.description = description;
        this.price = price;
        this.quantity = quantity;
        this.lineId = new OrderLineId();
    }


    @Override
    public OrderLineId getLineId() {
        return lineId;
    }

    @Override
    public ProductId getProductId() {
        return productId;
    }

    @Override
    public String getDescription() {
        return description;
    }

    @Override
    public BigDecimal getPrice() {
        return price;
    }

    @Override
    public Integer getQuantity() {
        return quantity;
    }


}
