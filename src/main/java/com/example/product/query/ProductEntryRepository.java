package com.example.product.query;

import com.example.api.product.ProductId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by michael.klos on 19/02/14.
 */
@Repository
public interface ProductEntryRepository extends JpaRepository<ProductEntry, ProductId> {
}
