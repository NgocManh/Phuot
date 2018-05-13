package com.example.phonglinh.phuot;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import org.w3c.dom.Text;

public class ChiTietSanPham extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chi_tiet_san_pham);
        TextView tv = findViewById(R.id.tv_chitietsp);
        tv.setText("ahihi");
    }
}
