package com.example.cqrs.concrete.product.query;

import com.example.cqrs.api.product.ProductDetails;
import com.example.cqrs.api.product.ProductId;

import javax.persistence.Entity;
import javax.persistence.Id;

/**
 * Created by michael.klos on 19/02/14.
 */
@Entity
public class ProductEntry implements ProductDetails {

    @Id
    private ProductId id;

    private String modelNumber;

    private String brand;

    ProductEntry(){};

    ProductEntry(ProductId id, String modelNumber, String brand){
        this.id = id;
        this.modelNumber = modelNumber;
        this.brand = brand;
    }

    @Override
    public String getModelNumber() {
        return modelNumber;
    }

    @Override
    public String getBrand() {
        return brand;
    }

    @Override
    public ProductId getId() {
        return id;
    }
}
