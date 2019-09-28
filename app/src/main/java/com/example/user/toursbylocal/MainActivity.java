package com.example.user.toursbylocal;

import android.content.Intent;
import android.graphics.Typeface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    Button toursByLocal_btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

               toursByLocal_btn = (Button) findViewById(R.id.toursByLocal_btn);
        toursByLocal_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, LoginRegistration.class);
                startActivity(intent);
            }
        });


        Thread timer=new Thread() {
            Intent myIntent = new Intent(MainActivity.this,LoginRegistration.class);
            public void run() {

                try {
                    sleep(2000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } finally {
                    startActivity(myIntent);
                    finish();
                }
            }};
        timer.start();
    }
    }
