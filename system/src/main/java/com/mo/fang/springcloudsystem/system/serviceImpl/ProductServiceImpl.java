package com.mo.fang.springcloudsystem.system.serviceImpl;

import com.mo.fang.springcloudsystem.system.entity.Product;
import com.mo.fang.springcloudsystem.system.mapper.ProductMapper;
import com.mo.fang.springcloudsystem.system.serviceI.ProductService;
import com.mo.fang.springcloudsystem.system.serviceI.RedisService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import utils.QrCodeUtil;

import javax.inject.Inject;
import java.io.File;
import java.util.List;

/**
 * create by Mofang_ysc on 2018/10/18 0018
 */

@Service
public class ProductServiceImpl implements ProductService {
    @Inject
    private ProductMapper productMapper;
    @Autowired
    private RedisService redisService;
    @Value("${oa.redis.system-data.prefix}")
    private String PREFIX;

    @Override
    public List<Product> getAllProducts(Product product) {
        List<Product> allProducts = productMapper.getAllProducts(product);
        return allProducts;
    }

    @Override
    public void mkcode(Product product) {
        String codetiaopath = (String) redisService.get(PREFIX+"PARAS-CODETIAOPATH");
        String codeerpath = (String) redisService.get(PREFIX+"PARAS-CODEERPATH");
        String tiao = codetiaopath+System.getProperty("file.separator")+product.getId()+".png";
        String er = codeerpath+System.getProperty("file.separator")+product.getId()+".png";
        QrCodeUtil.encodeBarCode(product.getCode(),105,50,tiao);
        QrCodeUtil.encodeQRCode(product.getCode(),105,105,er);
    }

    @Transactional
    @Override
    public boolean saveOrUpdate(Product product) {
        int i = productMapper.saveOrUpdate(product);
        boolean flag = i<0?false:true;
        return flag;
    }
    @Transactional
    @Override
    public boolean deleteByPrimaryKey(Integer id) {
        int i = productMapper.deleteByPrimaryKey(id);
        boolean flag = i<0?false:true;
        return flag;
    }

    @Override
    public Product getProductById(Integer id) {
        return productMapper.selectByPrimaryKey(id);
    }

    @Override
    public void deleteCode(Integer id) {
        String codetiaopath = (String) redisService.get(PREFIX+"PARAS-CODETIAOPATH");
        String codeerpath = (String) redisService.get(PREFIX+"PARAS-CODEERPATH");
        String tiao = codetiaopath+System.getProperty("file.separator")+id+".png";
        String er = codeerpath+System.getProperty("file.separator")+id+".png";
        File file_tiao = new File(tiao);
        if (file_tiao.exists())
            file_tiao.delete();
        File file_er = new File(er);
        if (file_er.exists())
            file_er.delete();
    }
}
