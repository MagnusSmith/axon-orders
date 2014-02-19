package com.example.order.query;

import com.example.api.order.OrderLineId;

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
public class OrderLineEntry {

    @Id
    private OrderLineId orderLineId;
    private String description;
    private BigDecimal price;
    private Integer quantity;


    OrderLineEntry(){};



    public BigDecimal totalPrice(){
        return BigDecimal.ZERO;
    }

    public static class Builder {
        private final OrderLineId orderLineId;
        private String description;
        private BigDecimal price;
        private Integer quantity;

        public Builder(OrderLineId id){
             this.orderLineId = id;
        }

        public Builder description(String description){
            this.description = description;
            return this;
        }

        public Builder price(BigDecimal price){
            this.price = price;
            return this;
        }

        public Builder quantity(int quantity){
            this.quantity = quantity;
            return this;
        }
    }
}
