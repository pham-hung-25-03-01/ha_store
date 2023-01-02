package com.ha_store.bus;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.ha_store.R;

public class SettingActivity extends AppCompatActivity {
    TextView btn_thong_tin_tk;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting);
        Init();
        btn_thong_tin_tk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(SettingActivity.this, AccountInfoActivity.class));
            }
        });
    }

    private void Init() {
        btn_thong_tin_tk = findViewById(R.id.btn_thong_tin_tk);
    }
}