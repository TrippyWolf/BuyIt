package com.example.buyit.model;

import androidx.room.ColumnInfo;
import androidx.room.Entity;

import java.io.Serializable;

public class ProductModel implements Serializable {

    private int productId;
    private int imageId;
    private String productName;
    private int productPrice;

    public ProductModel(int productId, int imageId, String productName, int productPrice) {
        this.productId = productId;
        this.imageId = imageId;
        this.productName = productName;
        this.productPrice = productPrice;
    }

    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }

    public int getImageId() {
        return imageId;
    }

    public void setImageId(int imageId) {
        this.imageId = imageId;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public int getProductPrice() {
        return productPrice;
    }

    public void setProductPrice(int productPrice) {
        this.productPrice = productPrice;
    }
}
