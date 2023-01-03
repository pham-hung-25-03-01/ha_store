package com.ha_store.bus;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager2.widget.ViewPager2;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.bottomnavigation.BottomNavigationItemView;
import com.google.android.material.bottomnavigation.BottomNavigationMenuView;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.imageview.ShapeableImageView;
import com.google.android.material.navigation.NavigationBarView;
import com.google.android.material.tabs.TabLayout;
import com.ha_store.R;
import com.ha_store.adapter.OptionAdapter;
import com.ha_store.adapter.OptionProductPageAdapter;
import com.ha_store.adapter.SPBanChayAdapter;
import com.ha_store.adapter.SPNoiBatAdapter;
import com.ha_store.adapter.sliderAdapter;
import com.ha_store.dao.GioHangDAO;
import com.ha_store.dao.KhachHangDAO;
import com.ha_store.dao.SanPhamDAO;
import com.ha_store.dto.LoaiSanPhamDTO;
import com.ha_store.dto.Option;
import com.ha_store.dto.Photo;
import com.ha_store.dto.SanPhamDTO;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.TimerTask;

import me.relex.circleindicator.CircleIndicator3;

public class HomeActivity extends AppCompatActivity {
    //new
    GioHangDAO gh_dao;
    KhachHangDAO kh_dao;
    SanPhamDAO sp_dao;

    private ViewPager2 v2_slider,vp_product_option;
    private TextView txt_searched,cart_badge;
    private BottomNavigationView bottom_nav;
    private CircleIndicator3 slider_control;

    TabLayout tabLayout;

    ConstraintLayout btn_search_text;

    Animation text_animation_move_in,text_animation_move_out;

    RecyclerView rc_option, rc_sp_ban_chay, rc_sp_noi_bat;
    List<Photo> photoList;
    List<String> history_search;
    List<Option> optionList;

    //new
    List<SanPhamDTO> ds_sp_ban_chay, ds_sp_noi_bat;
    List<LoaiSanPhamDTO> ds_loai_sp;

    int i;

    ShapeableImageView btn_ao;
    ShapeableImageView btn_quan;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        Init();
        btn_quan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
                Intent intent = new Intent(HomeActivity.this, ShoppingActivity.class);
                intent.putExtra("loai_san_pham_id", 1);
                startActivity(intent);
            }
        });
        btn_ao.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(HomeActivity.this, ShoppingActivity.class);
                intent.putExtra("loai_san_pham_id", 2);
                startActivity(intent);
            }
        });
        bottom_nav.getMenu().findItem(R.id.nav_home).setChecked(true);
        setSliderData();
        setHistorySearchData();
        setOptionData();

        //new
        setSPBanChay();
        setSPNoiBat();
        setSPTheoLoai();
        setNotifyBottomNav();
        java.util.Timer timer = new java.util.Timer();
        timer.scheduleAtFixedRate(new The_slide_timer(),4000,3000);
        timer.scheduleAtFixedRate(new The_searched_timer(),5000,3000);

        //new cick to search
        btn_search_text.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(HomeActivity.this,
                        SearchActivity.class);
                intent.putExtra("key_search", txt_searched.getText());
                v.getContext().startActivity(intent);
            }
        });

        //new
        bottom_nav.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId())
                {
                    case R.id.nav_home:
                        setHomeAction();
                        break;
                    case R.id.nav_shopping:
                        setShoppingAction();
                        break;
                    case R.id.nav_cart:
                        setCartAction();
                        break;
                    case R.id.nav_account:
                        setAccountAction();
                        break;
                    default:
                        setHomeAction();
                        break;
                }
                return true;
            }

            private void setAccountAction() {
                startActivity(new Intent(HomeActivity.this, AccountActivity.class));
            }

            private void setCartAction() {
                startActivity(new Intent(HomeActivity.this, CartActivity.class));
            }

            private void setShoppingAction() {
                Intent intent = new Intent(HomeActivity.this, ShoppingActivity.class);
                intent.putExtra("loai_san_pham_id", -1);
                startActivity(intent);
            }

            private void setHomeAction() {
                bottom_nav.getMenu().findItem(R.id.nav_home).setIcon(getDrawable(R.drawable.ic_home_heart));
                bottom_nav.getMenu().findItem(R.id.nav_home).setTitle("Cho bạn");
            }

        });


    }

    private void setSPTheoLoai() {
        OptionProductPageAdapter adapter = new OptionProductPageAdapter(this);
        vp_product_option.setAdapter(adapter);
        tabLayout = findViewById(R.id.tabLayout);
        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                vp_product_option.setCurrentItem(tab.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
        vp_product_option.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
            @Override
            public void onPageSelected(int position) {
                super.onPageSelected(position);
                tabLayout.getTabAt(position).select();
            }
        });
    }

    private void setSPNoiBat() {
        ds_sp_noi_bat = sp_dao.LayDanhSachSanPhamGiamGia(4);
        //  Toast.makeText(this, ds_sp_ban_chay.get(0).get_ten_san_pham()+"du lieu", Toast.LENGTH_SHORT).show();
        RecyclerView.LayoutManager layoutManager = new GridLayoutManager(this,2,GridLayoutManager.HORIZONTAL, false);
        SPNoiBatAdapter spNoiBatAdapter = new SPNoiBatAdapter(ds_sp_noi_bat);
        rc_sp_noi_bat.setAdapter(spNoiBatAdapter);
        rc_sp_noi_bat.setLayoutManager(layoutManager);
        rc_sp_noi_bat.setHasFixedSize(true);
    }


    private void setSPBanChay() {
        ds_sp_ban_chay = sp_dao.LayDanhSachSanPhamMoi(4);
      //  Toast.makeText(this, ds_sp_ban_chay.get(0).get_ten_san_pham()+"du lieu", Toast.LENGTH_SHORT).show();
        RecyclerView.LayoutManager layoutManager = new GridLayoutManager(this,2,GridLayoutManager.HORIZONTAL, false);
        SPBanChayAdapter spBanChayAdapter = new SPBanChayAdapter(ds_sp_ban_chay);
        rc_sp_ban_chay.setAdapter(spBanChayAdapter);
        rc_sp_ban_chay.setLayoutManager(layoutManager);
        rc_sp_ban_chay.setHasFixedSize(true);
    }

    private void setNotifyBottomNav() {
        BottomNavigationMenuView menu = (BottomNavigationMenuView)bottom_nav.getChildAt(0);
        BottomNavigationItemView item_nav_bn =(BottomNavigationItemView) menu.getChildAt(2);
        BottomNavigationItemView item_nav_shopping =(BottomNavigationItemView) menu.getChildAt(1);

        View notify_item_nav_shopping = LayoutInflater.from(this).inflate(R.layout.item_bage,menu,false);
        cart_badge = notify_item_nav_shopping.findViewById(R.id.cart_badge);
        cart_badge.setText("+20");


        View notify_item_nav_mess = LayoutInflater.from(this).inflate(R.layout.item_bage,menu,false);
        TextView cart_badge_shopping = notify_item_nav_mess.findViewById(R.id.cart_badge);
        cart_badge_shopping.setText("+90");

        item_nav_bn.addView(notify_item_nav_mess);
        item_nav_shopping.addView(notify_item_nav_shopping);

    }

    private void setOptionData() {
        optionList = getListOption();
        RecyclerView.LayoutManager layoutManager = new GridLayoutManager(this,2,GridLayoutManager.HORIZONTAL, false);
        OptionAdapter optionAdapter = new OptionAdapter(optionList);
        rc_option.setAdapter(optionAdapter);
        rc_option.setLayoutManager(layoutManager);
        rc_option.setHasFixedSize(true);
    }

    private void setHistorySearchData() {
        history_search = getListSearched();
    }

    private void setSliderData() {
        photoList = getListPhoto();
        sliderAdapter sliderAdapter = new sliderAdapter(this,photoList);
        v2_slider.setAdapter(sliderAdapter);
        slider_control.setViewPager(v2_slider);

    }



    private void Init() {
        i=0;
        txt_searched = findViewById(R.id.txt_searched);
        v2_slider =(ViewPager2) findViewById(R.id.v2_slider);
        vp_product_option =(ViewPager2) findViewById(R.id.vp_product_option);

        slider_control = findViewById((R.id.slider_control));
        rc_option =findViewById(R.id.rc_option);
        bottom_nav = findViewById(R.id.bottom_nav);
        btn_search_text = findViewById(R.id.btn_search_text);
        text_animation_move_in = AnimationUtils.loadAnimation(this, R.anim.animation_text_move_in);
        text_animation_move_out = AnimationUtils.loadAnimation(this,R.anim.animation_text_move_out);
        btn_ao = findViewById(R.id.btn_ao);
        btn_quan = findViewById(R.id.btn_quan);

        bottom_nav.getMenu().findItem(R.id.nav_home).setChecked(true);

        //new
        gh_dao = new GioHangDAO();
        sp_dao = new SanPhamDAO();
        rc_sp_ban_chay =findViewById(R.id.rc_sp_ban_chay);
        rc_sp_noi_bat =findViewById(R.id.rc_sp_noi_bat);

        ds_sp_ban_chay = new ArrayList<>();
        ds_sp_noi_bat = new ArrayList<>();
        ds_loai_sp = new ArrayList<>();

    }


    private List<Photo> getListPhoto() {
        List<Photo> list = new ArrayList<>();
        list.add(new Photo("https://giamgia88.com/wp-content/uploads/2020/12/12.12-banner-copy.jpg"));
        list.add(new Photo("https://thuvienmuasam.com/uploads/default/optimized/2X/0/067d48d824f580e062d22fe367ca2820080bf587_2_1024x540.jpeg"));
        list.add(new Photo("https://tingiasoc.com/wp-content/uploads/2021/12/lazada-12-12-sale-to-cuoi-nam.png"));
        list.add(new Photo("https://magiamgialientuc.com/wp-content/uploads/2019/12/lazada-tet-2021.jpg"));
        list.add(new Photo("https://danhgiatot.cdn.vccloud.vn/wp-content/uploads/2022/01/lazada-sale-tet-2022-7.jpg"));
        return list;
    }
    private List<String> getListSearched() {
        List<String> list = new ArrayList<>();
        list.add(new String("Áo dài"));
        list.add(new String("thời trang mùa hè"));
        list.add(new String("quần áo"));
        list.add(new String("sản phẩm giảm giá"));
        return list;
    }
    private List<Option> getListOption() {
        List<Option> list = new ArrayList<>();
        list.add(new Option("https://laz-img-cdn.alicdn.com/images/ims-web/TB1tq_lmBjTBKNjSZFuXXb0HFXa.png","LazMall"));
        list.add(new Option("https://laz-img-cdn.alicdn.com/images/ims-web/TB1UiNthUT1gK0jSZFhXXaAtVXa.png","Mã Giảm Giá"));
        list.add(new Option("https://www.pngkit.com/png/full/229-2296936_5-percent-off-png-icon-gim-gi-10.png","Mua Hết <99k"));
        list.add(new Option("https://laz-img-cdn.alicdn.com/images/ims-web/TB1tq_lmBjTBKNjSZFuXXb0HFXa.png","LazMall"));
        list.add(new Option("https://laz-img-cdn.alicdn.com/images/ims-web/TB1UiNthUT1gK0jSZFhXXaAtVXa.png","Mã Giảm Giá"));
        list.add(new Option("https://www.pngkit.com/png/full/229-2296936_5-percent-off-png-icon-gim-gi-10.png","Mua Hết <99k"));
        list.add(new Option("https://laz-img-cdn.alicdn.com/images/ims-web/TB1tq_lmBjTBKNjSZFuXXb0HFXa.png","LazMall"));
        list.add(new Option("https://laz-img-cdn.alicdn.com/images/ims-web/TB1UiNthUT1gK0jSZFhXXaAtVXa.png","Mã Giảm Giá"));
        list.add(new Option("https://www.pngkit.com/png/full/229-2296936_5-percent-off-png-icon-gim-gi-10.png","Mua Hết <99k"));
        list.add(new Option("https://laz-img-cdn.alicdn.com/images/ims-web/TB1UiNthUT1gK0jSZFhXXaAtVXa.png","Mã Giảm Giá"));
        list.add(new Option("https://www.pngkit.com/png/full/229-2296936_5-percent-off-png-icon-gim-gi-10.png","Mua Hết <99k"));
        return list;
    }
    public class The_slide_timer extends TimerTask {
        @Override
        public void run() {
            HomeActivity.this.runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    if (v2_slider.getCurrentItem()< photoList.size()-1) {
                        v2_slider.setCurrentItem(v2_slider.getCurrentItem()+1);
                    }
                    else
                        v2_slider.setCurrentItem(0);
                }
            });
        }
    }
    public class The_searched_timer extends TimerTask {
        @Override
        public void run() {

            HomeActivity.this.runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    if(history_search.size()>i){
                        txt_searched.setText(history_search.get(i));
                        i++;
                    }else{
                        txt_searched.setText(history_search.get(0));
                        i=0;
                    }
                    txt_searched.startAnimation(text_animation_move_in);
                }
            });
        }
    }
}