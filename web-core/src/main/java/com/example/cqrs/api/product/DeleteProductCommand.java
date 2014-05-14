package com.example.cqrs.api.product;

import org.axonframework.commandhandling.annotation.TargetAggregateIdentifier;

/**
 * Created by michael.klos on 25/02/14.
 */
public class DeleteProductCommand {

    @TargetAggregateIdentifier
    private final ProductId productId;

    public DeleteProductCommand(ProductId productId) {
        this.productId = productId;
    }

    public ProductId getProductId() {
        return productId;
    }
}
