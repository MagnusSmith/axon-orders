package com.example.cqrs.api.product;

/**
 * Created by michael.klos on 25/02/14.
 */
public class ProductDeletedEvent {

    private final ProductId productId;

    public ProductDeletedEvent(ProductId productId) {
        this.productId = productId;
    }

    public ProductId getProductId() {
        return productId;
    }
}
