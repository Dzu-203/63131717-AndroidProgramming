package com.ntu.vu;

import android.content.Intent;
import android.os.Bundle;

import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;

import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import com.ntu.vu.databinding.ActivityLoginBinding;

public class Login extends AppCompatActivity {

    EditText username,password;
    Button btnLoginFB;
    String AdminUser;
    String AdminPass;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        addInit();
        btnLoginFB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String user = username.getText().toString();
                String pass = password.getText().toString();
                if (user.equals(AdminUser) && pass.equals(AdminPass)){
                    Intent intent = new Intent(Login.this, HomeActivity.class);
                    intent.putExtra("value",user);
                    startActivity(intent);
                }else {
                    Toast.makeText(Login.this,"Tên đăng nhập hoặc mật khẩu không đúng",Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private void addInit() {
        username = findViewById(R.id.usernameFB);
        password = findViewById(R.id.passwordFB);
        btnLoginFB = findViewById(R.id.btnLogin);
        AdminUser = "hoangvu";
        AdminPass = "123";
    }
}