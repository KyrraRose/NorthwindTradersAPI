package com.pluralsight.NorthwindTradersAPI.controller;

import com.pluralsight.NorthwindTradersAPI.dao.ProductDaoJDBC;
import com.pluralsight.NorthwindTradersAPI.model.Category;
import com.pluralsight.NorthwindTradersAPI.model.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.*;
@RestController
public class ProductController {

    private ProductDaoJDBC dao;
    @Autowired
    public ProductController(ProductDaoJDBC dao)
    {
        this.dao = dao;
    }


// respond to http://localhost:8080/customers
    @RequestMapping(path="/products", method=RequestMethod.GET)
    public List<Product> getProducts() {

        return dao.getAll();
    }

    @RequestMapping(path="/productsByName",method=RequestMethod.GET)
    public List<Product> getProductsByName(@RequestParam(required=false) String name) {

        return dao.getByProductName(name);

    }

    @RequestMapping(path="/productsByID",method=RequestMethod.GET)
    public Product getProductsByID(@RequestParam (required = true)int id) {

        return dao.getByProductID(id);

    }

    @RequestMapping(path="/productsByPrice",method=RequestMethod.GET)
    public List<Product> getProductsByPrice(@RequestParam(required=false) double low, double high) {

        return dao.getByPrice(low,high);

    }

    @RequestMapping(path="/productsByCategory",method=RequestMethod.GET)
    public List<Product> getProductsByCategory(@RequestParam(required = false) String category){
        return dao.getByCategory(category);
    }


}
