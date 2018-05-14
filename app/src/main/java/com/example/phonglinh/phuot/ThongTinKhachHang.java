package com.example.phonglinh.phuot;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.Retrofit.APIUtils;
import com.model.Chitietdonhang;
import com.model.Donhang;
import com.model.Giohang;
import com.model.ResultBO;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ThongTinKhachHang extends AppCompatActivity {

    Button btnThanhToan, btnTroVe;
    EditText edHoten, edSdt, edEmail;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_thong_tin_khach_hang);

        init();

        actionButtonThanhToan();

        actionButtonTroVe();

    }

    private void actionButtonTroVe() {
        btnTroVe.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(),MainActivity.class).setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP));
            }
        });


    }

    private void actionButtonThanhToan() {
        btnThanhToan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                Donhang donhang=new Donhang();
                donhang.email= edEmail.getText().toString();
                donhang.name= edHoten.getText().toString();
                donhang.sdt= edSdt.getText().toString();

                for(Giohang item: MainActivity.giohang)
                {
                    Chitietdonhang chitiet=new Chitietdonhang();
                    chitiet.gia=item.price * item.soluong;
                    chitiet.soluong=item.soluong;
                    chitiet.masanpham=item.id;
                    chitiet.tensanpham=item.name;
                    donhang.listChiTietDonHang.add(chitiet);
                }

                Call<ResultBO> call = APIUtils.getData().createHoaDon(donhang);
                call.enqueue(new Callback<ResultBO>() {
                    @Override
                    public void onResponse(Call<ResultBO> call, Response<ResultBO> response) {

                        Toast.makeText(ThongTinKhachHang.this, "Gửi đơn hàng thành công. Mời bạn đợi xác nhận từ hệ thống!!", Toast.LENGTH_SHORT).show();
                        MainActivity.giohang = new ArrayList<>();
                        Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                        startActivity(intent);
                    }

                    @Override
                    public void onFailure(Call<ResultBO> call, Throwable t) {
                        /*Toast.makeText(ThongTinKhachHang.this, "tach", Toast.LENGTH_SHORT).show();*/
                    }
                });


            }
        });
    }

    private void init() {
        edHoten = findViewById(R.id.edHoTen);
        edSdt = findViewById(R.id.edSdt);
        edEmail = findViewById(R.id.edEmail);
        btnThanhToan = findViewById(R.id.btnXacNhan_ttkh);
        btnTroVe = findViewById(R.id.btnBack_ttkh);
    }
}
