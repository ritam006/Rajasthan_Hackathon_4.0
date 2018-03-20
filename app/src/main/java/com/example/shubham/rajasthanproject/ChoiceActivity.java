package com.example.shubham.rajasthanproject;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;


public class ChoiceActivity extends AppCompatActivity {
    private static final int REQUEST_SIGNUP = 0;
    private int choice;
    @BindView(R.id.admin_choice) Button _adminButton;
    @BindView(R.id.user_choice) Button _userButton;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choice);
        ButterKnife.bind(this);

        _adminButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent send_intent = new Intent(ChoiceActivity.this,AdminSignup.class);
                startActivityForResult(send_intent,REQUEST_SIGNUP);
                finish();
                overridePendingTransition(R.anim.push_left_in, R.anim.push_left_out);
            }
        });

        _userButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent send_intent = new Intent(ChoiceActivity.this,SignupActivity.class);
                startActivityForResult(send_intent,REQUEST_SIGNUP);
                finish();
                overridePendingTransition(R.anim.push_left_in, R.anim.push_left_out);
            }
        });

        /*if(choice ==1)
        {
            Intent send_intent = new Intent(ChoiceActivity.this,SignupActivity.class);
            startActivityForResult(send_intent,REQUEST_SIGNUP);
            finish();
            overridePendingTransition(R.anim.push_left_in, R.anim.push_left_out);
        }
        if(choice ==2)
        {
            Intent send_intent = new Intent(ChoiceActivity.this,AdminSignup.class);
            startActivityForResult(send_intent,REQUEST_SIGNUP);
            finish();
            overridePendingTransition(R.anim.push_left_in, R.anim.push_left_out);
        }*/

    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == REQUEST_SIGNUP) {
            if (resultCode == RESULT_OK) {

                // TODO: Implement successful signup logic here
                // By default we just finish the Activity and log them in automatically
                this.finish();
            }
        }
    }
}
