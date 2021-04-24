package com.wpg.graduationdesign.moudles.shones.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.wpg.graduationdesign.config.ResourceConfigBean;
import com.wpg.graduationdesign.moudles.shones.dao.BrandDao;
import com.wpg.graduationdesign.moudles.shones.dao.CategoryDao;
import com.wpg.graduationdesign.moudles.shones.dao.ProductDao;
import com.wpg.graduationdesign.moudles.shones.dao.ProductImageDao;
import com.wpg.graduationdesign.moudles.shones.entity.Brand;
import com.wpg.graduationdesign.moudles.shones.entity.Category;
import com.wpg.graduationdesign.moudles.shones.entity.Product;
import com.wpg.graduationdesign.moudles.shones.entity.ProductImage;
import com.wpg.graduationdesign.moudles.shones.service.ProductService;
import com.wpg.graduationdesign.vo.Result;
import com.wpg.graduationdesign.vo.SearchVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class ProductServiceImpl implements ProductService {
    @Autowired
    private ProductDao productDao;
    @Autowired
    private ResourceConfigBean resourceConfigBean;
    @Autowired
    private CategoryDao categoryDao;
    @Autowired
    private BrandDao brandDao;
    @Autowired
    private ProductImageDao productImageDao;

    @Override
    @Transactional
    public PageInfo<Product> getProdcutsBySearchVo(SearchVo searchVo) {
        searchVo.initSearchVo();
        PageHelper.startPage(searchVo.getCurrentPage(), searchVo.getPageSize());
        List<Product> prodcutslist = productDao.getProdcutsBySearchVo(searchVo);
        PageInfo<Product> productPageInfo = new PageInfo<>(prodcutslist);
        return productPageInfo;
    }

    //著图片路径
    @Override
    public Result<String> getProductMainImg(MultipartFile file) {
        if (file.isEmpty()) {
            return new Result<String>(
                    Result.Resultstatus.FAILD.status, "Please select img.");
        }
        String relativePath = "";
        String destFilePath = "";
        try {
            String osName = System.getProperty("os.name");
            if (osName.toLowerCase().startsWith("win")) {
                destFilePath = resourceConfigBean.getLocationPathForWindows() + "productimg/" +
                        file.getOriginalFilename();
            } else {
                destFilePath = resourceConfigBean.getLocationPathForLinux() + "productimg/"
                        + file.getOriginalFilename();
            }
            relativePath = resourceConfigBean.getRelativePath() + "productimg/" +
                    file.getOriginalFilename();
            File destFile = new File(destFilePath);
            //存入upload文件夹
            file.transferTo(destFile);

        } catch (IOException e) {
            e.printStackTrace();
            return new Result<String>(
                    Result.Resultstatus.FAILD.status, "Upload failed.");
        }
        return new Result<String>(
                Result.Resultstatus.SUCCESS.status, "Upload success.", relativePath);
    }

    //附图片路劲
    @Override
    @Transactional
    public Result<String> getProduct_fu_Img(MultipartFile file) {
        if (file.isEmpty()) {
            return new Result<String>(
                    Result.Resultstatus.FAILD.status, "Please select img.");
        }
        String relativePath = "";
        String destFilePath = "";
        try {
            String osName = System.getProperty("os.name");
            if (osName.toLowerCase().startsWith("win")) {
                destFilePath = resourceConfigBean.getLocationPathForWindows() + "product_fu_img/" +
                        file.getOriginalFilename();
            } else {
                destFilePath = resourceConfigBean.getLocationPathForLinux() + "product_fu_img/"
                        + file.getOriginalFilename();
            }
            relativePath = resourceConfigBean.getRelativePath() + "product_fu_img/" +
                    file.getOriginalFilename();
            File destFile = new File(destFilePath);
            //存入upload文件夹
            file.transferTo(destFile);

        } catch (IOException e) {
            e.printStackTrace();
            return new Result<String>(
                    Result.Resultstatus.FAILD.status, "Upload failed.");
        }
        return new Result<String>(
                Result.Resultstatus.SUCCESS.status, "Upload success.", relativePath);
    }

    @Override
    @Transactional
    public Result<Product> insertProductByProduct(Product product) {
        //分类
        String fenlei = product.getCategory().getName();
        Category select_category = categoryDao.getCategoryByName(fenlei);
        if (select_category != null) {
            product.setProductType(select_category.getCategoryId());
        } else {
            //插入的id自动回填到Category中，但是传入必须是个对象啊   记住
            Category category = product.getCategory();
            categoryDao.insertCategoryByName(category);
//            Category category = categoryDao.getCategoryByName(fenlei);
            product.setProductType(category.getCategoryId());
        }

        //品牌
        String pinpai = product.getBrand().getBrandName();
        Brand select_brand = brandDao.getBrandBybrandName(pinpai);
        if (select_brand != null) {
            product.setbId(select_brand.getBrandId());
        } else {
            //自动回填
            Brand brand = product.getBrand();
            brandDao.insertBrandBybrandName(brand);
//            Brand brand = brandDao.getBrandBybrandName(pinpai);
            product.setbId(brand.getBrandId());
        }

        //时间
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//设置日期格式
        product.setCreatetime(df.format(new Date()));// new Date()为获取当前系统时间
        productDao.insertProductByProduct(product);
        return new Result<Product>(
                Result.Resultstatus.SUCCESS.status, "Insert success.", product);
    }


    //查询商品所有信息
    @Override
    @Transactional
    public Result<Product> selectProdcutByProductId(int productId) {
        Product product = productDao.selectProdcutByProductId(productId);
        return new Result<Product>(
                Result.Resultstatus.SUCCESS.status, "", product);
    }

    //修改商品信息

    @Override
    @Transactional
    public Result<Product> updateProdcutByProduct(Product product) {

        //分类
        String fenlei = product.getCategory().getName();
        Category select_category = categoryDao.getCategoryByName(fenlei);
        if (select_category != null) {
            product.setProductType(select_category.getCategoryId());
        } else {
            //插入的id自动回填到Category中，但是传入必须是个对象啊   记住
            Category category = product.getCategory();
            categoryDao.insertCategoryByName(category);
//            Category category = categoryDao.getCategoryByName(fenlei);
            product.setProductType(category.getCategoryId());
        }

        //品牌
        String pinpai = product.getBrand().getBrandName();
        Brand select_brand = brandDao.getBrandBybrandName(pinpai);
        if (select_brand != null) {
            product.setbId(select_brand.getBrandId());
        } else {
            //自动回填
            Brand brand = product.getBrand();
            brandDao.insertBrandBybrandName(brand);
//            Brand brand = brandDao.getBrandBybrandName(pinpai);
            product.setbId(brand.getBrandId());
        }


//        //时间
//        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//设置日期格式
//        product.setCreatetime(df.format(new Date()));// new Date()为获取当前系统时间

        //修改product_image表的pname
        productImageDao.updateProduct_fu_ImageByproductId(product.getProductName(), product.getProductId());

        productDao.updateProdcutByProduct(product);
        return new Result<Product>(
                Result.Resultstatus.SUCCESS.status, "update success。", product);
    }

    //停售
    @Override
    @Transactional
    public Result<Product> updateProdcutStatus0ByProductId(Integer productId) {
        productDao.updateProdcutStatus0ByProductId(productId);
        return new Result<Product>(
                Result.Resultstatus.SUCCESS.status, "已停售");
    }

    //在售
    @Override
    @Transactional
    public Result<Product> updateProdcutStatus1ByProductId(Integer productId) {
        productDao.updateProdcutStatus1ByProductId(productId);
        return new Result<Product>(
                Result.Resultstatus.SUCCESS.status, "已在售");
    }

    //插入商品其他副图片
    @Override
    @Transactional
    public Result<String> addProductOtherImg(int productId, String fu_img1, String fu_img2, String fu_img3) {

        List<ProductImage> select_productImagelist = productImageDao.getProductImageByProductId(productId);
        String productname = productDao.selectProdcutNameByProductId(productId);
        if (select_productImagelist == null) {
            if (fu_img1 != "") {
                productImageDao.addProductOtherImg(productId, productname, fu_img1);
            }
            if (fu_img2 != "") {
                productImageDao.addProductOtherImg(productId, productname, fu_img2);
            }
            if (fu_img3 != "") {
                productImageDao.addProductOtherImg(productId, productname, fu_img3);
            }

        } else {
            productImageDao.deleteProductImageByProductId(productId);
            if (fu_img1 != "") {
                productImageDao.addProductOtherImg(productId, productname, fu_img1);
            }
            if (fu_img2 != "") {
                productImageDao.addProductOtherImg(productId, productname, fu_img2);
            }
            if (fu_img3 != "") {
                productImageDao.addProductOtherImg(productId, productname, fu_img3);
            }
        }

        return new Result<>(Result.Resultstatus.SUCCESS.status, "insert success.");
    }


    /**
     * 通过种类获取商品信息
     *
     * @param categoryId id
     * @return list
     */
    @Override
    public Result<List<Product>> getProductList(Integer categoryId, String keywords) {
        if (StringUtils.isEmpty(keywords)) {
            return new Result<>(Result.Resultstatus.SUCCESS.status
                    , "获取商品信息成功"
                    , productDao.getProductList(categoryId));
        } else {
            return new Result<>(Result.Resultstatus.SUCCESS.status
                    , "获取商品信息成功"
                    , productDao.getProductLists(categoryId, keywords));
        }
    }

    /**
     * 通过商品id获取商品信息
     *
     * @param productId id
     * @return list
     */
    @Override
    public Result<Product> getProductInfo(Integer productId) {
        return new Result<>(Result.Resultstatus.SUCCESS.status
                , "获取商品信息成功"
                , productDao.getProductInfo(productId));
    }

    /**
     * 通过商品id获取商品其他图片
     *
     * @param productId id
     * @return list
     */
    @Override
    public Result<List<ProductImage>> getProductImages(Integer productId) {
        return new Result<>(Result.Resultstatus.SUCCESS.status
                , "获取商品信息成功"
                , productDao.getProductImages(productId));
    }

    @Override
    public Boolean stockReduce(Integer pId, Integer num) {
        Product productInfo = productDao.getProductInfo(pId);
        Integer stock = productInfo.getStock() - num;
        return productDao.stockReduce(pId, stock);
    }

    @Override
    public Boolean stockAdd(Integer pId, Integer num) {
        Product productInfo = productDao.getProductInfo(pId);
        Integer stock = productInfo.getStock() + num;
        return productDao.stockAdd(pId, stock);
    }

    @Override
    public Result<List<Product>> getProductsFor6() {
        return new Result<>(Result.Resultstatus.SUCCESS.status
                , "获取商品推荐信息成功"
                , productDao.getProductsFor6());
    }
}
