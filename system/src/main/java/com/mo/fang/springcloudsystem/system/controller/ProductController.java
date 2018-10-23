package com.mo.fang.springcloudsystem.system.controller;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.google.gson.Gson;
import com.mo.fang.springcloudsystem.system.adapter.ViewAdapter;
import com.mo.fang.springcloudsystem.system.entity.Category;
import com.mo.fang.springcloudsystem.system.entity.Product;
import com.mo.fang.springcloudsystem.system.serviceI.ProductService;
import com.mo.fang.springcloudsystem.system.serviceI.RedisService;
import entity.CodeMsg;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import utils.LayUtil;
import utils.Result;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.List;


@RestController
public class ProductController {
    @Value("${oa.redis.system-data.prefix}")
    private String PREFIX;
    @Autowired
    private ViewAdapter adapter;
    @Autowired
    private ProductService  productService;
//    @Autowired
//    private ProductPicService productPicService;
    @Autowired
    private RedisService redisService;

    private Gson gson = new Gson();

    @RequiresPermissions("productmsg:search")
    @GetMapping("toProductList.html")
    public ModelAndView toUserList(ModelAndView modelAndView){
        String viewAdapter = adapter.viewAdapter("product/productlist");
        modelAndView.setViewName(viewAdapter);
        return modelAndView;
    }
    @RequiresPermissions("productmsg:add")
    @GetMapping("{id}/toUploadPic.html")
    public ModelAndView toUploadPic(@PathVariable("id")Integer id, ModelAndView modelAndView){
        String viewAdapter = adapter.viewAdapter("product/pic");
        modelAndView.setViewName(viewAdapter);
        modelAndView.addObject("productId",id);
        return modelAndView;
    }
//    @RequiresPermissions("productmsg:detail")
//    @GetMapping("{id}/toProductDetail.html")
//    public ModelAndView toProductDetail(@PathVariable("id")Integer id, ModelAndView modelAndView){
//        String viewAdapter = adapter.viewAdapter("product/detail");
//        List<Category > categorys = (List<Category >)redisService.get(PREFIX + "CATEGORYS");
//        modelAndView.addObject("categorys",categorys);
//        Product product = new Product();
//        product.setProductId(id);
//        List<Product> products = productService.getAllProducts(product);
//        if (products!=null&&products.size()>0){
//            product = products.get(0);
//        }else
//            throw new RuntimeException(CodeMsg.SERVER_EXCEPTION.toString());
//        modelAndView.setViewName(viewAdapter);
//        modelAndView.addObject("product",product);
//        return modelAndView;
//    }
    @RequiresPermissions("productmsg:add")
    @GetMapping("toAddProduct.html")
    public ModelAndView toAddUser(ModelAndView modelAndView){
        String viewAdapter = adapter.viewAdapter("product/add");
//        List<Category > categorys = (List<Category >)redisService.get(PREFIX + "CATEGORYS");
//        modelAndView.addObject("categorys",categorys);
        modelAndView.setViewName(viewAdapter);
        return modelAndView;
    }
    @RequiresPermissions("productmsg:add")
    @PostMapping("doAddProduct.html")
    public String doAddProduct( Product product) throws Exception {
        Result result = Result.success();
        boolean flag = productService.saveOrUpdate(product);
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    productService.mkcode(product);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
        thread.start();
        if(!flag)
            result = Result.error(CodeMsg.SAVE_OR_UPDATE_FAIL);
        return  gson.toJson(result);
    }
    @RequiresPermissions("productmsg:del")
    @PostMapping("doDelProduct.html")
    public String doDelProduct( Integer id) throws Exception {
        Result result = Result.success();
        boolean flag = productService.deleteByPrimaryKey(id);
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    productService.deleteCode(id);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
        thread.start();
        if(!flag)
            result = Result.error(CodeMsg.DEL_FAIL);
        return  gson.toJson(result);
    }
//    @RequiresPermissions("productmsg:add")
//    @PostMapping("doUploadPic.html")
//    public String doUploadPic(MultipartFile file,Integer id){
//        String filename = file.getOriginalFilename();
//        String name = file.getName();
//        Result res = Result.success();
//        Product product = new Product();
//        product.setProductId(id);
//        List<Product> products = productService.getAllProducts(product);
//        if (products!=null&&products.size()>0){
//            product = products.get(0);
//        }else {
//            res = Result.error(CodeMsg.DB_EXCEPTION);
//        }
//        if(product!=null){
//            Integer categoryid = product.getCategoryid();
//            String PRODUCTIMGPATH = (String)redisService.get(PREFIX + "PARAS-PRODUCTIMGPATH");
//            PRODUCTIMGPATH += File.separator+categoryid+File.separator+id+File.separator;
//            String resJson = fileFeignClient.upload(file, PRODUCTIMGPATH);
//            res = gson.fromJson(resJson,Result.class);
//            int retCode = res.getRetCode();
//            ProductPic productPic;
//            if (retCode==200){
//                productPic = new ProductPic();
//                productPic.setInsertime(new Date());
//                productPic.setInsertuser(SysUtil.getLoginUser().getUsername());
//                productPic.setPic((String) res.getData());
//                productPic.setProductid(id);
//                boolean b = productPicService.saveProductPic(productPic);
//                if(!b)
//                    res = Result.error(CodeMsg.PIC_FILE_FAIL);
//            }
//        }else {
//            res = Result.error(CodeMsg.DB_EXCEPTION);
//        }
//
//        return  gson.toJson(res);
//    }
    @RequiresPermissions("productmsg:search")
    @PostMapping("productList.html")
    public  String userList(Integer page,Integer limit,Product product){
        PageHelper.startPage(page, limit);
        Page<Object> pagehelperPage = PageHelper.getLocalPage();
        List<Product> productList = productService.getAllProducts(product);
        String layjson = LayUtil.getLayJsonWeChat(productList,pagehelperPage.getTotal(),page);
        return layjson;
    }
//    @RequestMapping("/{categoryid}/{id}/{pic}/showImage.html")
//    public void  showImage(HttpServletResponse response,@PathVariable("categoryid")Integer categoryid, @PathVariable("id")Integer id,@PathVariable("pic")String fileName)  {
//        ServletOutputStream out = null;
//        FileInputStream ips = null;
//        String PRODUCTIMGPATH = (String)redisService.get(PREFIX + "PARAS-PRODUCTIMGPATH");
//        PRODUCTIMGPATH += File.separator+categoryid+File.separator+id+File.separator +File.separator+ fileName;
//        try{
//            ips = new FileInputStream(new File(PRODUCTIMGPATH));
//            response.setContentType("multipart/form-data");
//            out = response.getOutputStream();
//            //读取文件流
//            int len = 0;
//            byte[] buffer = new byte[1024 * 10];
//            while ((len = ips.read(buffer)) != -1){
//                out.write(buffer,0,len);
//            }
//            out.flush();
//        }catch (Exception e){
//        }finally {
//            try {
//                out.close();
//            } catch (IOException e) {
//                e.printStackTrace();
//            }
//        }
//    }



    @RequiresPermissions("productmsg:search")
    @GetMapping("/{id}/{type}/toProductCode.html")
    public ModelAndView toProductCode(ModelAndView modelAndView,@PathVariable("id")Integer id,@PathVariable("type")String type){
        String viewAdapter = adapter.viewAdapter("product/qrcode");
        modelAndView.addObject("id",id);
        Product productById = productService.getProductById(id);
        modelAndView.addObject("product",productById);
        modelAndView.addObject("type",type);
        modelAndView.setViewName(viewAdapter);
        return modelAndView;
    }
    @RequestMapping("/{id}/{type}/showImage.html")
    public void  showImage(HttpServletResponse response, @PathVariable("id")Integer id,@PathVariable("type")String type)  {
        String codetiaopath = (String) redisService.get(PREFIX+"PARAS-CODETIAOPATH");
        String codeerpath = (String) redisService.get(PREFIX+"PARAS-CODEERPATH");
        String tiao = codetiaopath+System.getProperty("file.separator")+id+".png";
        String er = codeerpath+System.getProperty("file.separator")+id+".png";
        ServletOutputStream out = null;
        FileInputStream ips = null;
        try{
            ips = new FileInputStream(new File("er".equals(type)?er:tiao));
            response.setContentType("multipart/form-data");
            out = response.getOutputStream();
            //读取文件流
            int len = 0;
            byte[] buffer = new byte[1024 * 10];
            while ((len = ips.read(buffer)) != -1){
                out.write(buffer,0,len);
            }
            out.flush();
        }catch (Exception e){

        }finally {
            try {
                out.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }


    }

}
