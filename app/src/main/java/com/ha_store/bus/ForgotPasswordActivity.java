package com.ha_store.bus;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputEditText;
import com.ha_store.R;
import com.ha_store.dao.KhachHangDAO;
import com.ha_store.dao.PasswordGenerator;
import com.ha_store.dao.SendEmail;
import com.ha_store.dto.KhachHangDTO;

public class ForgotPasswordActivity extends AppCompatActivity {
    TextView txt_back;
    AppCompatButton btn_gui_email;
    KhachHangDAO khachHangDAO;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgot_password);
        khachHangDAO = new KhachHangDAO();
        txt_back = (TextView)findViewById(R.id.txt_back);
        txt_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        btn_gui_email = (AppCompatButton)findViewById(R.id.btn_gui_email);
        btn_gui_email.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                TextInputEditText txt_email = (TextInputEditText)findViewById(R.id.txt_email);
                KhachHangDTO kh = khachHangDAO.LayThongTinKhachHangTheoEmail(txt_email.getText().toString());
                if(kh == null){
                    Toast.makeText(getApplicationContext(),"Không tìm thấy tài khoản này!",Toast.LENGTH_LONG).show();
                }
                else {
//                    PasswordGenerator passwordGenerator = new PasswordGenerator.PasswordGeneratorBuilder()
//                            .useDigits(true)
//                            .useLower(true)
//                            .useUpper(true)
//                            .build();
//                    String password = passwordGenerator.generate(8);
                    String password = "123456";
                    kh.set_mat_khau(KhachHangDTO.generateHashedPass(password));
                    if(khachHangDAO.CapNhatThongTinKhachHang(kh)){
//                        SendEmail sendEmail = new SendEmail();
//                        sendEmail.execute(kh.get_email(), "Mật khẩu mới của bạn là: " + password);
                        finish();
                        Toast.makeText(getApplicationContext(),"Bạn vui lòng kiểm tra email để nhận mật khẩu mới!",Toast.LENGTH_LONG).show();
                    }else {
                        Toast.makeText(getApplicationContext(),"Lỗi!",Toast.LENGTH_LONG).show();
                    }
                }
            }
        });
    }
}