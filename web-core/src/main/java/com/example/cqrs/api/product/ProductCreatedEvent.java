package com.example.cqrs.api.product;

/**
 * Created by michael.klos on 19/02/14.
 */
public class ProductCreatedEvent {

    private final ProductDetails productDetails;

    public ProductCreatedEvent(ProductDetails productDetails) {
        this.productDetails = productDetails;
    }

    public ProductDetails getProductDetails() {
        return productDetails;
    }
}
