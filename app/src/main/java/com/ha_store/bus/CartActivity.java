package com.ha_store.bus;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.ha_store.R;
import com.ha_store.adapter.GioHangAdapter;
import com.ha_store.adapter.OptionAdapter;
import com.ha_store.dao.GioHangDAO;
import com.ha_store.dao.KhachHangDAO;
import com.ha_store.dao.SanPhamDAO;
import com.ha_store.dto.DonDatHangDTO;
import com.ha_store.dto.GioHangDTO;
import com.ha_store.dto.SanPhamDTO;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

public class CartActivity extends AppCompatActivity {
    //new
    public static TextView txt_tong_tien;
    AppCompatButton btn_back, btn_thanh_toan,btn_login;
    LinearLayout ln_login;
    RecyclerView rc_cart;
    GioHangDAO gh_dao;
    SanPhamDAO sp_dao;
    List<GioHangDTO> ds_gh;
    int kh_id;
    int so_luong_gh=0;
    DecimalFormat format;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart);
        Init();
        if(KhachHangDAO.khach_hang_hien_tai.get_da_dang_nhap()){
            rc_cart.setVisibility(View.VISIBLE);
            ln_login.setVisibility(View.GONE);
            setDSGioHang();
            setTongTien();
            so_luong_gh = gh_dao.LaySoLuongSanPhamTrongGioHang(kh_id);
            btn_thanh_toan.setText("Thanh toán("+so_luong_gh+")");
        }else{
            ln_login.setVisibility(View.VISIBLE);
            rc_cart.setVisibility(View.GONE);
        }
        btn_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
                startActivity(new Intent(CartActivity.this, HomeActivity.class));
            }
        });
        btn_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
                startActivity(new Intent(CartActivity.this, LoginActivity.class));
            }
        });
        btn_thanh_toan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(CartActivity.this, CheckOutActivity.class));
            }
        });
    }

    private void setTongTien() {
        int tong = 0;
        for (GioHangDTO i: ds_gh) {
            SanPhamDTO sp = sp_dao.LayThongTinSanPhamTheoId(i.getSan_pham_id());
            tong+= (i.getSo_luong()*(int)(sp.get_gia_ban().doubleValue()*(1-sp.get_phan_tram_khuyen_mai())));
        }
        txt_tong_tien.setText(format.format(tong).toString()+"đ");
    }

    private void setDSGioHang() {
        ds_gh = gh_dao.LayGioHangTheoKhachHangId(kh_id);
        RecyclerView.LayoutManager layoutManager = new GridLayoutManager(this,1,GridLayoutManager.VERTICAL, false);
        GioHangAdapter gioHangAdapter = new GioHangAdapter(ds_gh);
        rc_cart.setAdapter(gioHangAdapter);
        rc_cart.setLayoutManager(layoutManager);
        rc_cart.setHasFixedSize(true);
    }

    private void Init() {
        ln_login = findViewById(R.id.ln_login);
        btn_login = findViewById(R.id.btn_login);
        btn_back = findViewById(R.id.btn_back);
        btn_thanh_toan = findViewById(R.id.btn_thanh_toan);
        rc_cart = findViewById(R.id.rc_cart);
        ds_gh = new ArrayList<>();
        gh_dao = new GioHangDAO();
        sp_dao = new SanPhamDAO();
        kh_id = KhachHangDAO.khach_hang_hien_tai.get_id();
        txt_tong_tien = findViewById(R.id.txt_tong_tien);
        format = new DecimalFormat("###,###,###");


    }
}