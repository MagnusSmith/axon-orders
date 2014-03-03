package com.example.product.query;

import com.example.api.product.ProductDetails;
import com.example.api.product.ProductId;

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
