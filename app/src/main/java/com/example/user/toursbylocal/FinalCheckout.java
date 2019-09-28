package com.example.user.toursbylocal;

import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
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

public class FinalCheckout extends AppCompatActivity {

    private RequestQueue requestQueue;

    private StringRequest request;
    private String URL = "http://undrooping-till.000webhostapp.com/local_tourist/guide/tour_enroll.php";


    String ture_district, tour_id,tour_title, start_date, end_date, person;
    Button check_out_btn;
    TextView tourOverview, tourDistrict, tourHeadline, startTour, endTour,tourBudget,nope;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_final_checkout);

        tourDistrict = (TextView) findViewById(R.id.tourDistrict);
        tourHeadline = (TextView) findViewById(R.id.tourHeadline);
        startTour = (TextView) findViewById(R.id.startTour);
        endTour = (TextView) findViewById(R.id.endTour);
        tourBudget = (TextView) findViewById(R.id.tourBudget);
        nope = (TextView) findViewById(R.id.nope);

        requestQueue = Volley.newRequestQueue(this);


        //--------getExtra------------//
        Bundle extrasGuideDetails = getIntent().getExtras();

        ture_district = extrasGuideDetails.getString("ture_district");
        tourDistrict.setText(ture_district);

         tour_title = extrasGuideDetails.getString("tour_title");
        tourHeadline.setText(tour_title);

        tour_id = extrasGuideDetails.getString("tour_id");

         start_date = extrasGuideDetails.getString("start_date");
        startTour.setText("Start Date: "+start_date);

         end_date = extrasGuideDetails.getString("end_date");
        endTour.setText("End Date: "+end_date);

         person = extrasGuideDetails.getString("person");
        String budget = extrasGuideDetails.getString("budget");
        String policy = extrasGuideDetails.getString("policy");
        String phone_no = extrasGuideDetails.getString("phone_no");

         tour_id = extrasGuideDetails.getString("tour_id");

        int total_person = 0;
        if(person.length()>8){
            total_person = 0;
        } else {
            total_person =  Integer.parseInt(person);
        }


       tourBudget.setText("Budget: "+ Integer.toString(Integer.parseInt(budget) * total_person));



        check_out_btn = (Button) findViewById(R.id.check_out_btn);
        check_out_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                Intent intent = new Intent(FinalCheckout.this, PaymentSystem.class);
////                startActivity(intent);
                  String a  = getTravelerID();
                request = new StringRequest(Request.Method.POST, URL, new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            Log.d("noakhailla","noa");
                            JSONObject jsonObject = new JSONObject(response);

                            if (jsonObject.getString("code").equalsIgnoreCase("200")) {
                                //  Toast.makeText(getApplicationContext(), "SUCCESS " + jsonObject.getString("data"), Toast.LENGTH_SHORT).show();
                                Intent intent = new Intent(FinalCheckout.this, ConfirmTour.class);
                                startActivity(intent);

                                Log.d("noakhailla", "tut");
                            } else  if (jsonObject.getString("code").equalsIgnoreCase("201")){
                                Intent intent = new Intent(FinalCheckout.this, Fucntional.class);
                                Toast.makeText(getApplicationContext(), "You already Enroll the system", Toast.LENGTH_SHORT).show();
                                startActivity(intent);
                            } else {

                                // Log.d("noakhailla",jsonObject.getString("result").toString());
                                Toast.makeText(getApplicationContext(), "Error" + jsonObject.getString("result"), Toast.LENGTH_SHORT).show();
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
                        hashMap.put("ture_id", tour_id);
                        hashMap.put("traveler_id", getTravelerID());
                        hashMap.put("number_of_people", person.toString());

                        return hashMap;
                    }
                };

                requestQueue.add(request);




            }
        });


    }
    public String getTravelerID(){
        SharedPreferences sharedPreferences = getSharedPreferences("torid", getApplication().MODE_PRIVATE);

        String id = sharedPreferences.getString("id","");
        //  Toast.makeText(this, " : "+ phone, Toast.LENGTH_LONG).show();
        int a = 10;
        return  id;
    }
}
