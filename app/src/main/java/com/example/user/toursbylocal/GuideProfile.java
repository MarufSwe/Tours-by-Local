package com.example.user.toursbylocal;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class GuideProfile extends AppCompatActivity {

    Button btnGuideDetails, btnBookGuide;
    ImageView guideImage;
    TextView guideFirstName, guideLastName, guideAge, guideBirthDate, guideEmail, guideSkype, guideAddress,guide_name, guideCountry;

    private RequestQueue requestQueue;

    private static final String URL = "http://undrooping-till.000webhostapp.com/local_tourist/guide/guide_details.php";

    private StringRequest request;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_guide_profile);
        getSupportActionBar().hide();


        btnBookGuide = findViewById(R.id.btnBookGuide);


        guideFirstName = (TextView) findViewById(R.id.guideFirstName);
        guideLastName = (TextView) findViewById(R.id.guideLastName);
        guideAge = (TextView) findViewById(R.id.guideAge);
        guideBirthDate = (TextView) findViewById(R.id.guideBirthDate);
        guideEmail = (TextView) findViewById(R.id.guideEmail);
        guideSkype = (TextView) findViewById(R.id.guideSkype);
        guideAddress = (TextView) findViewById(R.id.guideAddress);
        guideCountry = (TextView) findViewById(R.id.guideCountry);
        guide_name = (TextView) findViewById(R.id.guide_name);




        requestQueue = Volley.newRequestQueue(this);

        //--------getExtra------------//
        Bundle extrasGuideDetails = getIntent().getExtras();

        String first_name = extrasGuideDetails.getString("guideFirstName");
        guideFirstName.setText(first_name);

        String last_name = extrasGuideDetails.getString("guideLastName");
        guideLastName.setText(last_name);

        String age = extrasGuideDetails.getString("guideAge");
        guideAge.setText(age);

        String birth_date = extrasGuideDetails.getString("guideBirthDate");
        guideBirthDate.setText(birth_date);

        String email = extrasGuideDetails.getString("guideEmail");
        guideEmail.setText(email);

        String skype_name = extrasGuideDetails.getString("guideSkype");
        guideSkype.setText(skype_name);

        String district = extrasGuideDetails.getString("guideAddress");
        guideAddress.setText(district);

        String country = extrasGuideDetails.getString("guideCountry");
        guideCountry.setText(country);

        guide_name.setText(first_name + " " + last_name);


        btnBookGuide.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(),ConfirmGuideBook.class);
                startActivity(intent);
            }
        });

    }
}
