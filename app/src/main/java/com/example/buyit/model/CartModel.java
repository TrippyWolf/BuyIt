package com.example.buyit.model;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.io.Serializable;

@Entity(tableName = "cartmodel")
public class CartModel implements Serializable {

    @PrimaryKey
    @ColumnInfo(name = "id")
    private int productId;
    @ColumnInfo(name = "image_id")
    private int imageId;
    @ColumnInfo(name = "name")
    private String productName;
    @ColumnInfo(name = "price")
    private  int productPrice;

    @ColumnInfo(name = "quantity")
    private int quantity;

    public CartModel(int productId, int imageId, String productName, int productPrice, int quantity) {
        this.productId = productId;
        this.imageId = imageId;
        this.productName = productName;
        this.productPrice = productPrice;
        this.quantity = quantity;
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

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}
