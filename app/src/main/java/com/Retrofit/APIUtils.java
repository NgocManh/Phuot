package com.Retrofit;

public class APIUtils {
    final static String url = "http://192.168.1.159:8081/QuanLyBanHang/api/";

    public static DataClient getData()
    {
        return RetrofitClient.getClient(url).create(DataClient.class);
    }
}
