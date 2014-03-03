package com.example.api.product;

import org.axonframework.commandhandling.annotation.TargetAggregateIdentifier;

/**
 * Created by michael.klos on 03/03/14.
 */
public class UpdateProductCommand {

    @TargetAggregateIdentifier
    private final ProductId productId;

    private final ProductDetails productDetails;

    public UpdateProductCommand(ProductDetails productDetails) {
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
