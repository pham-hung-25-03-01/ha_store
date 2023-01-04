package com.ha_store.bus;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;
import androidx.appcompat.widget.AppCompatImageButton;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;
import com.ha_store.R;
import com.ha_store.dao.KhachHangDAO;
import com.ha_store.dao.KhuyenMaiDAO;
import com.ha_store.dao.YeuThichDAO;

public class AccountActivity extends AppCompatActivity {
    KhachHangDAO khachHangDAO;
    YeuThichDAO yeuThichDAO;
    KhuyenMaiDAO khuyenMaiDAO;
    private BottomNavigationView bottom_nav;
    AppCompatImageButton btn_setting;
    AppCompatButton btn_login;
    AppCompatImageButton btn_edit;
    RelativeLayout r_info;
    RelativeLayout r_login;
    ScrollView s_category;
    TextView txt_name;
    TextView txt_yeu_thich;
    TextView txt_ma_giam_gia;
    TextView btn_tat_ca_don_hang;
    LinearLayout btn_ma_khuyen_mai;
    LinearLayout btn_yeu_thich;
    LinearLayout btn_cho_duyet;
    LinearLayout btn_cho_giao_hang;
    LinearLayout btn_da_nhan_hang;
    LinearLayout btn_don_da_huy;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_account);
        Init();
        bottom_nav.getMenu().findItem(R.id.nav_account).setChecked(true);
        khachHangDAO = new KhachHangDAO();
        yeuThichDAO = new YeuThichDAO();
        khuyenMaiDAO = new KhuyenMaiDAO();
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
            }

            private void setCartAction() {
                startActivity(new Intent(AccountActivity.this, CartActivity.class));
            }

            private void setShoppingAction() {
                Intent intent = new Intent(AccountActivity.this, ShoppingActivity.class);
                intent.putExtra("loai_san_pham_id", -1);
                intent.putExtra("key_word", "");
                startActivity(intent);
            }

            private void setHomeAction() {
                startActivity(new Intent(AccountActivity.this, HomeActivity.class));
            }

        });
        btn_setting.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
                startActivity(new Intent(AccountActivity.this, SettingActivity.class));
            }
        });
        btn_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
                startActivity(new Intent(AccountActivity.this, LoginActivity.class));
            }
        });
        btn_edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
                startActivity(new Intent(AccountActivity.this, AccountInfoActivity.class));
            }
        });
        btn_ma_khuyen_mai.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
                startActivity(new Intent(AccountActivity.this, DiscountActivity.class));
            }
        });
        btn_yeu_thich.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
                startActivity(new Intent(AccountActivity.this, FavoriteActivity.class));
            }
        });
        txt_ma_giam_gia.setText(khuyenMaiDAO.LaySoLuongKhuyenMai().toString());
        txt_yeu_thich.setText(yeuThichDAO.LaySoLuongYeuThichTheoKhachHangId(KhachHangDAO.khach_hang_hien_tai.get_id()).toString());
        Integer id = KhachHangDAO.khach_hang_hien_tai.get_id();
        if(KhachHangDAO.khach_hang_hien_tai.get_da_dang_nhap()){
            txt_name.setText(khachHangDAO.LayThongTinKhachHangTheoId(id).ho_ten());
        }
        btn_cho_duyet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(AccountActivity.this, OrderActivity.class);
                intent.putExtra("trang_thai", 1);
                intent.putExtra("title", "Đơn hàng chờ duyệt");
                startActivity(intent);
            }
        });
        btn_cho_giao_hang.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(AccountActivity.this, OrderActivity.class);
                intent.putExtra("trang_thai", 2);
                intent.putExtra("title", "Đơn hàng chờ giao");
                startActivity(intent);
            }
        });
        btn_da_nhan_hang.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(AccountActivity.this, OrderActivity.class);
                intent.putExtra("trang_thai", 3);
                intent.putExtra("title", "Đơn hàng đã nhận");
                startActivity(intent);
            }
        });
        btn_don_da_huy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(AccountActivity.this, OrderActivity.class);
                intent.putExtra("trang_thai", 0);
                intent.putExtra("title", "Đơn hàng đã hủy");
                startActivity(intent);
            }
        });
        btn_tat_ca_don_hang.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(AccountActivity.this, OrderActivity.class);
                intent.putExtra("trang_thai", -1);
                intent.putExtra("title", "Tất cả đơn hàng của bạn");
                startActivity(intent);
            }
        });
    }

    private void Init() {
        bottom_nav = findViewById(R.id.bottom_nav);
        r_info = (RelativeLayout)findViewById(R.id.r_info);
        r_login = (RelativeLayout)findViewById(R.id.r_login);
        s_category = (ScrollView)findViewById(R.id.s_category);
        btn_setting = (AppCompatImageButton) findViewById(R.id.btn_setting);
        btn_login = (AppCompatButton) findViewById(R.id.btn_login);
        btn_edit = (AppCompatImageButton) findViewById(R.id.btn_edit);
        txt_name = (TextView) findViewById(R.id.txt_name);
        txt_yeu_thich = (TextView) findViewById(R.id.txt_yeu_thich);
        txt_ma_giam_gia = (TextView) findViewById(R.id.txt_ma_giam_gia);
        btn_ma_khuyen_mai = findViewById(R.id.btn_ma_giam_gia);
        btn_yeu_thich = findViewById(R.id.btn_yeu_thich);
        btn_cho_duyet = findViewById(R.id.btn_cho_duyet);
        btn_cho_giao_hang = findViewById(R.id.btn_cho_giao_hang);
        btn_da_nhan_hang = findViewById(R.id.btn_da_nhan_hang);
        btn_don_da_huy = findViewById(R.id.btn_don_da_huy);
        btn_tat_ca_don_hang = findViewById(R.id.btn_tat_ca_don_hang);
        if(KhachHangDAO.khach_hang_hien_tai.get_da_dang_nhap()){
            r_info.setVisibility(View.VISIBLE);
            s_category.setVisibility(View.VISIBLE);
            r_login.setVisibility(View.GONE);
        }
        else{
            r_info.setVisibility(View.GONE);
            s_category.setVisibility(View.GONE);
            r_login.setVisibility(View.VISIBLE);
        }
    }
}