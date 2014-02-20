package com.example.api.product;

/**
 * Created by michael.klos on 19/02/14.
 */
public class ProductCreatedEvent {

    private final ProductId productId;
    private final String modelNumer;
    private final String brand;

    public ProductCreatedEvent(ProductId productId, String modelNumer, String brand) {
        this.productId = productId;
        this.modelNumer = modelNumer;
        this.brand = brand;
    }

    public ProductId getProductId() {
        return productId;
    }

    public String getBrand() {
        return brand;
    }

    public String getModelNumer() {
        return modelNumer;
    }
}
