package com.example.phonglinh.phuot;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.Retrofit.APIUtils;
import com.adapter.SanPhamAdapter;
import com.model.SanPham;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DoBaoHo extends AppCompatActivity {

    ArrayList<SanPham> arr = new ArrayList<>();
    SanPhamAdapter adapter;
    RecyclerView rvDoBaoHo;
    Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_do_bao_ho);

        init();

        actionListSanPham();
    }

    private void actionListSanPham() {
        Call<List<SanPham>> call = APIUtils.getData().getListDoBaoHo();
        call.enqueue(new Callback<List<SanPham>>() {
            @Override
            public void onResponse(Call<List<SanPham>> call, Response<List<SanPham>> response) {

                arr = (ArrayList<SanPham>) response.body();
                adapter = new SanPhamAdapter(arr);
                rvDoBaoHo.setLayoutManager(new GridLayoutManager(getApplicationContext(), 1));
                //rvDoDaNgoai.setLayoutManager(new LinearLayoutManager(getApplicationContext(),0,false));
                rvDoBaoHo.setAdapter(adapter);
            }

            @Override
            public void onFailure(Call<List<SanPham>> call, Throwable t) {

            }
        });
    }

    private void init() {

        rvDoBaoHo = findViewById(R.id.rv_dobaoho);
        toolbar = findViewById(R.id.toolBar_dobaoho);

        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
}
