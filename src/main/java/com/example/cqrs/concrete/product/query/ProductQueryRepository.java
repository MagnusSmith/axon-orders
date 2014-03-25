package com.example.cqrs.concrete.product.query;

import com.example.cqrs.api.product.ProductId;
import com.example.common.repository.ReadOnlyRepository;

/**
 * Created by michael.klos on 25/02/14.
 */
public interface ProductQueryRepository extends ReadOnlyRepository<ProductEntry, ProductId> {
    long count();
}
