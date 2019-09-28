package com.example.user.toursbylocal;

import android.content.Intent;
import android.graphics.Typeface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class LoginRegistration extends AppCompatActivity {

    Button login_btn,reg_btn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_registration);

        login_btn = (Button) findViewById(R.id.login_btn);

        login_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(LoginRegistration.this, LoginActivity.class);
                startActivity(intent);
            }
        });


        reg_btn = (Button) findViewById(R.id.reg_btn);

        reg_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(LoginRegistration.this, RegistrationActivity.class);
                startActivity(intent);
            }
        });

        getSupportActionBar().hide();

    }
}
