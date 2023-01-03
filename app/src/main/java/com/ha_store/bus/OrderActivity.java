package com.ha_store.bus;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatImageButton;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.ha_store.R;
import com.ha_store.adapter.DiscountAdapter;
import com.ha_store.adapter.OrderAdapter;
import com.ha_store.dao.DonDatHangDAO;
import com.ha_store.dao.KhachHangDAO;
import com.ha_store.dto.DonDatHangDTO;

import java.util.List;

public class OrderActivity extends AppCompatActivity {

    RecyclerView r_don_dat_hang;
    List<DonDatHangDTO> ds_ddh;
    AppCompatImageButton btn_back;
    DonDatHangDAO donDatHangDAO;
    TextView txt_title;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order);
        donDatHangDAO = new DonDatHangDAO();
        Init();
        btn_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
                startActivity(new Intent(OrderActivity.this, AccountActivity.class));
            }
        });
        setDSDDH();
    }
    public void Init(){
        r_don_dat_hang = findViewById(R.id.r_don_dat_hang);
        btn_back = findViewById(R.id.btn_back);
        txt_title = findViewById(R.id.title);
    }
    private void setDSDDH() {
        Intent intent = getIntent();
        txt_title.setText(intent.getExtras().getString("title"));
        Integer khach_hang_id = KhachHangDAO.khach_hang_hien_tai.get_id();
        Integer trang_thai = intent.getExtras().getInt("trang_thai") == -1 ? null : intent.getExtras().getInt("trang_thai");
        ds_ddh = donDatHangDAO.LayDanhSachDonDatHang(khach_hang_id, trang_thai);
        RecyclerView.LayoutManager layoutManager = new GridLayoutManager(this,1,GridLayoutManager.VERTICAL, false);
        OrderAdapter adapter = new OrderAdapter(ds_ddh);
        r_don_dat_hang.setAdapter(adapter);
        r_don_dat_hang.setLayoutManager(layoutManager);
        r_don_dat_hang.setHasFixedSize(true);
    }
}