package com.example.web.rest.product;

import com.example.cqrs.api.product.ProductDetails;
import com.example.cqrs.concrete.product.query.ProductFactory;
import com.google.common.base.Function;
import com.google.common.collect.FluentIterable;

import java.io.Serializable;
import java.util.List;

/**
 * Created by michael.klos on 26/02/14.
 */
public class ProductTO implements Serializable {

    private String id;
    private String modelNumber;
    private String brand;

    public static ProductTO fromProductDetails(ProductDetails productDetails) {
        ProductTO productTO = new ProductTO();
        productTO.id = productDetails.getId().toString();
        productTO.modelNumber = productDetails.getModelNumber();
        productTO.brand = productDetails.getBrand();
        return productTO;
    }

    public static List<ProductTO> fromProductsDetails(List<? extends ProductDetails> productsDetails) {
        return FluentIterable.from(productsDetails).transform(new Function<ProductDetails, ProductTO>() {
            @Override
            public ProductTO apply(ProductDetails product) {
                    return fromProductDetails(product);
            }
        }).toList();
    }

    public ProductDetails toProductDetails() {
        return id == null ? ProductFactory.create(modelNumber,brand) : ProductFactory.create(id, modelNumber, brand);
    }

    @Override
    public String toString() {
        return "Product{" +
                "modelNumber='" + modelNumber + '\'' +
                ", brand='" + brand + '\'' +
                ", id='"+id+ '\'' +
                "}";
    }

    public String getId() {
        return id;
    }

    public String getModelNumber() {
        return modelNumber;
    }

    public String getBrand() {
        return brand;
    }
}
