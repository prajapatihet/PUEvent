package com.techflix.puevents;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.InputType;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class AdminLoginActivity extends AppCompatActivity {

    EditText admin_user,admin_pass;
    ImageView password_icon;
    private boolean passwordShowing = false;
    TextView forgotPassword;
    Button signInBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_login);

        admin_user = findViewById(R.id.admin_user_id);
        admin_pass = findViewById(R.id.admin_password);
        password_icon = findViewById(R.id.passwordIcon);
        forgotPassword = findViewById(R.id.forgotPassword);
        signInBtn = findViewById(R.id.signInBtn);

        password_icon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(passwordShowing){
                    passwordShowing = false;

                    admin_pass.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);
                    password_icon.setImageResource(R.drawable.show);
                }else{
                    passwordShowing = true;

                    admin_pass.setInputType(InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD);
                    password_icon.setImageResource(R.drawable.hide);
                }
                admin_pass.setSelection(admin_pass.length());
            }
        });

        forgotPassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(AdminLoginActivity.this, "Contact your Main-Admin", Toast.LENGTH_SHORT).show();
            }
        });

        signInBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String username = admin_user.getText().toString();
                String password = admin_pass.getText().toString();

                if (TextUtils.isEmpty(username) && TextUtils.isEmpty(password)){
                    Toast.makeText(AdminLoginActivity.this, "Enter Username and Password", Toast.LENGTH_LONG).show();
                } else if (username.equals("admin") && password.equals("admin")) {
                    startActivity(new Intent(AdminLoginActivity.this,AdminManageEvents.class));
                    finish();
                } else {
                    Toast.makeText(AdminLoginActivity.this, "Error", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}