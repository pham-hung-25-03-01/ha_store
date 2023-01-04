package com.ha_store.bus;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;
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
    AppCompatButton btn_tim_kiem;
    AppCompatButton btn_back;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
        Intent intent = getIntent();
        key_search = intent.getExtras().getString("key_search");
        Init();
        btn_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
                startActivity(new Intent(SearchActivity.this, HomeActivity.class));
            }
        });
        btn_tim_kiem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
                Intent intent = new Intent(SearchActivity.this, ShoppingActivity.class);
                intent.putExtra("loai_san_pham_id", -1);
                intent.putExtra("key_word", txt_search.getText().toString());
                startActivity(intent);
            }
        });
    }

    private void Init() {
        txt_search = findViewById(R.id.txt_search);
        txt_search.setText(key_search);
        btn_tim_kiem = findViewById(R.id.btn_tim_kiem);
        btn_back = findViewById(R.id.btn_back);
        sp_dao = new SanPhamDAO();
        ds_sp_tim_kiem = new ArrayList<>();
    }

}
