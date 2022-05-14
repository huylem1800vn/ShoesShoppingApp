package com.ldq.ltdd_cs92_nhom6_shoesshoppingapp.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Cart {
   public int id;
   @SerializedName("product_id")
   public int productID;
   @SerializedName("product_name")
   public String name;
   @SerializedName("image")
   public String image;
   @SerializedName("size")
   public int size;
   @SerializedName("quantity")
   public int quantity;
   @SerializedName("product_price")
   public double price;
   @SerializedName("total_amount")
   public double totalAmount;
   @SerializedName("order_id")
   public String orderID;

   public Cart(int productID, String name, String image, int size, int quantity, double price, double totalAmount) {
      this.productID = productID;
      this.name = name;
      this.image = image;
      this.size = size;
      this.quantity = quantity;
      this.price = price;
      this.totalAmount = totalAmount;
   }

   public Cart(int id, int productID, String name, String image, int size, int quantity, double price, double totalAmount, String orderID) {
      this.id = id;
      this.productID = productID;
      this.name = name;
      this.image = image;
      this.size = size;
      this.quantity = quantity;
      this.price = price;
      this.totalAmount = totalAmount;
      this.orderID = orderID;
   }

   public int getId() {
      return id;
   }

   public void setId(int id) {
      this.id = id;
   }

   public Cart(int productID, int size, int quantity, double totalAmount, String orderID) {
      this.productID = productID;
      this.size = size;
      this.quantity = quantity;
      this.totalAmount = totalAmount;
      this.orderID = orderID;
   }

   public String getOrderID() {
      return orderID;
   }

   public void setOrderID(String orderID) {
      this.orderID = orderID;
   }

   public int getProductID() {
      return productID;
   }

   public void setProductID(int productID) {
      this.productID = productID;
   }

   public String getName() {
      return name;
   }

   public void setName(String name) {
      this.name = name;
   }

   public String getImage() {
      return image;
   }

   public void setImage(String image) {
      this.image = image;
   }

   public int getSize() {
      return size;
   }

   public void setSize(int size) {
      this.size = size;
   }

   public int getQuantity() {
      return quantity;
   }

   public void setQuantity(int quantity) {
      this.quantity = quantity;
   }

   public double getPrice() {
      return price;
   }

   public void setPrice(double price) {
      this.price = price;
   }

   public double getTotalAmount() {
      return totalAmount;
   }

   public void setTotalAmount(double totalAmount) {
      this.totalAmount = totalAmount;
   }
}
