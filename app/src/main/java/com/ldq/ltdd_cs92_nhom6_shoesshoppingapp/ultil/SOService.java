package com.ldq.ltdd_cs92_nhom6_shoesshoppingapp.ultil;

import com.ldq.ltdd_cs92_nhom6_shoesshoppingapp.model.Brand;
<<<<<<< HEAD
=======
import com.ldq.ltdd_cs92_nhom6_shoesshoppingapp.model.Cart;
import com.ldq.ltdd_cs92_nhom6_shoesshoppingapp.model.Order;
>>>>>>> devhuy
import com.ldq.ltdd_cs92_nhom6_shoesshoppingapp.model.Product;
import com.ldq.ltdd_cs92_nhom6_shoesshoppingapp.model.ResponseAPI;
import com.ldq.ltdd_cs92_nhom6_shoesshoppingapp.model.Shoes;

import java.util.List;

import retrofit2.Call;
<<<<<<< HEAD
import retrofit2.http.DELETE;
import retrofit2.http.GET;
=======
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.POST;
>>>>>>> devhuy
import retrofit2.http.Query;

public interface SOService {
    @GET("product/read.php")
    Call<List<Product>> getProducts();

    @GET("product/getProductByID.php")
    Call<Product> getProductByID(@Query("id") int productID);

    @GET("brand/read.php")
    Call<List<Brand>> getBrands();

    @GET("brand/getBrandByID.php")
    Call<Brand> getBrandByID(@Query("id") int brandID);
<<<<<<< HEAD
=======

    @GET("order/read.php")
    Call<List<Order>> getOrders();

    @POST("order/create.php")
    Call<ResponseAPI> addOrder(@Body Order order);

    @GET("order_detail/getOrderDetailByOrderID.php")
    Call<List<Cart>> getOrderDetailByOrderID(@Query("order_id") String orderID);

    @POST("order_detail/create.php")
    Call<ResponseAPI> addOrderDetail(@Body Cart orderDetail);
>>>>>>> devhuy
}
