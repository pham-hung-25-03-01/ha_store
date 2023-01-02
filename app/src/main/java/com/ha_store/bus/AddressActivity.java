package com.ha_store.bus;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatImageButton;

import android.content.Intent;
import android.os.Bundle;
import android.text.InputType;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.ha_store.R;
import com.ha_store.dao.KhachHangDAO;
import com.ha_store.dto.KhachHangDTO;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class AddressActivity extends AppCompatActivity {
    KhachHangDAO khachHangDAO;
    KhachHangDTO kh;
    AppCompatImageButton btn_back;
    TextView txt_so_nha;
    TextView txt_phuong_xa;
    TextView txt_quan_huyen;
    TextView txt_tinh_thanh;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_address);
        Init();
        khachHangDAO = new KhachHangDAO();
        Integer id = KhachHangDAO.khach_hang_hien_tai.get_id();
        kh = khachHangDAO.LayThongTinKhachHangTheoId(id);
        btn_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
                startActivity(new Intent(AddressActivity.this, SettingActivity.class));
            }
        });
        setInfo();
        txt_so_nha.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showDialog(view, "Số nhà",  1);
            }
        });
        txt_phuong_xa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showDialog(view, "Phường/xã",  2);
            }
        });
        txt_quan_huyen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showDialog(view, "Quận/huyện",  3);
            }
        });
        txt_tinh_thanh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showDialog(view, "Tỉnh/thành",  4);
            }
        });
    }
    public void showDialog(View view, String title, Integer option){
        View viewDialog = LayoutInflater.from(AddressActivity.this).inflate(R.layout.dialog_text_input, null);
        AlertDialog.Builder builder = new AlertDialog.Builder(AddressActivity.this);
        builder.setView(viewDialog);
        AlertDialog alertDialog = builder.create();
        alertDialog.show();
        ((TextView)viewDialog.findViewById(R.id.title)).setText(title);
        EditText txt_input = viewDialog.findViewById(R.id.txt_input);
        viewDialog.findViewById(R.id.btn_save).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String input = txt_input.getText().toString();
                if (input.isEmpty()){
                    Toast.makeText(getApplicationContext(),"Vui lòng nhập thông tin",Toast.LENGTH_LONG).show();
                }
                else{
                    switch (option){
                        case 1:{
                            kh.set_so_nha(input);
                            break;
                        }
                        case 2:{
                            if(input.matches("^([a-zA-Z]| )+$")){
                                kh.set_phuong_xa(input.trim());
                            }
                            else {
                                Toast.makeText(getApplicationContext(),"Chuỗi ký tự phải không chứa số",Toast.LENGTH_LONG).show();
                                return;
                            }
                            break;
                        }
                        case 3:{
                            if(input.matches("^([a-zA-Z]| )+$")){
                                kh.set_quan_huyen(input.trim());
                            }
                            else {
                                Toast.makeText(getApplicationContext(),"Chuỗi ký tự phải không chứa số",Toast.LENGTH_LONG).show();
                                return;
                            }
                            break;
                        }
                        case 4:{
                            if(input.matches("^([a-zA-Z]| )+$")){
                                kh.set_tinh_thanh(input.trim());
                            }
                            else {
                                Toast.makeText(getApplicationContext(),"Chuỗi ký tự phải không chứa số",Toast.LENGTH_LONG).show();
                                return;
                            }
                            break;
                        }
                    }
                    if(khachHangDAO.CapNhatThongTinKhachHang(kh)){
                        setInfo();
                        Toast.makeText(getApplicationContext(),"Cập nhật thành công",Toast.LENGTH_LONG).show();
                        alertDialog.dismiss();
                    }
                    else {
                        Toast.makeText(getApplicationContext(),"Lỗi",Toast.LENGTH_LONG).show();
                    }
                }
            }
        });
    }
    public void Init(){
        btn_back = findViewById(R.id.btn_back);
        txt_so_nha = findViewById(R.id.txt_so_nha);
        txt_phuong_xa = findViewById(R.id.txt_phuong_xa);
        txt_quan_huyen = findViewById(R.id.txt_quan_huyen);
        txt_tinh_thanh = findViewById(R.id.txt_tinh_thanh);
    }
    private void setInfo(){
        txt_so_nha.setText(kh.get_so_nha());
        txt_phuong_xa.setText(kh.get_phuong_xa());
        txt_quan_huyen.setText(kh.get_quan_huyen());
        txt_tinh_thanh.setText(kh.get_tinh_thanh());
    }
}