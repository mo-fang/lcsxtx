package com.mo.fang.springcloudsystem.system.serviceI;

import com.mo.fang.springcloudsystem.system.entity.Product;

import java.util.List;

/**
 * create by Mofang_ysc on 2018/10/18 0018
 */

public interface ProductService {
    List<Product> getAllProducts(Product product);

}
