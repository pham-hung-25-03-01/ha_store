package com.ha_store.bus;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;
import androidx.appcompat.widget.AppCompatImageButton;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.ha_store.R;
import com.ha_store.adapter.BinhLuanAdapter;
import com.ha_store.dao.BinhLuanDAO;
import com.ha_store.dao.KhachHangDAO;
import com.ha_store.dto.BinhLuanDTO;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class BinhLuanActivity extends AppCompatActivity {
    RecyclerView rc_rating;
    AppCompatImageButton btn_back;
    int sp_id;
    List<BinhLuanDTO> ds_binh_luan;
    BinhLuanDAO bl_dao;
    EditText txt_them_danh_gia;
    AppCompatButton btn_them_bl;
    BinhLuanAdapter binhLuanAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_binh_luan);
        Intent intent = getIntent();
        sp_id = intent.getExtras().getInt("id");
        Init();
        setDSBinhLuan();
        btn_them_bl.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(KhachHangDAO.khach_hang_hien_tai.get_da_dang_nhap()){
                    ThemBinhLuan();
                }else{
                    hienDiaLogChuaDangNhap();
                }
            }
        });
        btn_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
                Intent intent = new Intent(BinhLuanActivity.this,
                        DetailActivity.class);
                intent.putExtra("id", sp_id);
                v.getContext().startActivity(intent);
            }
        });
    }
    private void hienDiaLogChuaDangNhap() {
        AlertDialog.Builder builder = new AlertDialog.Builder(BinhLuanActivity.this);
        builder.setCancelable(true);
        builder.setTitle("Thông báo");
        builder.setMessage("Đăng nhập để tiếp tục");
        builder.setPositiveButton("Đăng nhập",
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        startActivity(new Intent(BinhLuanActivity.this, LoginActivity.class));
                    }
                });
        builder.setNegativeButton(android.R.string.cancel, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
            }
        });
        AlertDialog dialog = builder.create();
        dialog.show();
    }

    private void ThemBinhLuan() {
        boolean da_them_bl = false;
        BigDecimal ngay_tao = convert_date_to_big_decimal(new Date());
        BinhLuanDTO bl = new BinhLuanDTO(KhachHangDAO.khach_hang_hien_tai.get_id(), sp_id,txt_them_danh_gia.getText().toString(),ngay_tao);
        da_them_bl = bl_dao.ThemBinhLuan(bl);
        if (da_them_bl) {
            Toast.makeText(this, "Đã thêm bình luận", Toast.LENGTH_SHORT).show();
            ds_binh_luan.add(bl);
            binhLuanAdapter.notifyDataSetChanged();
            txt_them_danh_gia.setText("");
        }else
            Toast.makeText(this, "Lỗi", Toast.LENGTH_SHORT).show();
    }
    private BigDecimal convert_date_to_big_decimal(Date date){
        SimpleDateFormat b_format = new SimpleDateFormat("ddMMyyyy");
        return new BigDecimal(b_format.format(date));
    }
    private void setDSBinhLuan() {
        try {
            ds_binh_luan = bl_dao.LayDanhSachBinhLuanTheoSanPhamId(sp_id);
            RecyclerView.LayoutManager layoutManager = new GridLayoutManager(this,1,GridLayoutManager.VERTICAL, false);
            binhLuanAdapter = new BinhLuanAdapter(ds_binh_luan);
            rc_rating.setAdapter(binhLuanAdapter);
            rc_rating.setLayoutManager(layoutManager);
            rc_rating.setHasFixedSize(true);
        }catch (Exception e){
            Toast.makeText(this, "Error get product by id", Toast.LENGTH_SHORT).show();
        }
    }

    private void Init() {
        rc_rating = findViewById(R.id.rc_rating);
        btn_them_bl = findViewById(R.id.btn_them_bl);
        btn_back = findViewById(R.id.btn_back);
        txt_them_danh_gia = findViewById(R.id.txt_them_danh_gia);
        ds_binh_luan = new ArrayList<>();
        bl_dao = new BinhLuanDAO();

    }
}