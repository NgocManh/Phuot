package com.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.phonglinh.phuot.ChiTietSanPham;
import com.example.phonglinh.phuot.R;
import com.model.SanPham;
import com.squareup.picasso.Picasso;

import java.text.DecimalFormat;
import java.util.ArrayList;

public class SanPhamMoiNhatAdapter extends RecyclerView.Adapter<SanPhamMoiNhatAdapter.ViewHolder>{

    ArrayList<SanPham> arr;
    Context context = null;


    public SanPhamMoiNhatAdapter(ArrayList<SanPham> arr) {

        this.arr = arr;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        if(context == null) context = parent.getContext();

        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.dong_sanphammoinhat, null);

        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int i) {
        SanPham item = arr.get(i);
        DecimalFormat partern = new DecimalFormat("###,###,###");
        Picasso.with(context).load(item.image).into(holder.img);
        holder.tvGiasp.setText(partern.format(item.price) + "đ");
        holder.tvTensp.setText(item.name);


    }

    @Override
    public int getItemCount() {
        return arr.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder
    {
        public ImageView img;
        public TextView tvTensp;
        public TextView tvGiasp;

        public ViewHolder(View itemView) {
            super(itemView);
            img  = itemView.findViewById(R.id.imgsp);
            tvTensp = itemView.findViewById(R.id.tvtensp);
            tvGiasp = itemView.findViewById(R.id.tvgiasp);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(context, ChiTietSanPham.class);
                    context.startActivity(intent);
                }
            });
        }
    }


}
