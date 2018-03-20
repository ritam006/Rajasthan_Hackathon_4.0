package com.example.shubham.rajasthanproject;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.CheckBox;
import android.widget.EditText;

import java.util.Arrays;
import java.util.List;

public class SurveyForm extends AppCompatActivity {

    EditText editTextName;
    EditText editTextPhone;
    EditText editTextNumInfants;
    EditText editTextNumTeens;
    EditText editTextNumAdults;
    EditText editTextNumOlds;
    AutoCompleteTextView districtTextView;
    CheckBox checkBox;
    private List<String> district_list;
    private String [] districts;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_survey_form);
        editTextName=findViewById(R.id.name_person);
        editTextPhone=findViewById(R.id.phone_person);
        districtTextView=findViewById(R.id.district);
        editTextNumTeens=findViewById(R.id.num_teens);
        editTextNumInfants=findViewById(R.id.num_infants);
        editTextNumAdults=findViewById(R.id.num_adults);
        editTextNumOlds=findViewById(R.id.num_olds);
        checkBox=findViewById(R.id.checkbox_water);
        districts = getResources().getStringArray(R.array.district_name);
        district_list = Arrays.asList(districts);
        ArrayAdapter<String> district_adapter = new ArrayAdapter<String>(
                this,android.R.layout.simple_list_item_1,districts);
        districtTextView.setAdapter(district_adapter);

    }
}
