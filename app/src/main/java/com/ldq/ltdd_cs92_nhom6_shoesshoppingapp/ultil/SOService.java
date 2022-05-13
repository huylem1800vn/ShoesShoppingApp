package com.ldq.ltdd_cs92_nhom6_shoesshoppingapp.ultil;

import com.ldq.ltdd_cs92_nhom6_shoesshoppingapp.model.Brand;
import com.ldq.ltdd_cs92_nhom6_shoesshoppingapp.model.Product;
import com.ldq.ltdd_cs92_nhom6_shoesshoppingapp.model.Shoes;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
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
}
