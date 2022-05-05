package com.example.webpos.db;

import com.example.webpos.mapper.ProductMapper;
import com.example.webpos.model.Cart;
import com.example.webpos.model.Product;
import com.example.webpos.model.ProductDAO;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Repository;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@Repository
public class JD implements PosDB {


    private List<Product> products = null;

    @Cacheable("products")
    @Override
    public List<Product> getProducts() {
        try {
            if (products == null)
                products = parseJD("Java");
        } catch (IOException e) {
            products = new ArrayList<>();
        }
        return products;
    }

    @Override
    public Product getProduct(String productId) {
        for (Product p : getProducts()) {
            if (p.getId().equals(productId)) {
                return p;
            }
        }
        return null;
    }

    @Autowired
    ProductMapper productMapper;

    @Autowired
    SqlSessionFactory sqlSessionFactory;

    public List<Product> parseJD(String keyword) throws IOException {
        List<Product> products=new ArrayList<>();
        List<ProductDAO> daos=new ArrayList<>();
        //从数据库获取数据
        try (SqlSession session = sqlSessionFactory.openSession()) {
            daos = session.selectList("com.example.webpos.mapper.ProductMapper.getProducts");
        }
        for (ProductDAO dao:daos
        ) {
            String asin=dao.getAsin();
            String main_cat=dao.getMain_cat();
            String title=dao.getTitle();
            String url=dao.getImageURL();
            String priceS=dao.getPrice();
            Double price=0.0;
            try{
                if(!priceS.equals("")){
                    price=Double.parseDouble(priceS.substring(1));
                }
                products.add(new Product(asin,title,price,url));
            }catch (Exception e){
                System.out.println(e);
                products.add(new Product(asin,title,0,url));
            }

        }
        /*for (ProductDAO dao:productDAOS
             ) {
            String img=productMapper.getImageURL(dao.asin,dao.title);
            products.add(new Product(dao.asin,dao.title,0,img));
        }*/
        return products;
    }

}
