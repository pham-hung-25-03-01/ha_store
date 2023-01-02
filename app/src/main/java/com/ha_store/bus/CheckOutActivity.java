package com.ha_store.bus;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.ha_store.R;

public class CheckOutActivity extends AppCompatActivity {

    AppCompatButton btn_back_to_cart;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_check_out);
        Init();
//        btn_back_to_cart.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                startActivity(new Intent(CheckOutActivity.this, CartActivity.class));
//            }
//        });
    }

    private void Init() {
        btn_back_to_cart = findViewById(R.id.btn_back_to_cart);
    }
}