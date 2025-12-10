package com.pluralsight.NorthwindTradersAPI.dao;

import com.pluralsight.NorthwindTradersAPI.model.Product;

import java.util.List;

public interface ProductDao {
    public void add(Product product);
    public Product makeProduct();

    public List<Product> getAll();

    public List<Product> getByProductName(String name);
    public List<Product> getByCategory(String category);
    public List<Product> getByPrice(double low, double high);
    public Product getByProductID(int id);
    public void delete(int id);

}
