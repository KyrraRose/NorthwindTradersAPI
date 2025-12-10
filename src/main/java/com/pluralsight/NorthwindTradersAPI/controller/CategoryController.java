package com.pluralsight.NorthwindTradersAPI.controller;


import com.pluralsight.NorthwindTradersAPI.dao.CategoryDaoJDBC;
import com.pluralsight.NorthwindTradersAPI.dao.ProductDaoJDBC;
import com.pluralsight.NorthwindTradersAPI.model.Category;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
public class CategoryController {


    private CategoryDaoJDBC dao;
    @Autowired
    public CategoryController(CategoryDaoJDBC dao)
    {
        this.dao = dao;

    }

    @RequestMapping(path="/categories", method= RequestMethod.GET)
    public List<Category> getCategories() {
        return dao.getCategories();
    }

    @RequestMapping(path="/categoriesById",method=RequestMethod.GET)
    public List<Category> getCategoriesById(@RequestParam(required=false) int id) {

        return dao.getById(id);
    }

}
