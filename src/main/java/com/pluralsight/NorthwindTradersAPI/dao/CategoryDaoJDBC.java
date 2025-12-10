package com.pluralsight.NorthwindTradersAPI.dao;

import com.pluralsight.NorthwindTradersAPI.model.Category;
import com.pluralsight.NorthwindTradersAPI.model.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
@Component
public class CategoryDaoJDBC {
    private ArrayList<Category> categories;
    private DataSource dataSource;

    @Autowired
    public CategoryDaoJDBC(DataSource dataSource){
        this.dataSource = dataSource;
        this.categories= new ArrayList<>();
    }

    public ArrayList<Category> getCategories(){
        this.categories.clear();
        String sql = "SELECT * FROM categories;";
        try(Connection connection = dataSource.getConnection()){
            PreparedStatement statement = connection.prepareStatement(sql);
            ResultSet rows = statement.executeQuery();
            System.out.println("Categories:");
            while(rows.next()){
                this.categories.add(new Category(rows.getInt("CategoryId"),rows.getString("CategoryName")));
            }

        }
        catch (SQLException e){
            e.printStackTrace();
        }
        return this.categories;
    }
    public ArrayList<Category> getById(int id){
        categories.clear();
        String sql = "SELECT * FROM categories WHERE CategoryId = ?;";

        try(Connection connection = dataSource.getConnection();
            PreparedStatement statement = connection.prepareStatement(sql);){
            statement.setInt(1,id);
            try(ResultSet rows = statement.executeQuery();) {
                while(rows.next()) {
                    this.categories.add(new Category(rows.getInt("CategoryId"), rows.getString("CategoryName")));
                }
            }

        }
        catch (SQLException e){
            System.out.println(e.getMessage());
            System.out.println(e.getErrorCode());
        }
        return this.categories;
    }


}
