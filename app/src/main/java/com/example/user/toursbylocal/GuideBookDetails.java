package com.example.user.toursbylocal;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class GuideBookDetails extends AppCompatActivity {

    Button btn_bookGuide;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_guide_book_details);

        btn_bookGuide = (Button) findViewById(R.id.btn_bookGuide);
        btn_bookGuide.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(GuideBookDetails.this, BookGuide.class);
                startActivity(intent);
            }
        });

    }
}
