package com.mo.fang.springcloudsystem.system.serviceImpl;

import com.mo.fang.springcloudsystem.system.entity.Product;
import com.mo.fang.springcloudsystem.system.mapper.ProductMapper;
import com.mo.fang.springcloudsystem.system.serviceI.ProductService;
import javax.inject.Inject;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * create by Mofang_ysc on 2018/10/18 0018
 */

@Service
public class ProductServiceImpl implements ProductService {
    @Inject
    private ProductMapper productMapper;


    @Override
    public List<Product> getAllProducts(Product product) {
        List<Product> allProducts = productMapper.getAllProducts(product);
        return allProducts;
    }
}
