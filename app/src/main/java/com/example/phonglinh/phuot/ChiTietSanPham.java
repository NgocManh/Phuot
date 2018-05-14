package com.example.phonglinh.phuot;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.model.Giohang;
import com.model.SanPham;
import com.squareup.picasso.Picasso;


public class ChiTietSanPham extends AppCompatActivity {

    Toolbar toolbar;
    ImageView img;
    TextView ten, gia, mota;
    Spinner spinner;
    Button btn;
    SanPham item;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chi_tiet_san_pham);

        init();

        actionToolBar();

        getData();

        actionSpinner();

        actionButton();

    }

    private void actionButton() {

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                int soluong = (int) spinner.getSelectedItem();

                int sz = MainActivity.giohang.size();

                Log.d("abc", MainActivity.giohang.size()+"");
                boolean flag = false;
                if(MainActivity.giohang.size() > 0)
                {
                    for(int i = 0; i < sz; i++)
                    {
                        if(MainActivity.giohang.get(i).id == item.id)
                        {
                            flag = true;
                            MainActivity.giohang.get(i).soluong += soluong;
                            break;
                        }
                    }

                    if(flag == false)

                        MainActivity.giohang.add(new Giohang(item.id, soluong,item.price,item.name, item.image));
                }
                else
                {
                    MainActivity.giohang.add(new Giohang(item.id, soluong,item.price,item.name, item.image));
                }

                Intent intent = new Intent(getApplicationContext(), GioHang.class);
                startActivity(intent);

            }
        });
    }

    private void actionSpinner() {

        Integer[] nums = new Integer[]{1,2,3,4,5,6,7,8,9,10};
        ArrayAdapter<Integer> adapter = new ArrayAdapter<Integer>(this, android.R.layout.simple_spinner_dropdown_item, nums);
        spinner.setAdapter(adapter);

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

        toolbar = findViewById(R.id.toolBar_chitietsp);
        mota = findViewById(R.id.tvMoTa_chitietsp);
        img = findViewById(R.id.img_chitietsp);
        ten = findViewById(R.id.tvTen_chitietsp);
        gia = findViewById(R.id.tvGia_chitietsp);
        spinner = findViewById(R.id.spinner);
        btn = findViewById(R.id.btnMuaSanPham);

    }

    private void getData()
    {
        Intent intent = getIntent();
        item = (SanPham) intent.getSerializableExtra("thongtinsanpham");
        ten.setText(item.name);
        gia.setText(item.price + "");
        mota.setText(item.detail);
        Picasso.with(getApplicationContext()).load(item.image).into(img);
    }
}
