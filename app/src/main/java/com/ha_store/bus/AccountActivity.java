package com.ha_store.bus;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;
import androidx.appcompat.widget.AppCompatImageButton;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import com.ha_store.R;
import com.ha_store.dao.KhachHangDAO;

public class AccountActivity extends AppCompatActivity {
    KhachHangDAO khachHangDAO;
    AppCompatImageButton btn_setting;
    AppCompatButton btn_login;
    AppCompatImageButton btn_edit;
    RelativeLayout r_info;
    RelativeLayout r_login;
    ScrollView s_category;
    TextView txt_name;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_account);
        Init();
        khachHangDAO = new KhachHangDAO();
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
        Integer id = KhachHangDAO.khach_hang_hien_tai.get_id();
        if(KhachHangDAO.khach_hang_hien_tai.get_da_dang_nhap()){
            txt_name.setText(khachHangDAO.LayThongTinKhachHangTheoId(id).ho_ten());
        }
    }

    private void Init() {
        r_info = (RelativeLayout)findViewById(R.id.r_info);
        r_login = (RelativeLayout)findViewById(R.id.r_login);
        s_category = (ScrollView)findViewById(R.id.s_category);
        btn_setting = (AppCompatImageButton) findViewById(R.id.btn_setting);
        btn_login = (AppCompatButton) findViewById(R.id.btn_login);
        btn_edit = (AppCompatImageButton) findViewById(R.id.btn_edit);
        txt_name = (TextView) findViewById(R.id.txt_name);
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