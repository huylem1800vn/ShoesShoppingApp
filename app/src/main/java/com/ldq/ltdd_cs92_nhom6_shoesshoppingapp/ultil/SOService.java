package com.ldq.ltdd_cs92_nhom6_shoesshoppingapp.ultil;

import com.ldq.ltdd_cs92_nhom6_shoesshoppingapp.model.Shoes;

import retrofit2.Call;
import retrofit2.http.GET;

public interface SOService {
    @GET("/shoes")
    Call<Shoes> getShoes();
}
