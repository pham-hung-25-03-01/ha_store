package com.ha_store.bus;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;
import androidx.appcompat.widget.AppCompatImageButton;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import com.ha_store.R;
import com.ha_store.adapter.CheckOutAdapter;
import com.ha_store.adapter.GioHangAdapter;
import com.ha_store.dao.ChiTietDonDatHangDAO;
import com.ha_store.dao.DonDatHangDAO;
import com.ha_store.dao.GioHangDAO;
import com.ha_store.dao.HoaDonDAO;
import com.ha_store.dao.KhachHangDAO;
import com.ha_store.dao.SanPhamDAO;
import com.ha_store.dto.ChiTietDonDatHangDTO;
import com.ha_store.dto.ChiTietThanhToanDTO;
import com.ha_store.dto.DonDatHangDTO;
import com.ha_store.dto.GioHangDTO;
import com.ha_store.dto.HoaDonDTO;
import com.ha_store.dto.KhachHangDTO;
import com.ha_store.dto.SanPhamDTO;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class CheckOutActivity extends AppCompatActivity {
    TextView txt_ho_ten,txt_sdt,txt_dia_chi,txt_tong_tien_1,txt_tong_tien_2,txt_so_luong;
    AppCompatImageButton btn_back;
    AppCompatButton btn_dat_hang;
    RadioButton btn_momo,btn_truc_tiep;
    RecyclerView rc_check_out;
    EditText txt_ma_km;
    GioHangDAO gh_dao;
    SanPhamDAO sp_dao;
    KhachHangDAO kh_dao;
    KhachHangDTO kh;
    List<GioHangDTO> ds_gh;
    DonDatHangDAO ddh_dao;
    DonDatHangDTO ddh;
    ChiTietDonDatHangDAO ctdh_dao;
    HoaDonDTO hoa_don;
    HoaDonDAO hoa_don_dao;


    int hinh_thuc_thanh_toan = 1;
    int kh_id;
    int tong = 0;
    DecimalFormat format;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_check_out);
        Init();
        setThongTinGiaoHang();
        setDSGioHang();
        setTongTien();
        setHinhThucThanhToan();
        btn_dat_hang.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setDatHang();
            }
        });
        btn_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
                startActivity(new Intent(CheckOutActivity.this, CartActivity.class));
            }
        });
    }
     private void setDatHang() {
        try {
            String ma_km = txt_ma_km.getText().toString();
            BigDecimal ngay_giao = convert_date_to_big_decimal(new Date(new Date().getTime() + (1000*60*60*24*7)));
            BigDecimal ngay_tao = convert_date_to_big_decimal(new Date());
            ddh = new DonDatHangDTO(kh_id,ma_km,ngay_giao,kh.get_so_nha(),kh.get_phuong_xa(),kh.get_quan_huyen(),kh.get_tinh_thanh(),BigDecimal.valueOf(tong),1,ngay_tao);
            ddh_dao.ThemDonDatHang(ddh);
            ddh = ddh_dao.LayDonDatHangMoi();
            List<GioHangDTO> ls_gh = gh_dao.LayGioHangTheoKhachHangId(kh_id);
            for (GioHangDTO item : ls_gh ) {
                SanPhamDTO sp = sp_dao.LayThongTinSanPhamTheoId(item.getSan_pham_id());
                ChiTietDonDatHangDTO ct = new ChiTietDonDatHangDTO(item.getSan_pham_id(),item.getKich_thuoc_id(),ddh.getId(),sp.get_gia_ban(),sp.get_phan_tram_khuyen_mai(),item.getSo_luong());
                ctdh_dao.ThemChiTietDonDatHang(ct);
                gh_dao.XoaSanPhamTrongGioHang(item.getKhach_hang_id(),item.getSan_pham_id(),item.getKich_thuoc_id());
            }
            hoa_don = new HoaDonDTO(1, 1, ngay_tao);
            hoa_don_dao.ThemHoaDon(hoa_don);
            hoa_don = hoa_don_dao.LayHoaDonMoi();
            ChiTietThanhToanDTO chiTietThanhToanDTO = new ChiTietThanhToanDTO(hinh_thuc_thanh_toan,hoa_don.getId(),ddh.getId(),BigDecimal.valueOf(0));
            ddh_dao.ThemCTTT(chiTietThanhToanDTO);
            Toast.makeText(this, "Dat hang thanh cong", Toast.LENGTH_SHORT).show();

            finish();
            Intent intent = new Intent(CheckOutActivity.this, OrderActivity.class);
            intent.putExtra("trang_thai", -1);
            intent.putExtra("title", "Tất cả đơn hàng của bạn");
            startActivity(intent);
        }catch (Exception e){
            Toast.makeText(this, "Dat hang that bai", Toast.LENGTH_SHORT).show();
        }

    }
    private BigDecimal convert_date_to_big_decimal(Date date){
        SimpleDateFormat b_format = new SimpleDateFormat("ddMMyyyy");
        return new BigDecimal(b_format.format(date));
    }
    private void setHinhThucThanhToan() {
        btn_momo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                btn_momo.setSelected(true);
                btn_truc_tiep.setSelected(false);
                hinh_thuc_thanh_toan = 2;
            }
        });
        btn_truc_tiep.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                btn_truc_tiep.setSelected(true);
                btn_momo.setSelected(false);
                hinh_thuc_thanh_toan = 1;
            }
        });
    }

    private void setTongTien() {

        int sl = 0;
        for (GioHangDTO i: ds_gh) {
            SanPhamDTO sp = sp_dao.LayThongTinSanPhamTheoId(i.getSan_pham_id());
            tong+= (i.getSo_luong()*(int)(sp.get_gia_ban().doubleValue()*(1-sp.get_phan_tram_khuyen_mai())));
            sl++;
        }
        txt_tong_tien_1.setText(format.format(tong).toString()+"đ");
        txt_tong_tien_2.setText(format.format(tong).toString()+"đ");
        txt_so_luong.setText(String.valueOf(sl)+" item, Subtotal: ");

    }
    private void setThongTinGiaoHang() {
        kh = kh_dao.LayThongTinKhachHangTheoId(KhachHangDAO.khach_hang_hien_tai.get_id());

        txt_ho_ten.setText(kh.get_ho()+" "+kh.get_ten());
        String dia_chi = kh.get_so_nha()+", "+kh.get_phuong_xa()+", "+kh.get_quan_huyen()+", "+kh.get_tinh_thanh();
        txt_dia_chi.setText(dia_chi);
        txt_sdt.setText(kh.get_so_dien_thoai());
    }

    private void setDSGioHang() {
        ds_gh = gh_dao.LayGioHangTheoKhachHangId(kh_id);
        RecyclerView.LayoutManager layoutManager = new GridLayoutManager(this,1,GridLayoutManager.VERTICAL, false);
        CheckOutAdapter checkOutAdapter = new CheckOutAdapter(ds_gh);
        rc_check_out.setAdapter(checkOutAdapter);
        rc_check_out.setLayoutManager(layoutManager);
        rc_check_out.setHasFixedSize(true);
    }

    private void Init() {
        btn_back = findViewById(R.id.btn_back);
        rc_check_out = findViewById(R.id.rc_check_out);
        txt_tong_tien_1 = findViewById(R.id.txt_tong_tien_1);
        txt_tong_tien_2 = findViewById(R.id.txt_tong_tien_2);
        gh_dao = new GioHangDAO();
        ds_gh = new ArrayList<>();
        kh_dao = new KhachHangDAO();
        sp_dao = new SanPhamDAO();
        ddh_dao = new DonDatHangDAO();
        hoa_don_dao = new HoaDonDAO();
        ctdh_dao = new ChiTietDonDatHangDAO();
        kh_id = KhachHangDAO.khach_hang_hien_tai.get_id();
        txt_ho_ten = findViewById(R.id.txt_ho_ten);
        txt_sdt = findViewById(R.id.txt_sdt);
        txt_dia_chi = findViewById(R.id.txt_dia_chi);
        txt_so_luong = findViewById(R.id.txt_so_luong);
        btn_momo = findViewById(R.id.btn_momo);
        btn_truc_tiep = findViewById(R.id.btn_truc_tiep);
        btn_dat_hang = findViewById(R.id.btn_dat_hang);
        format = new DecimalFormat("###,###,###");
        btn_truc_tiep.setSelected(true);
        txt_ma_km = findViewById(R.id.txt_ma_km);
    }
}