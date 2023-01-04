package com.ha_store.bus;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager2.widget.ViewPager2;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import com.ha_store.R;
import com.ha_store.adapter.AnhSPAdapter;
import com.ha_store.adapter.ProductSizeAdapter;
import com.ha_store.adapter.BinhLuanAdapter;
import com.ha_store.dao.BinhLuanDAO;
import com.ha_store.dao.GioHangDAO;
import com.ha_store.dao.KhachHangDAO;
import com.ha_store.dao.KhoDAO;
import com.ha_store.dao.SanPhamDAO;
import com.ha_store.dao.YeuThichDAO;
import com.ha_store.dao.XepHangDAO;
import com.ha_store.dto.AnhSanPhamDTO;
import com.ha_store.dto.BinhLuanDTO;
import com.ha_store.dto.GioHangDTO;
import com.ha_store.dto.KhachHangDTO;
import com.ha_store.dto.KichThuocDTO;
import com.ha_store.dto.SanPhamDTO;
import com.ha_store.dto.YeuThichDTO;
import com.ha_store.dto.XepHangDTO;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

public class DetailActivity extends AppCompatActivity {
    public static TextView txt_so_luong,txt_size;
    AppCompatButton btn_back;
    public static int so_luong = 1, id_kich_thuoc = 0;
    int sp_id;
    int so_luong_dat = 1;
    int tong_gia = 0,gia_giam = 0;
    int kh_id;
    TextView product_price_sale,product_price,product_discount,product_name,txt_product_rating,cart_item_qty,txt_tong_gia,txt_bl;
    AppCompatButton btn_sub,btn_plus,btn_them_gio_hang,btn_them_danh_gia;
    ViewPager2 v2_slider_img;
    FrameLayout l_cart;
    ImageView btn_cart;
    RatingBar rb_rating;
    SanPhamDAO sp_dao;
    KhoDAO kho_dao;
    GioHangDAO gh_dao;
    GioHangDTO item_gh;
    SanPhamDTO sp;
    DecimalFormat format;
    List<KichThuocDTO> ds_kich_thuoc;
    List<AnhSanPhamDTO> ds_anh_sp;
    AppCompatButton btn_yeu_thich;
    YeuThichDAO yeuThichDAO;
    RecyclerView rc_chon_size,rc_rating;
    List<BinhLuanDTO> ds_binh_luan;
    BinhLuanDAO bl_dao;
    XepHangDAO xh_dao;
    XepHangDTO xep_hang;
    RatingBar rb_rate,ratingBar;
    TextView txt_so_diem,txt_so_diem_1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        Intent intent = getIntent();
        sp_id = intent.getExtras().getInt("id");
        Toast.makeText(getApplicationContext(),"id"+sp_id,Toast.LENGTH_LONG).show();
        Init();
        setDSAnh(sp_id);
        setDetailProduct(sp_id);
        setListSize(sp_id);
        setTongGia();
        btn_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
                Intent intent = new Intent(DetailActivity.this, ShoppingActivity.class);
                intent.putExtra("loai_san_pham_id", -1);
                intent.putExtra("key_word", "");
                startActivity(intent);
            }
        });
        btn_cart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(DetailActivity.this, CartActivity.class));
            }
        });
        btn_sub.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                btn_plus.setEnabled(true);
                if(so_luong_dat < 2){
                    btn_sub.setEnabled(false);
                }else{
                    so_luong_dat--;
                    cart_item_qty.setText(String.valueOf(so_luong_dat));
                }
                setTongGia();
            }
        });
        btn_plus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                btn_sub.setEnabled(true);
                if(so_luong_dat == so_luong){
                    btn_plus.setEnabled(false);
                }else{
                    so_luong_dat++;
                    cart_item_qty.setText(String.valueOf(so_luong_dat));
                }
                setTongGia();
            }
        });
        btn_them_gio_hang.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(KhachHangDAO.khach_hang_hien_tai.get_da_dang_nhap()){
                    themVaoGioHang();
                }else{
                    hienDiaLogChuaDangNhap();
                }
            }
        });
        setYeuThich();
        btn_yeu_thich.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                YeuThichDTO yt = yeuThichDAO.LayYeuThich(KhachHangDAO.khach_hang_hien_tai.get_id(), sp.get_id());
                if(yt == null){
                    yeuThichDAO.ThemYeuThich(new YeuThichDTO(KhachHangDAO.khach_hang_hien_tai.get_id(), sp.get_id(), 1));
                    btn_yeu_thich.setBackgroundResource(R.drawable.ic_heart_fill);
                    Toast.makeText(getApplicationContext(),"Đã thêm yêu thích",Toast.LENGTH_LONG).show();
                }
                else{
                    yeuThichDAO.XoaYeuThich(KhachHangDAO.khach_hang_hien_tai.get_id(), sp.get_id());
                    btn_yeu_thich.setBackgroundResource(R.drawable.ic_heart);
                    Toast.makeText(getApplicationContext(),"Đã hủy yêu thích",Toast.LENGTH_LONG).show();
                }
            }
        });
        txt_bl.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(DetailActivity.this,
                        BinhLuanActivity.class);
                intent.putExtra("id", sp_id);
                v.getContext().startActivity(intent);
            }
        });
        setListBinhLuan();
        btn_them_danh_gia.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(KhachHangDAO.khach_hang_hien_tai.get_da_dang_nhap()){
                    ThemDanhGia();
                }else{
                    hienDiaLogChuaDangNhap();
                }
            }
        });
        setDanhGiaCuaBan();
    }

    private void hienDiaLogChuaDangNhap() {
        AlertDialog.Builder builder = new AlertDialog.Builder(DetailActivity.this);
        builder.setCancelable(true);
        builder.setTitle("Thông báo");
        builder.setMessage("Đăng nhập để tiếp tục");
        builder.setPositiveButton("Đăng nhập",
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        startActivity(new Intent(DetailActivity.this, LoginActivity.class));
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

    private void themVaoGioHang() {
        boolean item_da_ton_tai =false;
        boolean da_them_gh = false;
        item_gh = new GioHangDTO(kh_id,sp_id,id_kich_thuoc,so_luong_dat);
        item_da_ton_tai = gh_dao.KiemTraTonTaiTrongGioHang(item_gh.getKhach_hang_id(),item_gh.getSan_pham_id(),item_gh.getKich_thuoc_id());

        if(id_kich_thuoc == 0){
                Toast.makeText(this, "Chưa chọn size sản phẩm", Toast.LENGTH_SHORT).show();
        }
        else{
            if(kho_dao.LaySoLuongSanPham(item_gh.getSan_pham_id(), item_gh.getKich_thuoc_id()) < item_gh.getSo_luong()){
                Toast.makeText(this, "Số lượng sản phẩm trong kho không đủ", Toast.LENGTH_SHORT).show();
            }
            else{
                if(item_da_ton_tai){
                    capNhatSLGioHang(item_gh);
                    Toast.makeText(this, "Đã thêm vào giỏ hàng", Toast.LENGTH_SHORT).show();
                    setDemSoLuongGH();
                }else{
                    da_them_gh =  gh_dao.ThemSanPhamVaoGioHang(item_gh);
                    if (da_them_gh) {
                        Toast.makeText(this, "Đã thêm vào giỏ hàng", Toast.LENGTH_SHORT).show();
                        setDemSoLuongGH();
                    }else
                        Toast.makeText(this, "Lỗi", Toast.LENGTH_SHORT).show();
                }
            }
        }
    }

    private void capNhatSLGioHang(GioHangDTO item_gh) {
        boolean da_tang_sl = false;
        int so_luong_da_dat = 0;
        so_luong_da_dat = gh_dao.LaySoLuongMoiItemTrongGioHang(item_gh.getKhach_hang_id(),item_gh.getSan_pham_id(),item_gh.getKich_thuoc_id());
        item_gh.setSo_luong(item_gh.getSo_luong()+so_luong_da_dat);
        da_tang_sl = gh_dao.CapNhatSLTrongGioHang(item_gh.getKhach_hang_id(),item_gh.getSan_pham_id(),item_gh.getKich_thuoc_id(),item_gh.getSo_luong());
        if (da_tang_sl)
            Toast.makeText(this, "Đã thêm vào giỏ hàng", Toast.LENGTH_SHORT).show();
        else
            Toast.makeText(this, "Lỗi", Toast.LENGTH_SHORT).show();
    }

    private void setDemSoLuongGH() {
        int so_luong_gh=0;
        so_luong_gh = gh_dao.LaySoLuongSanPhamTrongGioHang(kh_id);
        View notify_cart = LayoutInflater.from(this).inflate(R.layout.cart_bage,l_cart,false);
        TextView txt_cart_badge = notify_cart.findViewById(R.id.top_cart_badge);
        txt_cart_badge.setText(String.valueOf(so_luong_gh));
        l_cart.addView(notify_cart);
        notify_cart.setPadding(40,0,0,0);
    }

    private void setTongGia() {
        tong_gia = so_luong_dat * gia_giam;
        txt_tong_gia.setText(String.valueOf(format.format(tong_gia))+"đ ("+so_luong_dat+")");
    }

    private void setListSize(int id) {
        try {
            ds_kich_thuoc = kho_dao.LayDanhSachKichThuocTheoSanPhamId(id);
            //  Toast.makeText(this, ds_sp_ban_chay.get(0).get_ten_san_pham()+"du lieu", Toast.LENGTH_SHORT).show();
            RecyclerView.LayoutManager layoutManager = new GridLayoutManager(this,2,GridLayoutManager.HORIZONTAL, false);
            ProductSizeAdapter productSizeAdapter = new ProductSizeAdapter(id,ds_kich_thuoc);
            rc_chon_size.setAdapter(productSizeAdapter);
            rc_chon_size.setLayoutManager(layoutManager);
            rc_chon_size.setHasFixedSize(true);
        }catch (Exception e){
            Toast.makeText(this, "Error get product by id", Toast.LENGTH_SHORT).show();
        }
            
    }

    private void Init() {
        btn_cart = findViewById(R.id.btn_cart);
        l_cart = findViewById(R.id.l_cart);
        v2_slider_img = findViewById(R.id.v2_slider_img);
        product_price_sale =findViewById(R.id.product_price_sale);
        txt_so_luong =findViewById(R.id.txt_so_luong);
        txt_size =findViewById(R.id.txt_size);
        btn_back = findViewById(R.id.btn_back);
        product_price =findViewById(R.id.product_price);
        product_discount =findViewById(R.id.product_discount);
        product_name =findViewById(R.id.product_name);
        txt_product_rating =findViewById(R.id.txt_product_rating);
        btn_sub =findViewById(R.id.btn_sub);
        btn_plus =findViewById(R.id.btn_plus);
        btn_them_gio_hang = findViewById(R.id.btn_them_gio_hang);
        cart_item_qty =findViewById(R.id.cart_item_qty);
        txt_tong_gia =findViewById(R.id.txt_tong_gia);
        rb_rating =findViewById(R.id.rb_rating);
        format = new DecimalFormat("###,###,###");
        ds_kich_thuoc = new ArrayList<>();
        sp_dao = new SanPhamDAO();
        kho_dao = new KhoDAO();
        gh_dao = new GioHangDAO();
        rc_chon_size = findViewById(R.id.rc_chon_size);
        kh_id = KhachHangDAO.khach_hang_hien_tai.get_id();
        setDemSoLuongGH();
        btn_yeu_thich = findViewById(R.id.btn_yeu_thich);
        yeuThichDAO = new YeuThichDAO();
        txt_bl =findViewById(R.id.txt_bl);
        rc_rating =findViewById(R.id.rc_rating);
        ds_binh_luan =new ArrayList<>();
        bl_dao = new BinhLuanDAO();
        btn_them_danh_gia = findViewById(R.id.btn_them_danh_gia);
        xh_dao = new XepHangDAO();
        rb_rate = findViewById(R.id.rb_rate);
        ratingBar = findViewById(R.id.ratingBar);
        txt_so_diem = findViewById(R.id.txt_so_diem);
        txt_so_diem_1 =findViewById(R.id.txt_so_diem_1);
        ratingBar.setVisibility(View.GONE);
        rb_rate.setStepSize(1);
    }

    private void setDetailProduct(int id) {
        try {
            sp = sp_dao.LayThongTinSanPhamTheoId(id);
            gia_giam = (int)(sp.get_gia_ban().doubleValue()*(1-sp.get_phan_tram_khuyen_mai()));
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

    private void setDSAnh(int id) {
        ds_anh_sp = sp_dao.LayDanhSachAnhSanPham(id);
        AnhSPAdapter anhSPAdapter = new AnhSPAdapter(this,ds_anh_sp);
        v2_slider_img.setAdapter(anhSPAdapter);
    }
    private void setYeuThich(){
        YeuThichDTO yt = yeuThichDAO.LayYeuThich(KhachHangDAO.khach_hang_hien_tai.get_id(), sp.get_id());
        if(yt == null){
            btn_yeu_thich.setBackgroundResource(R.drawable.ic_heart);
        }
        else{
            btn_yeu_thich.setBackgroundResource(R.drawable.ic_heart_fill);
        }
    }
    private void setListBinhLuan() {
        ds_binh_luan = bl_dao.LayDanhSachBinhLuanTheoSanPhamId(sp_id);
        RecyclerView.LayoutManager layoutManager = new GridLayoutManager(this,1,GridLayoutManager.VERTICAL, false);
        BinhLuanAdapter spBanChayAdapter = new BinhLuanAdapter(ds_binh_luan);
        rc_rating.setAdapter(spBanChayAdapter);
        rc_rating.setLayoutManager(layoutManager);
        rc_rating.setHasFixedSize(true);
    }
    private void ThemDanhGia() {
        int so_diem = (int)rb_rate.getRating();
        if(so_diem == 0){
            Toast.makeText(this, "Chưa chọn số điểm", Toast.LENGTH_SHORT).show();
        }else{
            xep_hang = new XepHangDTO(sp_id, KhachHangDAO.khach_hang_hien_tai.get_id(),so_diem);
            boolean da_danh_gia = false;
            boolean da_cap_nhat_danh_gia =false;
            XepHangDTO xp = null;
            xp = xh_dao.LayXepHangTheoKhachHangId(kh_id,sp_id);
            if(xp==null){
                da_danh_gia = xh_dao.ThemXepHang(xep_hang);
                if(da_danh_gia){
                    Toast.makeText(this, "Da danh gia san pham", Toast.LENGTH_SHORT).show();
                    setDanhGiaCuaBan();
                }else
                    Toast.makeText(this, "Loi", Toast.LENGTH_SHORT).show();
            }else{
                da_cap_nhat_danh_gia = xh_dao.CapNhatXepHang(xep_hang);
                if(da_cap_nhat_danh_gia){
                    Toast.makeText(this, "Da cap nhat danh gia san pham", Toast.LENGTH_SHORT).show();
                    setDanhGiaCuaBan();
                }else
                    Toast.makeText(this, "Loi", Toast.LENGTH_SHORT).show();
            }
        }
    }

    private void setDanhGiaCuaBan() {
        if(KhachHangDAO.khach_hang_hien_tai.get_da_dang_nhap()){
            int so_diem = 5;
            XepHangDTO xp = null;
            xp = xh_dao.LayXepHangTheoKhachHangId(kh_id,sp_id);
            if(xp!=null){
                so_diem = xp.getDiem_xep_hang();
                rb_rate.setRating(so_diem);
                ratingBar.setRating(so_diem);
                txt_so_diem.setText(String.valueOf(so_diem));
                ratingBar.setVisibility(View.VISIBLE);
                txt_so_diem.setVisibility(View.VISIBLE);
                txt_so_diem_1.setVisibility(View.VISIBLE);
            }else{
                txt_so_diem.setText("Chưa đánh giá");
                txt_so_diem.setVisibility(View.VISIBLE);
                txt_so_diem_1.setVisibility(View.VISIBLE);
            }
        }else{
            txt_so_diem_1.setVisibility(View.GONE);
            ratingBar.setVisibility(View.GONE);
            txt_so_diem.setVisibility(View.GONE);
        }
    }
}