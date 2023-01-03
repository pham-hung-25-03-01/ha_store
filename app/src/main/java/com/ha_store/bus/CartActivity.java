package com.ha_store.bus;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.ha_store.R;

public class CartActivity extends AppCompatActivity {
    //new
    AppCompatButton btn_back, btn_thanh_toan;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart);
        Init();
        btn_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
                startActivity(new Intent(CartActivity.this, HomeActivity.class));
            }
        });
        btn_thanh_toan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(CartActivity.this, CheckOutActivity.class));
            }
        });
    }

    private void Init() {

        btn_back = findViewById(R.id.btn_back);
        btn_thanh_toan = findViewById(R.id.btn_thanh_toan);

    }
}