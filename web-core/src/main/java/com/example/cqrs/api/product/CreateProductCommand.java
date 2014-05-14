package com.example.cqrs.api.product;

import org.axonframework.commandhandling.annotation.TargetAggregateIdentifier;

/**
 * Created by michael.klos on 19/02/14.
 */
public class CreateProductCommand {

    @TargetAggregateIdentifier
    private final ProductId productId;

    private final ProductDetails productDetails;

    public CreateProductCommand(ProductDetails productDetails) {
        this.productDetails = productDetails;
        this.productId = productDetails.getId();
    }

    public ProductId getProductId() {
        return productId;
    }

    public ProductDetails getProductDetails() {
        return productDetails;
    }
}
