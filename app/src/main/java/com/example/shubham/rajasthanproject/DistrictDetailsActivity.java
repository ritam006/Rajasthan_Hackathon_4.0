package com.example.shubham.rajasthanproject;

import android.content.Intent;
import android.content.Loader;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ListView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class DistrictDetailsActivity extends AppCompatActivity{
    String url_send;
    String district ;
    JSONArray jsonArray;
    ArrayList<CustomString> list;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_district_details);
        final ListView district_details = findViewById(R.id.details_of_districts);
        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();
        district = bundle.getString("district");
        RequestQueue queue = Volley.newRequestQueue(DistrictDetailsActivity.this);
        JSONObject jsonBody=new JSONObject();
        try {
            jsonBody.put("district",district);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        url_send = "http://172.26.42.194:5000/get_households";
        JsonObjectRequest postRequest = new JsonObjectRequest(Request.Method.PUT,url_send, jsonBody,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            //Process os success response
                            jsonArray = response.getJSONArray("households");
                                    /* TODO
                                        Implement the for loop
                                     */
                            list = new ArrayList<CustomString>();
                            for(int i=0;i<jsonArray.length();i++)
                            {
                                String name = jsonArray.getJSONObject(i).getString("name");
                                String address = jsonArray.getJSONObject(i).getString("address");
                                String phone = jsonArray.getJSONObject(i).getString("phone");
                                Double water_clean = jsonArray.getJSONObject(i).getDouble("water_clean");
                                Double water_normal = jsonArray.getJSONObject(i).getDouble("water_normal");
                                //Integer water = Integer.parseInt(water_clean);
                                CustomString obj = new CustomString(name,address,phone,water_clean);
                                list.add(obj);
                            }
                            WordAdapter customAdapter = new WordAdapter(DistrictDetailsActivity.this,list);
                            district_details.setAdapter(customAdapter);
                            String name = jsonArray.getJSONObject(0).getString("name");
                            String address = jsonArray.getJSONObject(0).getString("address");
                            String phone = jsonArray.getJSONObject(0).getString("phone");
                            String water_clean = jsonArray.getJSONObject(0).getString("water_clean");
                            String water_normal = jsonArray.getJSONObject(0).getString("water_normal");

                            Log.e("name",name);
                            Log.e("address",address);
                            Log.e("phone",phone);
                            Log.e("water_clean",water_clean);
                            Log.e("water_normal",water_normal);
                            //Log.e("water_normal",water_normal);

                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e("user_signup",error.getMessage().toString());
                VolleyLog.e("Error: ", error.getMessage());
            }
        });
        queue.add(postRequest);
        //ArrayList<CustomString> list = new ArrayList<>();

    }
}
