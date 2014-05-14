package com.example.cqrs.concrete.product.query;

import com.example.cqrs.api.product.ProductDetails;
import com.example.cqrs.api.product.ProductId;

/**
 * Created by michael.klos on 03/03/14.
 */
public class ProductFactory {

    public static ProductDetails create(String modelNumber, String brand) {
        return new ProductEntry(new ProductId(), modelNumber, brand);
    }

    public static ProductDetails create(String productId, String modelNumber, String brand) {
        return new ProductEntry(new ProductId(productId), modelNumber, brand);
    }

}
