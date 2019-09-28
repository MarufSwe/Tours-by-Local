package com.example.user.toursbylocal;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.util.Log;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
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

public class Fucntional extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    Button findTour_btn, btnFindGuide;

    RequestQueue requestQueue1 = null;

    private static final String URL_find_guide_m = "http://undrooping-till.000webhostapp.com/local_tourist/guide/guide_search.php";

    private StringRequest request;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fucntional);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        findTour_btn = (Button) findViewById(R.id.btnFindTour);
        findTour_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), FindTour.class);
                startActivity(intent);
            }
        });

        requestQueue1 = Volley.newRequestQueue(this);


        btnFindGuide = (Button) findViewById(R.id.btnFindGuide);
        btnFindGuide.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

//                Intent a = new Intent(getApplicationContext(), FindGuide.class);
//                startActivity(a);

                request = new StringRequest(Request.Method.POST, URL_find_guide_m, new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            // Log.d("noakhailla","noa");
                            JSONObject jsonObjectGuide = new JSONObject(response);

                            if (jsonObjectGuide.getString("code").equalsIgnoreCase("200")){
                              //  Toast.makeText(getApplicationContext(), "SUCCESS " + jsonObjectGuide.getString("data"), Toast.LENGTH_SHORT).show();
//                                JSONObject jsonObjectGuide2 = new JSONObject(jsonObjectGuide.getString("date"));
//                                JSONObject jsonObjectGuideDetails = (jsonObjectGuide.getJSONObject("data"));
//                                String guideFirstName = jsonObjectGuideDetails.getString("first_name");
//                                String guideLastName = jsonObjectGuideDetails.getString("last_name");
//                                String guideAge = jsonObjectGuideDetails.getString("age");
//                                String guideBirthDate = jsonObjectGuideDetails.getString("birth_date");
//                                String guideEmail = jsonObjectGuideDetails.getString("email");
//                                String guideSkype = jsonObjectGuideDetails.getString("skype_name");
//                                String guideAddress = jsonObjectGuideDetails.getString("parmanent_address");
//                                String guideCountry = jsonObjectGuideDetails.getString("country");



                                Log.d("tour_id","dooone");
//

                                //----------PutExtra-----------//
                           //     Intent intent = new Intent(getApplicationContext(), FindGuide.class);


//                                intent.putExtra("guideFirstName", guideFirstName);
//                                intent.putExtra("guideLastName", guideLastName);
//                                intent.putExtra("guideAge", guideAge);
//                                intent.putExtra("guideBirthDate", guideBirthDate);
//                                intent.putExtra("guideEmail", guideEmail);
//                                intent.putExtra("guideSkype", guideSkype);
//                                intent.putExtra("guideAddress", guideAddress);
//                                intent.putExtra("guideCountry", guideCountry);
//                                Log.d("tour_id","do2oone");
                                Intent a1 = new Intent(getApplicationContext(), FindGuide.class);
                                startActivity(a1);


                            } else {

                              //  Toast.makeText(getApplicationContext(), "Error" + jsonObjectGuide.getString("result"), Toast.LENGTH_SHORT).show();
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
                        hashMap.put("district", "dhaka");

                        return hashMap;
                    }
                };
                requestQueue1.add(request);
            }
        });

//        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
//        fab.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
//                        .setAction("Action", null).show();
//            }
//        });

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.fucntional, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_camera) {
            // Handle the camera action
        } else if (id == R.id.nav_gallery) {

        } else if (id == R.id.nav_slideshow) {

        } else if (id == R.id.nav_requested) {

        } else if (id == R.id.nav_update) {

        } else if (id == R.id.nav_rating) {

        }else if (id == R.id.nav_history) {

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
