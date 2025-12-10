package com.pluralsight.NorthwindTradersAPI.controller;


import com.pluralsight.NorthwindTradersAPI.model.Category;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
public class CategoryController {
    @RequestMapping(path="/categories", method= RequestMethod.GET)
    public List<Category> getCategories() {
        List<Category> categories = new ArrayList<>();
        categories.add(new Category(1,"Beverages"));
        categories.add(new Category(2,"Meat"));
        categories.add(new Category(3,"Produce"));
        categories.add(new Category(4,"Bread"));
        categories.add(new Category(5,"Dry Goods"));
        categories.add(new Category(6,"Pharmacy"));
        categories.add(new Category(7,"Frozen"));
        categories.add(new Category(8,"Bakery"));

        return categories;
    }

    @RequestMapping(path="/categoriesByName",method=RequestMethod.GET)
    public List<Category> getCategories(@RequestParam(required=false) String name) {
        List<Category> categories = new ArrayList<>();
        List<Category> results = new ArrayList<>();
        categories.add(new Category(1,"Beverages"));
        categories.add(new Category(2,"Meat"));
        categories.add(new Category(3,"Produce"));
        categories.add(new Category(4,"Bread"));
        categories.add(new Category(5,"Dry Goods"));
        categories.add(new Category(6,"Pharmacy"));
        categories.add(new Category(7,"Frozen"));
        categories.add(new Category(8,"Bakery"));

        for (Category c : categories){
            if (c.getCategoryName().contains(name)){
                results.add(c);
            }
        }
        return results;
    }

}
