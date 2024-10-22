package com.ltdd.shoesshoppingapp.ultil;

public class Server {
//    public static String localhost = "192.168.13.119";
    public static String connectString = "http://192.168.1.6:80/shoesshop/api/";
    public static SOService getSOService(){
        return RetrofitClient.getClient(connectString).create(SOService.class);
    }
}
