package com.ha_store.bus;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.ha_store.R;
import com.ha_store.adapter.SPNoiBatAdapter;
import com.ha_store.adapter.SearchedAdapter;
import com.ha_store.dao.SanPhamDAO;
import com.ha_store.dto.SanPhamDTO;

import java.util.ArrayList;
import java.util.List;
import java.util.TimerTask;

public class SearchActivity extends AppCompatActivity {
    EditText txt_search;
    String key_search;
    SanPhamDAO sp_dao;
    List<SanPhamDTO> ds_sp_tim_kiem;
    RecyclerView rc_searched;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
        Intent intent = getIntent();
        key_search = intent.getExtras().getString("key_search");
        Init();
        java.util.Timer timer = new java.util.Timer();
        txt_search.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                setDSKetQua();
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
    }

    private void setDSKetQua() {
        ds_sp_tim_kiem = sp_dao.TimKiemSanPhamTheoTen(txt_search.getText().toString());
        SearchedAdapter searchedAdapter = new SearchedAdapter(ds_sp_tim_kiem);
        rc_searched.setAdapter(searchedAdapter);
    }

    private void Init() {
        txt_search = findViewById(R.id.txt_search);
        txt_search.setText(key_search);
        sp_dao = new SanPhamDAO();
        ds_sp_tim_kiem = new ArrayList<>();
        rc_searched = findViewById(R.id.rc_searched);
    }

}
