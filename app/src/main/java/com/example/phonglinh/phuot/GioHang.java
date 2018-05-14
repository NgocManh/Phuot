package com.example.phonglinh.phuot;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.adapter.GioHangAdapter;

import java.text.DecimalFormat;


public class GioHang extends AppCompatActivity {

    public static RecyclerView rv;
    public static TextView tvGia, tvFlag;
    Button btnTiepTuc, btnThanhToan;
    Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gio_hang);

        init();

        actionToolBar();

        actionGioHang();

        actionButtonTiepTuc();

        actionButtonThanhToan();
    }

    private void actionButtonThanhToan() {
        btnThanhToan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(MainActivity.giohang.size() <= 0)

                    Toast.makeText(GioHang.this, "Giỏ hàng rỗng", Toast.LENGTH_SHORT).show();

                else
                {
                    Intent intent = new Intent(getApplicationContext(), ThongTinKhachHang.class);
                    startActivity(intent);
                }


            }
        });
    }


    private void actionButtonTiepTuc() {
        btnTiepTuc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(intent);

            }
        });

    }

    private void actionGioHang() {
        if(MainActivity.giohang.size() != 0) tvFlag.setVisibility(View.INVISIBLE);
        GioHangAdapter adapter = new GioHangAdapter(MainActivity.giohang);
        rv.setLayoutManager(new GridLayoutManager(getApplicationContext(), 1));
        rv.setAdapter(adapter);
    }

    private void actionToolBar() {
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    private void init() {

        rv = findViewById(R.id.rv_giohang);
        btnThanhToan = findViewById(R.id.btnThanhToan);
        btnTiepTuc = findViewById(R.id.btnTiepTuc);
        tvGia = findViewById(R.id.tvGia);
        tvFlag = findViewById(R.id.tvFlag);
        toolbar = findViewById(R.id.toolBar_giohang);
        DecimalFormat paterm = new DecimalFormat("###,###,###");
        tvGia.setText(paterm.format(MainActivity.tongTien())+"đ");
    }

}
