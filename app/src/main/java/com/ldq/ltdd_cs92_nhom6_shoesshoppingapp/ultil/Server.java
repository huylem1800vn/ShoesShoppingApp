package com.ldq.ltdd_cs92_nhom6_shoesshoppingapp.ultil;

import retrofit2.Retrofit;

public class Server {
//    public static String localhost = "192.168.13.119";
    public static String connectString = "http://192.168.56.1:81/shoesshop/api/";
    public static SOService getSOService(){
        return RetrofitClient.getClient(connectString).create(SOService.class);
    }
}
