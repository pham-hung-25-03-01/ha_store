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
import com.ha_store.adapter.OrderDetailAdapter;
import com.ha_store.dao.ChiTietDonDatHangDAO;
import com.ha_store.dao.DonDatHangDAO;
import com.ha_store.dao.KhuyenMaiDAO;
import com.ha_store.dto.ChiTietDonDatHangDTO;
import com.ha_store.dto.ChiTietThanhToanDTO;
import com.ha_store.dto.HinhThucThanhToanDTO;
import com.ha_store.dto.HoaDonDTO;

import java.math.BigDecimal;
import java.util.List;

public class OrderDetailActivity extends AppCompatActivity {

    RecyclerView r_chi_tiet_don_dat_hang;
    List<ChiTietDonDatHangDTO> ds_ctddh;
    AppCompatImageButton btn_back;
    ChiTietDonDatHangDAO chiTietDonDatHangDAO;
    DonDatHangDAO donDatHangDAO;
    TextView txt_hinh_thuc_thanh_toan;
    TextView txt_trang_thai;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_detail);
        chiTietDonDatHangDAO = new ChiTietDonDatHangDAO();
        donDatHangDAO = new DonDatHangDAO();
        Init();
        Intent intent = getIntent();
        ChiTietThanhToanDTO chiTietThanhToanDTO = donDatHangDAO.LayChiTietThanhToan(intent.getExtras().getInt("don_dat_hang_id"));
        HinhThucThanhToanDTO hinhThucThanhToanDTO = donDatHangDAO.LayHinhThucThanhToan(intent.getExtras().getInt("don_dat_hang_id"));
        HoaDonDTO hoaDonDTO = donDatHangDAO.LayHoaDon(chiTietThanhToanDTO.getHoa_don_id());
        txt_hinh_thuc_thanh_toan.setText(hinhThucThanhToanDTO.getTen_hinh_thuc_thanh_toan());
        String trang_thai = hoaDonDTO.getTrang_thai() == 2 ? "Đã thanh toán" : hoaDonDTO.getTrang_thai() == 0 ? "Đã hủy" : "Chưa thanh toán";
        txt_trang_thai.setText(trang_thai);
        btn_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
                Intent intent = new Intent(OrderDetailActivity.this, OrderActivity.class);
                intent.putExtra("trang_thai", -1);
                intent.putExtra("title", "Tất cả đơn hàng của bạn");
                startActivity(intent);
            }
        });
        setDSCTDDH(intent);
    }
    public void Init(){
        r_chi_tiet_don_dat_hang = findViewById(R.id.r_chi_tiet_don_dat_hang);
        btn_back = findViewById(R.id.btn_back);
        txt_hinh_thuc_thanh_toan = findViewById(R.id.txt_hinh_thuc_thanh_toan);
        txt_trang_thai = findViewById(R.id.txt_trang_thai);
    }
    private void setDSCTDDH(Intent intent) {
        ds_ctddh = chiTietDonDatHangDAO.LayDanhSachChiTietDonDatHang(intent.getExtras().getInt("don_dat_hang_id"));
        RecyclerView.LayoutManager layoutManager = new GridLayoutManager(this,1,GridLayoutManager.VERTICAL, false);
        OrderDetailAdapter adapter = new OrderDetailAdapter(ds_ctddh);
        r_chi_tiet_don_dat_hang.setAdapter(adapter);
        r_chi_tiet_don_dat_hang.setLayoutManager(layoutManager);
        r_chi_tiet_don_dat_hang.setHasFixedSize(true);
    }
}