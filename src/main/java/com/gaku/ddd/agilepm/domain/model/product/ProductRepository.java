package com.gaku.ddd.agilepm.domain.model.product;

/**
 * 资源库访问接口
 */
public interface ProductRepository {

    public ProductId nextIdentity();
    public void save(Product aProduct);

}
