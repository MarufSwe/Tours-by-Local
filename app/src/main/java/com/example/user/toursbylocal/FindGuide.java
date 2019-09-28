package com.example.user.toursbylocal;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
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

public class FindGuide extends AppCompatActivity implements View.OnClickListener {


    private static final String URLGuideDetails = "http://undrooping-till.000webhostapp.com/local_tourist/guide/guide_details.php";

    private StringRequest request;
    private RequestQueue requestQueue;
    Button findGuide_details1, findGuide_details2, findGuide_details3, findGuide_details4;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_find_guide);

        requestQueue = Volley.newRequestQueue(this);

        findGuide_details1 = (Button) findViewById(R.id.findGuideDetails1);
//        findGuide_details2 = (Button) findViewById(R.id.findGuide_details2);
//        findGuide_details3 = (Button) findViewById(R.id.findGuide_details3);
//        findGuide_details4 = (Button) findViewById(R.id.findGuide_details4);

        findGuide_details1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
             //   Toast.makeText(getApplicationContext(), "Error" , Toast.LENGTH_SHORT).show();
                request = new StringRequest(Request.Method.POST, URLGuideDetails, new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                             Log.d("noakhailla","noa");
                            JSONObject jsonObjectGuide = new JSONObject(response);

                            if (jsonObjectGuide.getString("code").equalsIgnoreCase("200")){
//                                Toast.makeText(getApplicationContext(), "SUCCESS " + jsonObjectGuide.getString("data"), Toast.LENGTH_SHORT).show();

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
//

                                //----------PutExtra-----------//
                                Intent intent = new Intent(getApplicationContext(), GuideProfile.class);


                                intent.putExtra("guideFirstName", guideFirstName);
                                intent.putExtra("guideLastName", guideLastName);
                                intent.putExtra("guideAge", guideAge);
                                intent.putExtra("guideBirthDate", guideBirthDate);
                                intent.putExtra("guideEmail", guideEmail);
                                intent.putExtra("guideSkype", guideSkype);
                                intent.putExtra("guideAddress", guideAddress);
                                intent.putExtra("guideCountry", guideCountry);

                                startActivity(intent);



                            } else {

                               // Toast.makeText(getApplicationContext(), "Error" + jsonObjectGuide.getString("result"), Toast.LENGTH_SHORT).show();
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
                        hashMap.put("id", "1");

                        return hashMap;
                    }
                };
                requestQueue.add(request);
            }
        });

//        findGuide_details1.setOnClickListener(this);
//        findGuide_details2.setOnClickListener(this);
//        findGuide_details3.setOnClickListener(this);
//        findGuide_details4.setOnClickListener(this);


    }

    @Override
    public void onClick(View v) {

        switch (v.getId()){
            case R.id.findGuide_details1:
//                Intent intent = new Intent(FindGuide.this, GuideBookDetails.class);
//                startActivity(intent);

                request = new StringRequest(Request.Method.POST, URLGuideDetails, new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            // Log.d("noakhailla","noa");
                            JSONObject jsonObjectGuide = new JSONObject(response);

                            if (jsonObjectGuide.getString("code").equalsIgnoreCase("200")){
//                                Toast.makeText(getApplicationContext(), "SUCCESS " + jsonObjectGuide.getString("data"), Toast.LENGTH_SHORT).show();

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
//

                                //----------PutExtra-----------//
                                Intent intent = new Intent(getApplicationContext(), GuideProfile.class);


                                intent.putExtra("guideFirstName", guideFirstName);
                                intent.putExtra("guideLastName", guideLastName);
                                intent.putExtra("guideAge", guideAge);
                                intent.putExtra("guideBirthDate", guideBirthDate);
                                intent.putExtra("guideEmail", guideEmail);
                                intent.putExtra("guideSkype", guideSkype);
                                intent.putExtra("guideAddress", guideAddress);
                                intent.putExtra("guideCountry", guideCountry);

                                startActivity(intent);



                            } else {

                            //    Toast.makeText(getApplicationContext(), "Error" + jsonObjectGuide.getString("result"), Toast.LENGTH_SHORT).show();
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
                        hashMap.put("id", "1");

                        return hashMap;
                    }
                };
                requestQueue.add(request);
                break;
//            case R.id.findGuide_details2:
//                Intent intent1 = new Intent(FindGuide.this, GuideBookDetails.class);
//                startActivity(intent1);
//                break;
//            case R.id.findGuide_details3:
//                Intent intent2 = new Intent(FindGuide.this, GuideBookDetails.class);
//                startActivity(intent2);
//                break;
//            case R.id.findGuide_details4:
//                Intent intent3 = new Intent(FindGuide.this, GuideBookDetails.class);
//                startActivity(intent3);
//                break;
        }

    }
}
