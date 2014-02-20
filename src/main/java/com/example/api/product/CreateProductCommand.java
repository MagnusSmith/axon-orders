package com.example.api.product;

import org.axonframework.commandhandling.annotation.TargetAggregateIdentifier;

/**
 * Created by michael.klos on 19/02/14.
 */
public class CreateProductCommand {

    @TargetAggregateIdentifier
    private final ProductId productId;

    private final String modelNumber;
    private final String brand;

    public CreateProductCommand(ProductId productId, String modelNumber, String brand) {
        this.productId = productId;
        this.modelNumber = modelNumber;
        this.brand = brand;
    }

    public ProductId getProductId() {
        return productId;
    }

    public String getModelNumber() {
        return modelNumber;
    }

    public String getBrand() {
        return brand;
    }
}
