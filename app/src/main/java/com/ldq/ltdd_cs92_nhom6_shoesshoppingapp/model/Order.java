package com.ldq.ltdd_cs92_nhom6_shoesshoppingapp.model;

import com.google.gson.annotations.SerializedName;

import java.util.Date;

public class Order {
    public String id;
    @SerializedName("customer_name")
    public String customerName;
    @SerializedName("customer_address")
    public String customerAddress;
    @SerializedName("customer_phone")
    public String customerPhone;
    @SerializedName("total_amount")
    public double totalAmount;
    @SerializedName("order_date")
    public Date orderDate;

    public Order(String id, String customerName, String customerAddress, String customerPhone, double totalAmount, Date orderDate) {
        this.id = id;
        this.customerName = customerName;
        this.customerAddress = customerAddress;
        this.customerPhone = customerPhone;
        this.totalAmount = totalAmount;
        this.orderDate = orderDate;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public String getCustomerAddress() {
        return customerAddress;
    }

    public void setCustomerAddress(String customerAddress) {
        this.customerAddress = customerAddress;
    }

    public String getCustomerPhone() {
        return customerPhone;
    }

    public void setCustomerPhone(String customerPhone) {
        this.customerPhone = customerPhone;
    }

    public double getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(double totalAmount) {
        this.totalAmount = totalAmount;
    }

    public Date getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(Date orderDate) {
        this.orderDate = orderDate;
    }
}
