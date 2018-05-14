package com.example.phonglinh.phuot;

import android.content.Intent;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Toast;
import android.widget.ViewFlipper;

import com.Retrofit.APIUtils;
import com.Retrofit.DataClient;
import com.Ultil.CheckConnectInternet;
import com.adapter.LoaiSanPhamAdapter;
import com.adapter.SanPhamMoiNhatAdapter;
import com.model.Giohang;
import com.model.LoaiSanPham;
import com.model.SanPham;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    Toolbar toolbar;
    ViewFlipper viewFlipper;
    NavigationView navigationView;
    RecyclerView recyclerView;
    ListView lvNavi;
    DrawerLayout drawerLayout;
    LoaiSanPhamAdapter adapter;
    ArrayList<LoaiSanPham> arrLoaiSanPham = new ArrayList<>();
    ArrayList<SanPham> arrSanPham = new ArrayList<>();

    public static ArrayList<Giohang> giohang;

    public static long tongTien()
    {
        long sum = 0;

        for(Giohang x: MainActivity.giohang)
        {
            sum += x.soluong * x.price;
        }

        return sum;
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);

        init();

        actionBar();

        actionViewFlipper();

        actionMenu();

        actionSanPhamMoiNhat();


    }

    private void actionSanPhamMoiNhat() {

        
        Call<List<SanPham>> call = APIUtils.getData().getListSanPham();
        call.enqueue(new Callback<List<SanPham>>() {
            @Override
            public void onResponse(Call<List<SanPham>> call, Response<List<SanPham>> response) {

                arrSanPham = (ArrayList<SanPham>) response.body();

                /*Collections.shuffle(arrSanPham);*/
                SanPhamMoiNhatAdapter adapter = new SanPhamMoiNhatAdapter(arrSanPham);
                recyclerView.setHasFixedSize(true);
                recyclerView.setLayoutManager(new GridLayoutManager(getApplicationContext(),2));
                recyclerView.setAdapter(adapter);
            }

            @Override
            public void onFailure(Call<List<SanPham>> call, Throwable t) {
                Toast.makeText(MainActivity.this, "eo len duoc", Toast.LENGTH_SHORT).show();
            }
        });
    }

    void actionMenu() {

        DataClient dataClient = APIUtils.getData();

        Call<List<LoaiSanPham>> call = dataClient.getList();

        call.enqueue(new Callback<List<LoaiSanPham>>() {

            @Override
            public void onResponse(Call<List<LoaiSanPham>> call, Response<List<LoaiSanPham>> response) {
                arrLoaiSanPham.add(new LoaiSanPham(0, "Trang chủ", "https://sv1.uphinhnhanh.com/images/2018/05/10/trang-chu-la-gi.jpg"));
                arrLoaiSanPham.addAll((ArrayList<LoaiSanPham>) response.body());
                arrLoaiSanPham.add(new LoaiSanPham(0, "Liên hệ", "https://sv1.uphinhnhanh.com/images/2018/05/10/lien-he-1.jpg"));
                adapter = new LoaiSanPhamAdapter(arrLoaiSanPham);
                lvNavi.setAdapter(adapter);
            }

            @Override
            public void onFailure(Call<List<LoaiSanPham>> call, Throwable t) {
                Toast.makeText(MainActivity.this, "tach cmnr", Toast.LENGTH_LONG).show();
            }
        });



        // touch menu
        lvNavi.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int i, long id) {
                switch (i)
                {
                    case 0:
                        drawerLayout.closeDrawer(GravityCompat.START);
                        break;
                    case 1:
                        Intent dodangoai = new Intent(MainActivity.this, DoDaNgoai.class);
                        dodangoai.putExtra("type", arrLoaiSanPham.get(i).id);
                        startActivity(dodangoai);
                        drawerLayout.closeDrawer(GravityCompat.START);
                        break;
                    case 2:
                        Intent dobaoho = new Intent(MainActivity.this, DoBaoHo.class);
                        dobaoho.putExtra("type", arrLoaiSanPham.get(i).id);
                        startActivity(dobaoho);
                        drawerLayout.closeDrawer(GravityCompat.START);
                        break;
                    case 3:
                        Intent lienhe = new Intent(MainActivity.this, LienHe.class);
                        lienhe.putExtra("type", arrLoaiSanPham.get(i).id);
                        startActivity(lienhe);
                        drawerLayout.closeDrawer(GravityCompat.START);
                        break;

                }

            }
        });

    }

    private void actionBar() {
        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        toolbar.setNavigationIcon(android.R.drawable.ic_menu_sort_by_size);

        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                drawerLayout.openDrawer(GravityCompat.START);
            }
        });
    }

    private void actionViewFlipper() {

        ArrayList<String> arr = new ArrayList<>();

        arr.add("http://cdn.nhanh.vn/cdn/store/5620/bn/sb_1467198102_388.png");
        arr.add("https://sv1.uphinhnhanh.com/images/2018/05/10/4478945-paint-wallpapers.jpg");

        for (int i = 0; i < arr.size(); i++) {
            ImageView imageView = new ImageView(getApplicationContext());
            Picasso.with(getApplicationContext()).load(arr.get(i)).into(imageView);
            imageView.setScaleType(ImageView.ScaleType.FIT_XY);
            viewFlipper.addView(imageView);
        }

        viewFlipper.setFlipInterval(5000);
        viewFlipper.setAutoStart(true);
        Animation slide_left = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.slide_left);
        Animation slide_right = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.slide_right);
        viewFlipper.setInAnimation(slide_left);
        viewFlipper.setOutAnimation(slide_right);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId())
        {
            case R.id.menugiohang:
                Intent intent = new Intent(getApplicationContext(), GioHang.class);
                startActivity(intent);
        }
        return super.onOptionsItemSelected(item);
    }

    private void init() {

        toolbar = findViewById(R.id.toolBar);
        viewFlipper = findViewById(R.id.viewFlipper);
        navigationView = findViewById(R.id.naviView);
        recyclerView = findViewById(R.id.recyclerView);
        lvNavi = findViewById(R.id.lvNavi);
        drawerLayout = findViewById(R.id.drawerLayout);

        if(giohang == null) giohang = new ArrayList<>();

    }


}
