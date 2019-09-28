package com.example.user.toursbylocal;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
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

public class LoginActivity extends AppCompatActivity {

    private EditText login_email,login_password;
    private Button login_submit;
    private RequestQueue requestQueue;

    private static final String URL = "http://undrooping-till.000webhostapp.com/local_tourist/guide/login.php";

    private StringRequest request;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        getSupportActionBar().hide();

        login_email = (EditText) findViewById(R.id.login_email);
        login_password = (EditText) findViewById(R.id.login_password);
        login_submit = (Button) findViewById(R.id.login_submit);

        requestQueue = Volley.newRequestQueue(this);


        login_submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                request = new StringRequest(Request.Method.POST, URL, new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            Log.d("noakhailla","noa");
                            JSONObject jsonObject = new JSONObject(response);

                            if (jsonObject.getString("code").equalsIgnoreCase("200")){
                              //  Toast.makeText(getApplicationContext(), "SUCCESS " + jsonObject.getString("data"), Toast.LENGTH_SHORT).show();
                                JSONObject jsonObject2 = (jsonObject.getJSONObject("data"));
                                String traveler_Id = jsonObject2.getString("traveler_Id");
                                String first_name = jsonObject2.getString("first_name");
                                String last_name = jsonObject2.getString("last_name");
                                saveUserName(traveler_Id,first_name);
                                startActivity(new Intent(getApplicationContext(), Fucntional.class));

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
                        hashMap.put("email", login_email.getText().toString());
                        hashMap.put("password", login_password.getText().toString());

                        return hashMap;
                    }
                };

                requestQueue.add(request);
            }
        });

    }

    public void saveUserName(String id, String name){
        SharedPreferences sharedPreferences = getSharedPreferences("torid", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString("id", id);
        editor.putString("name", name);
        editor.apply();

        //  Toast.makeText(this, " ", Toast.LENGTH_LONG).show();
    }
}
