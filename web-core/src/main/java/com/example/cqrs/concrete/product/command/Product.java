package com.example.cqrs.concrete.product.command;

import com.example.common.logging.Logger;
import com.example.cqrs.api.product.*;
import org.axonframework.eventsourcing.annotation.AbstractAnnotatedAggregateRoot;
import org.axonframework.eventsourcing.annotation.AggregateIdentifier;
import org.axonframework.eventsourcing.annotation.EventSourcingHandler;

/**
 * Created by michael.klos on 19/02/14.
 */
public class Product extends AbstractAnnotatedAggregateRoot {

    @Logger
    private java.util.logging.Logger log;

    @AggregateIdentifier
    private ProductId productId;

    Product(){}

    public Product(ProductDetails productDetails){
        apply(new ProductCreatedEvent(productDetails));
    }

    public void delete() {
        markDeleted();
        apply(new ProductDeletedEvent(productId));
    }

    public void update(ProductDetails productDetails) {
        apply(new ProductUpdatedEvent(productDetails));
    }

    @EventSourcingHandler
    public void on(ProductCreatedEvent event) {
        this.productId = event.getProductDetails().getId();
    }

    @Override
    public ProductId getIdentifier() {
        return productId;
    }
}
