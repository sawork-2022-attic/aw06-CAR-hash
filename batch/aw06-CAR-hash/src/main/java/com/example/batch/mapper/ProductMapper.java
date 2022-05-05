package com.example.batch.mapper;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface ProductMapper {

    public void insertProduct(String asin,String main_cat,String title,String price,String category,String imageURL);

    public void insertProductCategory(String asin,String title,String category);

    public void insertProductImageURL(String asin,String title,String imageURL);
}
