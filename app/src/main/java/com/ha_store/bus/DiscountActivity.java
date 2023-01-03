package com.ha_store.bus;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatImageButton;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;

import com.ha_store.R;
import com.ha_store.adapter.DiscountAdapter;
import com.ha_store.adapter.SPNoiBatAdapter;
import com.ha_store.dao.KhuyenMaiDAO;
import com.ha_store.dto.KhuyenMaiDTO;

import java.util.List;

public class DiscountActivity extends AppCompatActivity {

    RecyclerView r_khuyen_mai;
    List<KhuyenMaiDTO> ds_km;
    AppCompatImageButton btn_back;
    KhuyenMaiDAO khuyenMaiDAO;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_discount);
        khuyenMaiDAO = new KhuyenMaiDAO();
        Init();
        btn_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
                startActivity(new Intent(DiscountActivity.this, AccountActivity.class));
            }
        });
        setDSKM();
    }
    public void Init(){
        r_khuyen_mai = findViewById(R.id.r_khuyen_mai);
        btn_back = findViewById(R.id.btn_back);
    }
    private void setDSKM() {
        ds_km = khuyenMaiDAO.LayDanhSachKhuyenMai();
        RecyclerView.LayoutManager layoutManager = new GridLayoutManager(this,1,GridLayoutManager.VERTICAL, false);
        DiscountAdapter adapter = new DiscountAdapter(ds_km);
        r_khuyen_mai.setAdapter(adapter);
        r_khuyen_mai.setLayoutManager(layoutManager);
        r_khuyen_mai.setHasFixedSize(true);
    }
}