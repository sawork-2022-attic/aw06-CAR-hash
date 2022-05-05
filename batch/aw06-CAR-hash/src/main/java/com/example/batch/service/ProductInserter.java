package com.example.batch.service;

import com.example.batch.mapper.ProductMapper;
import com.example.batch.model.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ProductInserter {
    @Autowired
    ProductMapper productMapper;
    public Boolean insertProduct(Product product){
        String asin=product.getAsin();
        String main_cat=product.getMain_cat();
        String title=product.getTitle();
        String price=product.getPrice();
        List<String> categories=product.getCategory();
        List<String> imageURLS=product.getImageURLHighRes();
        String category="";
        String imageURL="";
        if(!categories.isEmpty()){
            category=categories.get(0);
        }
        if(!imageURLS.isEmpty()) {
            imageURL = imageURLS.get(0);
        }
        productMapper.insertProduct(asin,main_cat,title,price,category,imageURL);

        /*for (String cat:
             categories) {
            productMapper.insertProductCategory(asin,title,cat);

        }
        for (String imageURL:imageURLS){
            productMapper.insertProductImageURL(asin,title,imageURL);
        }*/

        return true;
    }
}
