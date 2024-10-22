package com.ltdd.shoesshoppingapp.model;

import com.google.gson.annotations.SerializedName;

public class Product {
    @SerializedName("productID")
    public int id;
    public String name;
    @SerializedName("brandName")
    public String brand;
    public double price;
    @SerializedName("promotionalPrice")
    public double promotionalPrice;
    public String description;
    // phần trăm giảm giá
    @SerializedName("perRed")
    public int perRed;
    @SerializedName("image1")
    public String image1;
    @SerializedName("image2")
    public String image2;
    @SerializedName("image3")
    public String image3;
    @SerializedName("brandID")
    public int brandID;

    public Product(int id, String name, String brand, double price, double promotionalPrice, String description, int perRed, String image1, String image2, String image3, int brandID) {
        this.id = id;
        this.name = name;
        this.brand = brand;
        this.price = price;
        this.promotionalPrice = promotionalPrice;
        this.description = description;
        this.perRed = perRed;
        this.image1 = image1;
        this.image2 = image2;
        this.image3 = image3;
        this.brandID = brandID;
    }
    public Product(int id, String name, String image1, double price){
        this.id = id;
        this.name = name;
        this.price = price;
        this.image1 = image1;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public double getPromotionalPrice() {
        return promotionalPrice;
    }

    public void setPromotionalPrice(double promotionalPrice) {
        this.promotionalPrice = promotionalPrice;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getPerRed() {
        return perRed;
    }

    public void setPerRed(int perRed) {
        this.perRed = perRed;
    }

    public String getImage1() {
        return image1;
    }

    public void setImage1(String image1) {
        this.image1 = image1;
    }

    public String getImage2() {
        return image2;
    }

    public void setImage2(String image2) {
        this.image2 = image2;
    }

    public String getImage3() {
        return image3;
    }

    public void setImage3(String image3) {
        this.image3 = image3;
    }

    public int getBrandID() {
        return brandID;
    }

    public void setBrandID(int brandID) {
        this.brandID = brandID;
    }
}
