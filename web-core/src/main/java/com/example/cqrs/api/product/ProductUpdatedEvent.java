package com.example.cqrs.api.product;

/**
 * Created by michael.klos on 03/03/14.
 */
public class ProductUpdatedEvent {

    private final ProductDetails productDetails;

    public ProductUpdatedEvent(ProductDetails productDetails) {
        this.productDetails = productDetails;
    }

    public ProductDetails getProductDetails() {
        return productDetails;
    }
}
