package com.pluralsight.NorthwindTradersAPI.dao;

import com.pluralsight.NorthwindTradersAPI.model.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;
import java.sql.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@Component
public class ProductDaoJDBC implements ProductDao{

    private List<Product> products;
    private DataSource dataSource;

    @Autowired
    public ProductDaoJDBC(DataSource dataSource){
        this.products = new ArrayList<>();
        this.dataSource = dataSource;
    }

    @Override
    public Product insert(Product product) {
        String sql = "INSERT INTO products (`ProductID`,`ProductName`,`UnitPrice`)VALUES " +
                "(?,?,?);";
        try(Connection connection = dataSource.getConnection();
            PreparedStatement statement = connection.prepareStatement(sql,Statement.RETURN_GENERATED_KEYS)) {
            statement.setInt(1,product.getProductId());
            statement.setString(2,product.getName());
            statement.setDouble(4,product.getPrice());

            int rowsAffected = statement.executeUpdate();

            ResultSet rs = statement.getGeneratedKeys();

            if (rs.next()) product.setProductId(rs.getInt(1));

            if (rowsAffected > 0) {
                System.out.println("Product added successfully!");
            }
        }
        catch (SQLException e){
            e.printStackTrace();
        }
        return product;
    }

    @Override
    public List<Product> getAll() {
        this.products.clear();
        String sql = "SELECT ProductID, ProductName, CategoryName, UnitPrice FROM products p JOIN categories c ON p.CategoryId = c.CategoryId ;";
        try(Connection connection = dataSource.getConnection()){
            PreparedStatement statement = connection.prepareStatement(sql);
            ResultSet rows = statement.executeQuery();
            while(rows.next()){
                this.products.add(new Product (rows.getInt(1),rows.getString(2),rows.getString(3),rows.getDouble(4)));
            }
        }
        catch (SQLException e){
            e.printStackTrace();
        }
        return this.products;
    }

    @Override
    public List<Product> getByProductName(String name) {
        this.products.clear();
        String sql = "SELECT ProductID, ProductName, CategoryName, UnitPrice FROM products p JOIN categories c ON p.CategoryId = c.CategoryId WHERE ProductName LIKE ?;";
        try(Connection connection = dataSource.getConnection();
            PreparedStatement statement = connection.prepareStatement(sql);){
            statement.setString(1,"%"+name+"%");

            try(ResultSet rows = statement.executeQuery();) {
                while (rows.next()) {
                    this.products.add(new Product(rows.getInt(1), rows.getString(2), rows.getString(3), rows.getDouble(4)));
                }
            }
        }
        catch (SQLException e){
            e.printStackTrace();
        }
        return this.products;
    }

    @Override
    public List<Product> getByCategory(String category) {
        this.products.clear();
        String sql = "SELECT ProductID, ProductName, CategoryName, UnitPrice FROM products p JOIN categories c ON p.CategoryId = c.CategoryId WHERE CategoryName LIKE ?;";
        try(Connection connection = dataSource.getConnection();
            PreparedStatement statement = connection.prepareStatement(sql);){
            statement.setString(1,"%"+category+"%");

            try(ResultSet rows = statement.executeQuery();) {
                while (rows.next()) {
                    this.products.add(new Product(rows.getInt(1), rows.getString(2), rows.getString(3), rows.getDouble(4)));
                }
            }
        }
        catch (SQLException e){
            e.printStackTrace();
        }
        return this.products;
    }
    public int getCatId(String cat){
        String sql = "SELECT * FROM categories;";
        HashMap<String,Integer> categories = new HashMap<>();
        try(Connection connection = dataSource.getConnection()){
            PreparedStatement statement = connection.prepareStatement(sql);
            ResultSet rows = statement.executeQuery();
            System.out.println("Categories:");
            while(rows.next()){
                categories.put(rows.getString("CategoryName"),rows.getInt("CategoryId"));
            }
            return categories.get(cat);
        }
        catch (SQLException e){
            e.printStackTrace();
        }
        return 0;

    }
    @Override
    public List<Product> getByPrice(double low, double high) {
        this.products.clear();
        String sql = "SELECT ProductID, ProductName, CategoryName, UnitPrice FROM products p JOIN categories c ON p.CategoryId = c.CategoryId WHERE UnitPrice BETWEEN ? AND ?;";
        try(Connection connection = dataSource.getConnection();
            PreparedStatement statement = connection.prepareStatement(sql);){
            statement.setDouble(1,low);
            statement.setDouble(2,high);
            try(ResultSet rows = statement.executeQuery();) {
                while (rows.next()) {
                    this.products.add(new Product(rows.getInt(1), rows.getString(2), rows.getString(3), rows.getDouble(4)));
                }
            }
        }
        catch (SQLException e){
            e.printStackTrace();
        }
        return this.products;
    }

    @Override
    public Product getByProductID(int id) {
        String sql = "SELECT ProductID, ProductName, CategoryName, UnitPrice FROM products p JOIN categories c ON p.CategoryId = c.CategoryId WHERE ProductId = ?;";
        try(Connection connection = dataSource.getConnection();
            PreparedStatement statement = connection.prepareStatement(sql);){
            statement.setInt(1,id);

            try(ResultSet rows = statement.executeQuery();) {
                if (rows.next()) {
                    return new Product(rows.getInt(1), rows.getString(2), rows.getString(3), rows.getDouble(4));
                }else{
                    System.out.println("No results found.");
                }
            }
        }
        catch (SQLException e){
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public void delete(int id) {
        String sql = "DELETE FROM products WHERE ProductId = ?;";
        try(Connection connection = dataSource.getConnection();
            PreparedStatement statement = connection.prepareStatement(sql);){
            statement.setInt(1,id);
            int product = statement.executeUpdate();

            if (product > 0) {
                System.out.println(product + " product(s) deleted successfully.");
            } else {
                System.out.println("Product not found.");
            }
        }
        catch (SQLException e){
            e.printStackTrace();
        }
    }
}

