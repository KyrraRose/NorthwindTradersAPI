package com.pluralsight.NorthwindTradersAPI.model;

public class Category {
    private int categoryId;
    private String CategoryName;

    public Category(int categoryId, String categoryName) {
        this.categoryId = categoryId;
        CategoryName = categoryName;
    }

    public int getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(int categoryId) {
        this.categoryId = categoryId;
    }

    public String getCategoryName() {
        return CategoryName;
    }

    public void setCategoryName(String categoryName) {
        CategoryName = categoryName;
    }
}
