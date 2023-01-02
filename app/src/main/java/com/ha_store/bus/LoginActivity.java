package com.ha_store.bus;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;
import androidx.core.content.ContextCompat;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.auth.api.Auth;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.auth.api.signin.GoogleSignInResult;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.SignInButton;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.material.textfield.TextInputEditText;
import com.ha_store.R;
import com.ha_store.dao.KhachHangDAO;

public class LoginActivity extends AppCompatActivity implements GoogleApiClient.OnConnectionFailedListener {

    KhachHangDAO kh_dao;
    AppCompatButton signInButtonByHand;
    SignInButton signInButton;
    private GoogleApiClient googleApiClient;
    TextView textView;
    private static final int RC_SIGN_IN = 1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        kh_dao = new KhachHangDAO();
        GoogleSignInOptions gso =  new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestEmail()
                .build();
        googleApiClient=new GoogleApiClient.Builder(this)
                .enableAutoManage(this,this)
                .addApi(Auth.GOOGLE_SIGN_IN_API,gso)
                .build();



        signInButton=(SignInButton)findViewById(R.id.sign_in_button);
        signInButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = Auth.GoogleSignInApi.getSignInIntent(googleApiClient);
                startActivityForResult(intent,RC_SIGN_IN);
            }
        });
        TextView signInButton_TextView = (TextView) signInButton.getChildAt(0);
        signInButton_TextView.setText("Đăng nhập bằng Google");
        signInButton_TextView.setTextSize(21);
        signInButton_TextView.setPadding(0,22,0,22);
        signInButtonByHand = (AppCompatButton)findViewById(R.id.btn_dang_nhap);
        signInButtonByHand.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View view) {
                TextInputEditText txt_email = findViewById(R.id.txt_email);
                EditText txt_password = findViewById(R.id.txt_password);
                Boolean is_login = kh_dao.dang_nhap(txt_email.getText().toString(), txt_password.getText().toString());
                if (is_login){
                    gotoProfile();
                    Toast.makeText(getApplicationContext(), "Đăng nhập thành công!", Toast.LENGTH_LONG).show();
                }
                else{
                    Toast.makeText(getApplicationContext(), "Sai tài khoản hoặc mật khẩu!", Toast.LENGTH_LONG).show();
                }
            }
        });

    }


    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode==RC_SIGN_IN){
            GoogleSignInResult result = Auth.GoogleSignInApi.getSignInResultFromIntent(data);
            handleSignInResult(result);
        }
    }
    private void handleSignInResult(GoogleSignInResult result){
        if(result.isSuccess()){
            gotoProfile();
        }else{
            Toast.makeText(getApplicationContext(),"Sign in cancel",Toast.LENGTH_LONG).show();
        }
    }
    private void gotoProfile(){
        Intent intent=new Intent(LoginActivity.this,ProfileActivity.class);
        startActivity(intent);
    }
}