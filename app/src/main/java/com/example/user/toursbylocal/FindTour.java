package com.example.user.toursbylocal;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
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

public class FindTour extends AppCompatActivity {

    Button btnTourDetails;

    private RequestQueue requestQueue;

    private static final String URL = "http://undrooping-till.000webhostapp.com/local_tourist/guide/tour_details.php";

    private StringRequest request;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_find_tour);

        requestQueue = Volley.newRequestQueue(this);


        btnTourDetails = (Button) findViewById(R.id.btnTourDetails);
        btnTourDetails.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                request = new StringRequest(Request.Method.POST, URL, new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            // Log.d("noakhailla","noa");
                            JSONObject jsonObject = new JSONObject(response);

                            if (jsonObject.getString("code").equalsIgnoreCase("200")){
                             //   Toast.makeText(getApplicationContext(), "SUCCESS " + jsonObject.getString("data"), Toast.LENGTH_SHORT).show();
                                JSONObject jsonObject2 = (jsonObject.getJSONObject("data"));

                                 String tour_id = jsonObject2.getString("tour_id");
                                String ture_district = jsonObject2.getString("ture_district");
                                String tour_title = jsonObject2.getString("tour_title");
                                String tour_area_details = jsonObject2.getString("tour_area_details");
                                String tour_facilities = jsonObject2.getString("tour_facilities");
                                String transportation = jsonObject2.getString("transportation");
                                String tourist_limitation = jsonObject2.getString("tourist_limitation");
                                String start_date = jsonObject2.getString("start_date");
                                String end_date = jsonObject2.getString("end_date");
                                String meeting_point = jsonObject2.getString("meeting_point");
                                String budget = jsonObject2.getString("budget");

                                String guide_guide_id = jsonObject2.getString("guide_guide_id");



//                                Log.d("tour_id",tour_id);
//

                                //----------PutExtra-----------//
                                Intent intent = new Intent(FindTour.this, TourDetailsWithGuide.class);

                                intent.putExtra("tour_id", tour_id);
                                intent.putExtra("ture_district", ture_district);
                                intent.putExtra("tour_title", tour_title);
                                intent.putExtra("tour_area_details", tour_area_details);
                                intent.putExtra("tour_facilities", tour_facilities);
                                intent.putExtra("transportation", transportation);
                                intent.putExtra("tourist_limitation", tourist_limitation);

                                intent.putExtra("start_date", start_date);
                                intent.putExtra("end_date", end_date);
                                intent.putExtra("meeting_point", meeting_point);
                                intent.putExtra("budget", budget);
                                intent.putExtra("guide_guide_id", guide_guide_id);

                                startActivity(intent);

                            } else {

                              //  Toast.makeText(getApplicationContext(), "Error" + jsonObject.getString("result"), Toast.LENGTH_SHORT).show();
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
                        hashMap.put("id", "3");

                        return hashMap;
                    }
                };
                requestQueue.add(request);
            }
        });

//        btnTourDetails.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//
////                Intent intent = new Intent(FindTour.this, TourDetailsWithGuide.class);
////                startActivity(intent);
//            }
//        });
    }
}

