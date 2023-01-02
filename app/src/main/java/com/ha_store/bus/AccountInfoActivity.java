package com.ha_store.bus;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatImageButton;

import android.content.Intent;
import android.os.Bundle;
import android.text.InputType;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.ha_store.R;
import com.ha_store.dao.KhachHangDAO;
import com.ha_store.dto.KhachHangDTO;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class AccountInfoActivity extends AppCompatActivity {
    KhachHangDAO khachHangDAO;
    KhachHangDTO kh;
    AppCompatImageButton btn_back;
    TextView txt_ho_ten;
    TextView txt_id;
    TextView txt_mat_khau;
    TextView txt_so_dien_thoai;
    TextView txt_email;
    TextView txt_gioi_tinh;
    TextView txt_ngay_sinh;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_account_info);
        Init();
        khachHangDAO = new KhachHangDAO();
        Integer id = KhachHangDAO.khach_hang_hien_tai.get_id();
        kh = khachHangDAO.LayThongTinKhachHangTheoId(id);
        btn_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
                startActivity(new Intent(AccountInfoActivity.this, SettingActivity.class));
            }
        });
        setInfo();
        txt_ho_ten.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showDialog(view, "Họ tên", 1);
            }
        });
        txt_mat_khau.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showDialog(view, "Mật khẩu", 2);
            }
        });
        txt_so_dien_thoai.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showDialog(view, "Số điện thoại",  3);
            }
        });
        txt_gioi_tinh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showDialog(view, "Giới tính",  4);
            }
        });
        txt_ngay_sinh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showDialog(view, "Ngày sinh",  5);
            }
        });
    }
    public void Init(){
        btn_back = findViewById(R.id.btn_back);
        txt_ho_ten = findViewById(R.id.txt_ho_ten);
        txt_id = findViewById(R.id.txt_id);
        txt_mat_khau = findViewById(R.id.txt_mat_khau);
        txt_so_dien_thoai = findViewById(R.id.txt_so_dien_thoai);
        txt_email = findViewById(R.id.txt_email);
        txt_gioi_tinh = findViewById(R.id.txt_gioi_tinh);
        txt_ngay_sinh = findViewById(R.id.txt_ngay_sinh);
    }
    public void showDialog(View view, String title, Integer option){
        View viewDialog = LayoutInflater.from(AccountInfoActivity.this).inflate(R.layout.dialog_text_input, null);
        AlertDialog.Builder builder = new AlertDialog.Builder(AccountInfoActivity.this);
        builder.setView(viewDialog);
        AlertDialog alertDialog = builder.create();
        alertDialog.show();
        ((TextView)viewDialog.findViewById(R.id.title)).setText(title);
        EditText txt_input = viewDialog.findViewById(R.id.txt_input);
        if(option==2){
            txt_input.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);
        }
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
                            String[] temp = input.split(" ");
                            if(temp.length > 0){
                                if(temp.length == 1){
                                    kh.set_ten(temp[0]);
                                    kh.set_ho("");
                                }
                                else{
                                    String ho = temp[0];
                                    for(int i=1; i<temp.length-1; i++){
                                        ho+=" "+temp[i];
                                    }
                                    kh.set_ho(ho);
                                    kh.set_ten(temp[temp.length - 1]);
                                }
                            }
                            else{
                                Toast.makeText(getApplicationContext(),"Họ tên không được để trống",Toast.LENGTH_LONG).show();
                                return;
                            }
                            break;
                        }
                        case 2:{
                            if(input.length() >= 6){
                                kh.set_mat_khau(KhachHangDTO.generateHashedPass(input));
                            }
                            else {
                                Toast.makeText(getApplicationContext(),"Độ dài mật khẩu tối thiểu là 6",Toast.LENGTH_LONG).show();
                                return;
                            }
                            break;
                        }
                        case 3:{
                            if(input.matches("^0\\d{9}$")){
                                kh.set_so_dien_thoai(input);
                            }
                            else{
                                Toast.makeText(getApplicationContext(),"Số điện thoại sai định dạng",Toast.LENGTH_LONG).show();
                                return;
                            }
                            break;
                        }
                        case 4:{
                            if(input.equals("Nam") || input.equals("Nữ")){
                                Integer gioi_tinh = input.equals("Nam") ? 1 : 0;
                                kh.set_gioi_tinh(gioi_tinh);
                            }
                            else
                            {
                                Toast.makeText(getApplicationContext(),"Giới tính là \"Nam\" hoặc \"Nữ\"",Toast.LENGTH_LONG).show();
                                return;
                            }
                            break;
                        }
                        case 5:{
                            try {
                                Date ngay_sinh = new SimpleDateFormat("dd/MM/yyyy").parse(input);
                                kh.set_ngay_sinh(ngay_sinh);
                            } catch (ParseException e) {
                                e.printStackTrace();
                                Toast.makeText(getApplicationContext(),"Sai định dạng ngày: dd/mm/yyyy",Toast.LENGTH_LONG).show();
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
    private void setInfo(){
        txt_ho_ten.setText(kh.ho_ten());
        txt_id.setText(kh.get_id().toString());
        txt_so_dien_thoai.setText(kh.get_so_dien_thoai());
        txt_email.setText(kh.get_email());
        txt_gioi_tinh.setText(kh.get_gioi_tinh_chu());
        try {
            txt_ngay_sinh.setText(kh.get_ngay_sinh_chu());
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }
}