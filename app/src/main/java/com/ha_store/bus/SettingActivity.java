package com.ha_store.bus;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatImageButton;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.ha_store.R;
import com.ha_store.dao.KhachHangDAO;

import java.math.BigDecimal;
import java.text.DecimalFormat;

public class SettingActivity extends AppCompatActivity {
    KhachHangDAO khachHangDAO;
    TextView btn_thong_tin_tk;
    AppCompatImageButton btn_back;
    TextView btn_logout;
    TextView btn_address;
    TextView txt_wallet;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting);
        Init();
        khachHangDAO = new KhachHangDAO();
        btn_thong_tin_tk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(SettingActivity.this, AccountInfoActivity.class));
            }
        });
        btn_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
                startActivity(new Intent(SettingActivity.this, AccountActivity.class));
            }
        });
        btn_logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                KhachHangDAO.khach_hang_hien_tai.set_da_dang_nhap(false);
                finish();
                startActivity(new Intent(SettingActivity.this, AccountActivity.class));
            }
        });
        btn_address.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
                startActivity(new Intent(SettingActivity.this, AddressActivity.class));
            }
        });
        BigDecimal so_du_vi = khachHangDAO.LayThongTinKhachHangTheoId(KhachHangDAO.khach_hang_hien_tai.get_id()).get_so_du_vi();
        DecimalFormat format = new DecimalFormat("###,###,###");
        txt_wallet.setText(format.format(so_du_vi).toString() + "đ");
    }

    private void Init() {
        btn_thong_tin_tk = findViewById(R.id.btn_thong_tin_tk);
        btn_back = (AppCompatImageButton) findViewById(R.id.btn_back);
        btn_logout = (TextView) findViewById(R.id.btn_logout);
        btn_address = (TextView) findViewById(R.id.btn_address);
        txt_wallet = (TextView) findViewById(R.id.txt_wallet);
    }
}