package com.pluralsight.NorthwindTradersAPI.controller;

import com.pluralsight.NorthwindTradersAPI.model.Category;
import com.pluralsight.NorthwindTradersAPI.model.Product;
import org.springframework.web.bind.annotation.*;

import java.util.*;
@RestController
public class ProductController {

        // respond to http://localhost:8080/customers
        @RequestMapping(path="/products", method=RequestMethod.GET)
        public List<Product> getProducts() {
            List<Product> products = new ArrayList<>();
            products.add(new Product(1,"Coffee Creamer","Beverages",5.99));
            products.add(new Product(2,"Ground Beef","Meat",10.99));
            products.add(new Product(3,"Strawberries","Produce",2.49));
            products.add(new Product(4,"Brioche Buns","Bread",4.99));
            products.add(new Product(5,"Macaroni","Dry Goods",3.90));

            return products;
        }

    @RequestMapping(path="/productsByName",method=RequestMethod.GET)
    public List<Product> getProductsByName(@RequestParam(required=false) String name) {
        List<Product> products = new ArrayList<>();
        List<Product> results = new ArrayList<>();
        products.add(new Product(1,"Coffee Creamer","Beverages",5.99));
        products.add(new Product(2,"Ground Beef","Meat",10.99));
        products.add(new Product(3,"Strawberries","Produce",2.49));
        products.add(new Product(4,"Brioche Buns","Bread",4.99));
        products.add(new Product(5,"Macaroni","Dry Goods",3.90));

        for (Product p : products){
            if (p.getName().contains(name)){
                results.add(p);
            }

        }
        return results;

    }

    @RequestMapping(path="/productsByID/{id}",method=RequestMethod.GET)
    public List<Product> getProductsByID(@PathVariable int id) {
        List<Product> products = new ArrayList<>();
        List<Product> results = new ArrayList<>();
        products.add(new Product(1,"Coffee Creamer","Beverages",5.99));
        products.add(new Product(2,"Ground Beef","Meat",10.99));
        products.add(new Product(3,"Strawberries","Produce",2.49));
        products.add(new Product(4,"Brioche Buns","Bread",4.99));
        products.add(new Product(5,"Macaroni","Dry Goods",3.90));

        for (Product p : products){
            if (p.getProductId() == id) {
                results.add(p);
            }

        }
        return results;

    }

    @RequestMapping(path="/productsByPrice",method=RequestMethod.GET)
    public List<Product> getProductsByPrice(@RequestParam(required=false) double low, double high) {
        List<Product> products = new ArrayList<>();
        List<Product> results = new ArrayList<>();
        products.add(new Product(1,"Coffee Creamer","Beverages",5.99));
        products.add(new Product(2,"Ground Beef","Meat",10.99));
        products.add(new Product(3,"Strawberries","Produce",2.49));
        products.add(new Product(4,"Brioche Buns","Bread",4.99));
        products.add(new Product(5,"Macaroni","Dry Goods",3.90));

        for (Product p : products){
            if (p.getPrice() > low && p.getPrice() < high){
                results.add(p);
            }

        }
        return results;

    }


}
