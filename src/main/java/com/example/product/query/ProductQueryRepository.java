package com.example.product.query;

import com.example.api.product.ProductId;
import com.example.config.ReadOnlyRepository;

/**
 * Created by michael.klos on 25/02/14.
 */
public interface ProductQueryRepository extends ReadOnlyRepository<ProductEntry, ProductId> {
    long count();
}
