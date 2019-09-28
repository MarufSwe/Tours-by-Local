package com.example.user.toursbylocal;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

public class BookTour extends AppCompatActivity {


    Button confirm_tour_btn;
    String ture_district,tour_title,start_date,end_date,budget,person, phoneNo,tour_id, policy = "";
    Spinner touristSelectNoOfPeople, select_cancellation_policy;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book_tour);


        //-------- Receive data, getExtra------------//

        Bundle extrasGuideDetails = getIntent().getExtras();

         ture_district = extrasGuideDetails.getString("ture_district");
         tour_title = extrasGuideDetails.getString("tour_title");
         start_date = extrasGuideDetails.getString("start_date");
         end_date = extrasGuideDetails.getString("end_date");
         budget = extrasGuideDetails.getString("budget");

        tour_id = extrasGuideDetails.getString("tour_id");

        //guide_name.setText(first_name + " " + last_name);



        confirm_tour_btn = (Button) findViewById(R.id.confirm_tour_btn);
        confirm_tour_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                //----------Send data, PutExtra for Checkout Data-----------//
                Intent intent1 = new Intent(BookTour.this, FinalCheckout.class);

                touristSelectNoOfPeople = (Spinner) findViewById(R.id.touristSelectNoOfPeople);
                select_cancellation_policy = (Spinner) findViewById(R.id.select_cancellation_policy);

                person = (String) touristSelectNoOfPeople.getSelectedItem();
                policy = (String) select_cancellation_policy.getSelectedItem();
                phoneNo = ((EditText) findViewById(R.id.touristPhoneNumber)).getText().toString();

                intent1.putExtra("ture_district", ture_district);

                Log.d("TAG", "nai "+ture_district);

                intent1.putExtra("tour_title", tour_title);
                intent1.putExtra("start_date", start_date);
                intent1.putExtra("end_date", end_date);
                intent1.putExtra("budget", budget);

                intent1.putExtra("person", person);
                intent1.putExtra("phone_no", phoneNo);
                intent1.putExtra("policy", policy);
                intent1.putExtra("tour_id", tour_id);

                startActivity(intent1);
                //--------------End PutExtra----------------//

//                Intent intent = new Intent(BookTour.this, FinalCheckout.class);
//                startActivity(intent);
            }
        });
    }
    }

