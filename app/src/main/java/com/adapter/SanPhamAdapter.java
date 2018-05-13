package com.adapter;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.phonglinh.phuot.R;
import com.model.SanPham;
import com.squareup.picasso.Picasso;

import java.text.DecimalFormat;
import java.util.ArrayList;

public class SanPhamAdapter extends RecyclerView.Adapter<SanPhamAdapter.ViewHolder>{


    ArrayList<SanPham> arr;
    Context context = null;

    public SanPhamAdapter(ArrayList<SanPham> arr) {
        this.arr = arr;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        if(context == null) context = parent.getContext();

        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.dong_sanpham, null);

        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int i) {
        SanPham item = arr.get(i);
        Picasso.with(context).load(item.image).into(holder.img);
        DecimalFormat paterm = new DecimalFormat("###,###,###");
        holder.ten.setText(item.name);
        holder.gia.setText(paterm.format(item.price) + "đ");

        holder.mota.setText(item.detail.toString());

        holder.mota.setMaxLines(2);

    }

    @Override
    public int getItemCount() {
        return arr.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        public ImageView img;

        public TextView ten, gia, mota;

        public ViewHolder(View v) {

            super(v);
            img = v.findViewById(R.id.imgSanPham);
            ten = v.findViewById(R.id.tvTenSanPham);
            gia = v.findViewById(R.id.tvGiaSanPham);
            mota = v.findViewById(R.id.tvMoTaSanPham);
        }
    }
}
