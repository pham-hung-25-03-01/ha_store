package com.ha_store.bus;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import com.ha_store.R;
import com.ha_store.adapter.ProductSizeAdapter;
import com.ha_store.adapter.SPNoiBatAdapter;
import com.ha_store.dao.KhoDAO;
import com.ha_store.dao.SanPhamDAO;
import com.ha_store.dto.KichThuocDTO;
import com.ha_store.dto.SanPhamDTO;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

public class DetailActivity extends AppCompatActivity {
    TextView product_price_sale,product_price,product_discount,product_name,txt_product_rating;
    RatingBar rb_rating;
    SanPhamDAO sp_dao;
    KhoDAO kho_dao;
    SanPhamDTO sp;
    DecimalFormat format;
    List<KichThuocDTO> ds_kich_thuoc;
    RecyclerView rc_chon_size;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        Intent intent = getIntent();
        int id = intent.getExtras().getInt("id");
       Toast.makeText(getApplicationContext(),"id"+id,Toast.LENGTH_LONG).show();
        Init();
        setDetailProduct(id);
        
        setListSize(id);
    }

    private void setListSize(int id) {
        try {
            ds_kich_thuoc = kho_dao.LayDanhSachKichThuocTheoSanPhamId(id);
            //  Toast.makeText(this, ds_sp_ban_chay.get(0).get_ten_san_pham()+"du lieu", Toast.LENGTH_SHORT).show();
            RecyclerView.LayoutManager layoutManager = new GridLayoutManager(this,2,GridLayoutManager.HORIZONTAL, false);
            ProductSizeAdapter productSizeAdapter = new ProductSizeAdapter(ds_kich_thuoc);
            rc_chon_size.setAdapter(productSizeAdapter);
            rc_chon_size.setLayoutManager(layoutManager);
            rc_chon_size.setHasFixedSize(true);
        }catch (Exception e){
            Toast.makeText(this, "Error get product by id", Toast.LENGTH_SHORT).show();
        }
            
    }

    private void Init() {
        product_price_sale =findViewById(R.id.product_price_sale);
        product_price =findViewById(R.id.product_price);
        product_discount =findViewById(R.id.product_discount);
        product_name =findViewById(R.id.product_name);
        txt_product_rating =findViewById(R.id.txt_product_rating);
        rb_rating =findViewById(R.id.rb_rating);
        format = new DecimalFormat("###,###,###");
        ds_kich_thuoc = new ArrayList<>();
        sp_dao = new SanPhamDAO();
        kho_dao = new KhoDAO();
        rc_chon_size = findViewById(R.id.rc_chon_size);
    }

    private void setDetailProduct(int id) {
        try {
            sp = sp_dao.LayThongTinSanPhamTheoId(id);
            int gia_giam = (int)(sp.get_gia_ban().doubleValue()*(1-sp.get_phan_tram_khuyen_mai()));
            product_price_sale.setText(format.format(gia_giam).toString()+"đ");
            product_price.setText(format.format(sp.get_gia_ban()).toString()+"đ");
            String khuyen_mai = String.valueOf((int)(sp.get_phan_tram_khuyen_mai()*100));
            product_discount.setText("-"+khuyen_mai+"%");
            product_name.setText(sp.get_ten_san_pham());
            txt_product_rating.setText(sp.get_diem_xep_hang().toString()+" (2335) | 11.6kDa ban");
            rb_rating.setRating((float)sp.get_diem_xep_hang());
        }catch (Exception e){
            Toast.makeText(this, "Error get product by id", Toast.LENGTH_SHORT).show();
        }
    }
}