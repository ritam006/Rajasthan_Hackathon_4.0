package com.example.shubham.rajasthanproject;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

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
import org.w3c.dom.Text;

import java.util.ArrayList;

public class OfficialMainActivity extends AppCompatActivity {
    private String url_send;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_official_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        final ListView listView = findViewById(R.id.list_of_districts);
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(OfficialMainActivity.this,SurveyForm.class);
                startActivity(intent);
            }
        });

        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();
        ArrayList<String> string_district = bundle.getStringArrayList("districts");
        ArrayAdapter adapter = new ArrayAdapter(this,android.R.layout.simple_list_item_1,string_district);
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Object item = listView.getItemAtPosition(i);
                //Toast.makeText(OfficialMainActivity.this,String.valueOf(item),Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(OfficialMainActivity.this,DistrictDetailsActivity.class);
                intent.putExtra("district", String.valueOf(item));
                startActivity(intent);
                RequestQueue queue = Volley.newRequestQueue(OfficialMainActivity.this);
                JSONObject obj = new JSONObject();
                JSONObject jsonBody=new JSONObject();
                try {
                    jsonBody.put("district",String.valueOf(item));
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
                                    JSONArray jsonArray = response.getJSONArray("households");
                                    /* TODO
                                        Implement the for loop
                                     */
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
            }
        });
    }

}
