package com.ha_store.bus;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.google.android.material.bottomnavigation.BottomNavigationItemView;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;
import com.ha_store.R;
import com.ha_store.adapter.SPBanChayAdapter;
import com.ha_store.adapter.SPChoBanAdapter;
import com.ha_store.dao.SanPhamDAO;
import com.ha_store.dto.SanPhamDTO;

import java.util.List;

public class ShoppingActivity extends AppCompatActivity {

    RecyclerView r_san_pham;
    SanPhamDAO sanPhamDAO;
    List<SanPhamDTO> ds_sp;
    BottomNavigationView bottom_nav;
    TextView txt_khong_tim_thay_san_pham;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shopping);
        sanPhamDAO = new SanPhamDAO();
        Init();
    }
    private void Init(){
        Intent intent = getIntent();
        Integer loai_san_pham_id = intent.getExtras().getInt("loai_san_pham_id") == -1 ? null : intent.getExtras().getInt("loai_san_pham_id");
        String key_word = intent.getExtras().getString("key_word").equals("") ? null : intent.getExtras().getString("key_word");
        ds_sp = sanPhamDAO.LayDanhSachSanPhamTheoLoai(loai_san_pham_id, key_word);
        r_san_pham = findViewById(R.id.r_san_pham);
        RecyclerView.LayoutManager layoutManager = new GridLayoutManager(this,2,GridLayoutManager.VERTICAL, false);
        SPChoBanAdapter spChoBanAdapter = new SPChoBanAdapter(ds_sp);
        r_san_pham.setAdapter(spChoBanAdapter);
        r_san_pham.setLayoutManager(layoutManager);
        r_san_pham.setHasFixedSize(true);
        bottom_nav = findViewById(R.id.bottom_nav);
        bottom_nav.getMenu().findItem(R.id.nav_shopping).setChecked(true);
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
                startActivity(new Intent(ShoppingActivity.this, AccountActivity.class));
            }

            private void setCartAction() {
                startActivity(new Intent(ShoppingActivity.this, CartActivity.class));
            }

            private void setShoppingAction() {
            }

            private void setHomeAction() {
                startActivity(new Intent(ShoppingActivity.this, HomeActivity.class));
            }
        });
        txt_khong_tim_thay_san_pham = findViewById(R.id.txt_khong_tim_thay_san_pham);
        txt_khong_tim_thay_san_pham.setVisibility(ds_sp.size() > 0 ? View.GONE : View.VISIBLE);
    }
}