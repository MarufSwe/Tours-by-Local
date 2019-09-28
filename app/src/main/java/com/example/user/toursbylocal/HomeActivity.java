package com.example.user.toursbylocal;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

public class HomeActivity extends AppCompatActivity {

    Button btnFindGuide;

    private RequestQueue requestQueue;

    private static final String URL = "http://undrooping-till.000webhostapp.com/local_tourist/guide/tour_details.php";

    private StringRequest request;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);


        requestQueue = Volley.newRequestQueue(this);


        btnFindGuide = (Button) findViewById(R.id.btnFindGuide);
        btnFindGuide.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


            }
        });

    }
}
