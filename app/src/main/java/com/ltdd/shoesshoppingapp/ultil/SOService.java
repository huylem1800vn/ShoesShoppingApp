package com.ltdd.shoesshoppingapp.ultil;

import com.ltdd.shoesshoppingapp.model.Brand;
import com.ltdd.shoesshoppingapp.model.Cart;
import com.ltdd.shoesshoppingapp.model.Order;
import com.ltdd.shoesshoppingapp.model.Product;
import com.ltdd.shoesshoppingapp.model.ResponseAPI;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface

SOService {
    @GET("product/read.php")
    Call<List<Product>> getProducts();

    @GET("product/getProductByID.php")
    Call<Product> getProductByID(@Query("productID") int productID);

    @GET("brand/read.php")
    Call<List<Brand>> getBrands();

    @GET("brand/getBrandByID.php")
    Call<Brand> getBrandByID(@Query("brandID") int brandID);

    @GET("order/read.php")
    Call<List<Order>> getOrders();

    @POST("order/create.php")
    Call<ResponseAPI> addOrder(@Body Order order);

    @GET("order_detail/getOrderDetailByOrderID.php")
    Call<List<Cart>> getOrderDetailByOrderID(@Query("orderID") String orderID);

    @POST("order_detail/create.php")
    Call<ResponseAPI> addOrderDetail(@Body Cart orderDetail);
}
