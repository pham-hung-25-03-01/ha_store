package com.ha_store.bus;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;
import androidx.appcompat.widget.AppCompatImageButton;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.ha_store.R;

public class CheckOutActivity extends AppCompatActivity {

    AppCompatImageButton btn_back;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_check_out);
        Init();
        btn_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
                startActivity(new Intent(CheckOutActivity.this, CartActivity.class));
            }
        });
    }

    private void Init() {
        btn_back = findViewById(R.id.btn_back);
    }
}