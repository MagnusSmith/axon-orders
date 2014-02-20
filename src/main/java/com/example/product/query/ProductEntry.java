package com.example.product.query;

import com.example.api.product.ProductId;

import javax.persistence.Entity;
import javax.persistence.Id;

/**
 * Created by michael.klos on 19/02/14.
 */
@Entity
public class ProductEntry {

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
}
