package com.example.webpos.mapper;

import com.example.webpos.model.Product;
import com.example.webpos.model.ProductDAO;
import org.apache.ibatis.annotations.Mapper;

import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
@Mapper
public interface ProductMapper {
    List<ProductDAO> getProducts();
    String getImageURL(String asin,String title);
}
