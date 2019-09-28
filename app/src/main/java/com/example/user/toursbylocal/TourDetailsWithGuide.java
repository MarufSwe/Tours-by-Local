package com.example.user.toursbylocal;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
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

public class TourDetailsWithGuide extends AppCompatActivity {

    private Button btnSubmitBookNow, btnGuideDetails, btnMeetingPoint;
    private ImageView imgTourCoverImage;

    String guide_guide_id;

    String ture_district,tour_title,start_date,end_date,budget, tour_id;

    private TextView textTourDistrict,textTourTitle, textTourDetails, textWhatInclude, textWhaExtra
                     ,textTouristCapacity, textTouristEnrolled, textTourStartDate, textTourEndDate, textMeetingPoint,textTourBudget;

    private RequestQueue requestQueue;

    private static final String URL = "http://undrooping-till.000webhostapp.com/local_tourist/guide/tour_details.php";

    private static final String URLGuideDetails = "http://undrooping-till.000webhostapp.com/local_tourist/guide/guide_details.php";

    private StringRequest request;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tour_details_with_guide);

        getSupportActionBar().hide();

        textTourDistrict = (TextView) findViewById(R.id.textTourDistrict);
        textTourTitle = (TextView) findViewById(R.id.textTourTitle);
        textTourDetails = (TextView) findViewById(R.id.textTourDetails);
        textWhatInclude = (TextView) findViewById(R.id.textWhatInclude);
        textWhaExtra = (TextView) findViewById(R.id.textWhaExtra);
        textTouristCapacity = (TextView) findViewById(R.id.textTouristCapacity);
        textTouristEnrolled = (TextView) findViewById(R.id.textTouristEnrolled);
        textTourStartDate = (TextView) findViewById(R.id.textTourStartDate);
        textTourEndDate = (TextView) findViewById(R.id.textTourEndDate);
       // textMeetingPoint = (TextView) findViewById(R.id.textMeetingPoint);
        textTourBudget = (TextView) findViewById(R.id.textTourBudget);

        imgTourCoverImage = (ImageView) findViewById(R.id.imgTourCoverImage);

        requestQueue = Volley.newRequestQueue(this);


        //--------getExtra, Receive Data from API------------//
        Bundle extras = getIntent().getExtras();

        tour_id = extras.getString("tour_id");
        ture_district = extras.getString("ture_district");
        ture_district = extras.getString("ture_district");
        textTourDistrict.setText(ture_district);

         tour_title = extras.getString("tour_title");
        textTourTitle.setText(tour_title);

        String tour_area_details = extras.getString("tour_area_details");
        textTourDetails.setText(tour_area_details);

        String tour_facilities = extras.getString("tour_facilities");
        textWhatInclude.setText(tour_facilities);

        String transportation = extras.getString("transportation");
        textWhaExtra.setText(transportation);

        String tourist_limitation = extras.getString("tourist_limitation");
        textTouristCapacity.setText(tourist_limitation);

         start_date = extras.getString("start_date");
        textTourStartDate.setText(start_date);

         end_date = extras.getString("end_date");
        textTourEndDate.setText(end_date);

//        String meeting_point = extras.getString("meeting_point");
//        textMeetingPoint.setText(transportation);

         budget = extras.getString("budget");
        textTourBudget.setText(budget);

         guide_guide_id = extras.getString("guide_guide_id");



        btnSubmitBookNow = (Button) findViewById(R.id.btnSubmitBookNow);
        btnSubmitBookNow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                //----------PutExtra for Checkout Data-----------//
                Intent intent2 = new Intent(TourDetailsWithGuide.this, BookTour.class);

                intent2.putExtra("ture_district", ture_district);
                intent2.putExtra("tour_title", tour_title);
                intent2.putExtra("start_date", start_date);
                intent2.putExtra("end_date", end_date);
                intent2.putExtra("budget", budget);
                intent2.putExtra("tour_id", tour_id);


//                Log.d("tour_id","done");
                startActivity(intent2);
            }
        });


        //------------See Guide Details-------------//

        btnGuideDetails = (Button) findViewById(R.id.btnGuideDetails);
        btnGuideDetails.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                request = new StringRequest(Request.Method.POST, URLGuideDetails, new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            // Log.d("noakhailla","noa");
                            JSONObject jsonObjectGuide = new JSONObject(response);

                            if (jsonObjectGuide.getString("code").equalsIgnoreCase("200")){
                             //   Toast.makeText(getApplicationContext(), "SUCCESS " + jsonObjectGuide.getString("data"), Toast.LENGTH_SHORT).show();

                                JSONObject jsonObjectGuideDetails = (jsonObjectGuide.getJSONObject("data"));
                                String guideFirstName = jsonObjectGuideDetails.getString("first_name");
                                String guideLastName = jsonObjectGuideDetails.getString("last_name");
                                String guideAge = jsonObjectGuideDetails.getString("age");
                                String guideBirthDate = jsonObjectGuideDetails.getString("birth_date");
                                String guideEmail = jsonObjectGuideDetails.getString("email");
                                String guideSkype = jsonObjectGuideDetails.getString("skype_name");
                                String guideAddress = jsonObjectGuideDetails.getString("parmanent_address");
                                String guideCountry = jsonObjectGuideDetails.getString("country");

                                Log.d("tour_id","dooone");

                                //----------PutExtra-----------//
                                Intent intent = new Intent(TourDetailsWithGuide.this, GuideProfile.class);

                                intent.putExtra("guideFirstName", guideFirstName);
                                intent.putExtra("guideLastName", guideLastName);
                                intent.putExtra("guideAge", guideAge);
                                intent.putExtra("guideBirthDate", guideBirthDate);
                                intent.putExtra("guideEmail", guideEmail);
                                intent.putExtra("guideSkype", guideSkype);
                                intent.putExtra("guideAddress", guideAddress);
                                intent.putExtra("guideCountry", guideCountry);
                                Log.d("tour_id","do2oone");

                                startActivity(intent);



                            } else {

                                Toast.makeText(getApplicationContext(), "Error" + jsonObjectGuide.getString("result"), Toast.LENGTH_SHORT).show();
                            }

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }


                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {

                    }
                }) {
                    @Override
                    protected Map<String, String> getParams() throws AuthFailureError {
                        HashMap<String, String> hashMap = new HashMap<String, String>();
                        hashMap.put("id", guide_guide_id);

                        return hashMap;
                    }
                };
                requestQueue.add(request);
            }
        });


        //----------------Google Map, Meeting Point------------------//

        btnMeetingPoint = (Button) findViewById(R.id.btnMeetingPoint);
        btnMeetingPoint.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(android.content.Intent.ACTION_VIEW,
                Uri.parse("http://maps.google.com/maps?daddr=23.752802,90.377591"));
                startActivity(intent);
            }
        });
    }
    }

