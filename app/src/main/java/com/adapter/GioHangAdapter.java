package com.adapter;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.phonglinh.phuot.GioHang;
import com.example.phonglinh.phuot.MainActivity;
import com.example.phonglinh.phuot.R;
import com.model.Giohang;
import com.squareup.picasso.Picasso;

import java.text.DecimalFormat;
import java.util.ArrayList;

public class GioHangAdapter extends RecyclerView.Adapter<GioHangAdapter.ViewHolder>{

    ArrayList<Giohang> arr;
    Context context = null;

    public GioHangAdapter(ArrayList<Giohang> arr) {
        this.arr = arr;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        if(context == null) context = parent.getContext();
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.dong_giohang, null);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int i) {
        Giohang item = arr.get(i);
        DecimalFormat paterm = new DecimalFormat("###,###,###");
        holder.name.setText(item.name);
        holder.gia.setText(paterm.format(item.price) + "đ");
        Picasso.with(context).load(item.image).into(holder.img);
        holder.soLuong.setText(item.soluong+"");
    }

    @Override
    public int getItemCount() {
        return arr.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        public ImageView img;
        public TextView name, gia, soLuong;
        public Button btnTru, btnCong;


        public ViewHolder(View v) {
            super(v);
            img = v.findViewById(R.id.img_giohang);
            name = v.findViewById(R.id.tvTen_giohang);
            gia = v.findViewById(R.id.tvGia_giohang);
            btnCong = v.findViewById(R.id.btnCong);
            btnTru = v.findViewById(R.id.btnTru);
            soLuong = v.findViewById(R.id.tvSoLuong);

            btnCong.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    btnTru.setVisibility(View.VISIBLE);
                    DecimalFormat patern = new DecimalFormat("###,###,###");
                    int i = getAdapterPosition();
                    MainActivity.giohang.get(i).soluong += 1;
                    soLuong.setText(MainActivity.giohang.get(i).soluong+"");
                    GioHang.tvGia.setText(patern.format(MainActivity.tongTien())+"đ");

                }
            });


            btnTru.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    int i = getAdapterPosition();

                    if(MainActivity.giohang.get(i).soluong <= 1)
                    {
                        btnTru.setVisibility(View.INVISIBLE);
                        return;
                    }


                    DecimalFormat patern = new DecimalFormat("###,###,###");

                    MainActivity.giohang.get(i).soluong -= 1;

                    soLuong.setText(MainActivity.giohang.get(i).soluong+"");

                    GioHang.tvGia.setText(patern.format(MainActivity.tongTien())+"đ");
                }
            });


            v.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View v) {

                    AlertDialog.Builder builder = new AlertDialog.Builder(context);
                    builder.setTitle("Xác nhận xóa sản phẩm này");
                    builder.setMessage("Bạn có chắc chắn muốn xóa không?");

                    builder.setPositiveButton("Có", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {


                            DecimalFormat patern = new DecimalFormat("###,###,###");



                            MainActivity.giohang.remove(getAdapterPosition());
                            notifyItemRemoved(getAdapterPosition());
                            notifyItemRangeChanged(getAdapterPosition(), MainActivity.giohang.size());

                            GioHang.tvGia.setText(patern.format(MainActivity.tongTien())+"đ");

                            if(MainActivity.giohang.size() <= 0)
                            {
                                GioHang.tvFlag.setVisibility(View.VISIBLE);
                            }


                        }
                    });

                    builder.setNegativeButton("Không", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {

                        }
                    });

                    builder.show();
                    return  true;
                }
            });
        }
    }
}
