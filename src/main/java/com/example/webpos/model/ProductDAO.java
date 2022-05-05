package com.example.webpos.model;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class ProductDAO {
    public String main_cat;
    public String title;
    public String asin;
    public String price;
    public String imageURL;
}
