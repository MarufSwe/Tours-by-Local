package com.example.user.toursbylocal;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
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

public class RegistrationActivity extends AppCompatActivity {

    private EditText regFirstName,regLastName, regEmail, regCountry, regAge,regPassword;
    Button regSubmitBtn, findGuide_btn;

    private RequestQueue requestQueue;

    private static final String URL = "http://undrooping-till.000webhostapp.com/local_tourist/guide/registration.php";

    private StringRequest request;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);

        getSupportActionBar().hide();


//        findGuide_btn = (Button) findViewById(R.id.findGuide_btn);
//        findGuide_btn.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Intent intente = new Intent(RegistrationActivity.this, FindGuide.class);
//                startActivity(intente);
//            }
//        });


        regFirstName = (EditText) findViewById(R.id.regFirstName);
        regLastName = (EditText) findViewById(R.id.regLastName);
        regEmail = (EditText) findViewById(R.id.regEmail);
        regCountry = (EditText) findViewById(R.id.regCountry);
        regAge = (EditText) findViewById(R.id.regAge);
        regPassword = (EditText) findViewById(R.id.regPassword);

        regSubmitBtn = (Button) findViewById(R.id.regSubmitBtn);

        requestQueue = Volley.newRequestQueue(this);


        regSubmitBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                request = new StringRequest(Request.Method.POST, URL, new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            Log.d("noakhailla","noa");
                            JSONObject jsonObject = new JSONObject(response);

                            if (jsonObject.getString("code").equalsIgnoreCase("200")){
                                Toast.makeText(getApplicationContext(), "SUCCESS " + jsonObject.getString("result"), Toast.LENGTH_SHORT).show();
                                startActivity(new Intent(getApplicationContext(), LoginActivity.class));

                                Log.d("noakhailla","tut");
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
                        hashMap.put("first_name", regFirstName.getText().toString());
                        hashMap.put("last_name", regLastName.getText().toString());
                        hashMap.put("email", regEmail.getText().toString());
                        hashMap.put("country", regCountry.getText().toString());
                        hashMap.put("age", regAge.getText().toString());
                        hashMap.put("password", regPassword.getText().toString());

                        return hashMap;
                    }
                };

                requestQueue.add(request);
            }
        });


        }
    }

