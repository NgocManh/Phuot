package com.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.phonglinh.phuot.R;
import com.model.LoaiSanPham;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class LoaiSanPhamAdapter extends BaseAdapter{

    ArrayList<LoaiSanPham> arr;


    public LoaiSanPhamAdapter(ArrayList<LoaiSanPham> arr) {
        this.arr = arr;
    }

    @Override
    public int getCount() {
        return arr.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup parent) {

        LayoutInflater inflater = LayoutInflater.from(parent.getContext());

        view = inflater.inflate(R.layout.dong_loaisanpham, null);

        ImageView imageView = view.findViewById(R.id.imgloaisp);
        TextView tv = view.findViewById(R.id.tvloaisp);

        tv.setText(arr.get(i).name);

        Picasso.with(parent.getContext()).load(arr.get(i).image).into(imageView);

        return view;
    }
}
