package com.gaku.ddd.agilepm.infrastructure.persistence;

import com.gaku.ddd.agilepm.domain.model.product.Product;
import com.gaku.ddd.agilepm.domain.model.product.ProductId;
import com.gaku.ddd.agilepm.domain.model.product.ProductRepository;

public class CoherenceProductRepository implements ProductRepository {
    @Override
    public ProductId nextIdentity() {
        return null;
    }

    @Override
    public void save(Product aProduct) {

    }
}
