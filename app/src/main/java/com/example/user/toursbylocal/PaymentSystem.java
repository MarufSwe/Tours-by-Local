package com.example.user.toursbylocal;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class PaymentSystem extends AppCompatActivity {

    Button submit_deal;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_payment_system);


        submit_deal = (Button) findViewById(R.id.submit_deal);
        submit_deal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(PaymentSystem.this, ConfirmTour.class);
                startActivity(intent);
            }
        });
    }
}
