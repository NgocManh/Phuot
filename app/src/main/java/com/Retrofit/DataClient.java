package com.Retrofit;

import com.model.LoaiSanPham;
import com.model.SanPham;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface DataClient {

    @GET("loaisanpham")
    Call<List<LoaiSanPham>> getList();

    @GET("sanpham")
    Call<List<SanPham>> getListSanPham();

    @GET("sanpham/dodangoai")
    Call<List<SanPham>> getListDoDaNgoai();

    @GET("sanpham/dobaoho")
    Call<List<SanPham>> getListDoBaoHo();


}
