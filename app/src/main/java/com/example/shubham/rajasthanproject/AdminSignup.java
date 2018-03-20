package com.example.shubham.rajasthanproject;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import butterknife.BindView;
import butterknife.ButterKnife;
import com.android.volley.DefaultRetryPolicy;
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

import java.util.Arrays;
import java.util.List;


public class AdminSignup extends AppCompatActivity {
    private static final String TAG = "AdminSignupActivity";

    @BindView(R.id.admin_input_name) EditText _nameText;
    //@BindView(R.id.input_address) EditText _addressText;
    //@BindView(R.id.district)AutoCompleteTextView _districtText;
    //@BindView(R.id.input_email) EditText _emailText;
    @BindView(R.id.admin_input_id) EditText _IDText;
    @BindView(R.id.admin_input_password) EditText _passwordText;
    @BindView(R.id.admin_input_reEnterPassword) EditText _reEnterPasswordText;
    @BindView(R.id.admin_btn_signup) Button _signupButton;
    @BindView(R.id.admin_link_login) TextView _loginLink;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_signup);
        ButterKnife.bind(this);

        /*districts = getResources().getStringArray(R.array.district_name);
        district_list = Arrays.asList(districts);
        ArrayAdapter<String> district_adapter = new ArrayAdapter<String>(
                this,android.R.layout.simple_list_item_1,districts);
        _districtText.setAdapter(district_adapter);
*/
        _signupButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                signup();
            }
        });

        _loginLink.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Finish the registration screen and return to the Login activity
                Intent intent = new Intent(getApplicationContext(),LoginActivity.class);
                startActivity(intent);
                finish();
                overridePendingTransition(R.anim.push_left_in, R.anim.push_left_out);
            }
        });
    }

    public void signup() {
        Log.d(TAG, "Signup");

        if (!validate()) {
            onSignupFailed();
            return;
        }

        _signupButton.setEnabled(false);

        /*final ProgressDialog progressDialog = new ProgressDialog(SignupActivity.this,
                R.style.AppTheme_Dark_Dialog);
        progressDialog.setIndeterminate(true);
        progressDialog.setMessage("Creating Account...");
        progressDialog.show();*/

        String url_signup = "http://10.42.0.75:5000/signup_as_official";
        String name = _nameText.getText().toString();
        /*String address = _addressText.getText().toString();
        String district = _districtText.getText().toString();*/
        //String email = _emailText.getText().toString();
        String ID = _IDText.getText().toString();
        String password = _passwordText.getText().toString();
        String reEnterPassword = _reEnterPasswordText.getText().toString();

        // TODO: Implement your own signup logic here.
        RequestQueue queue = Volley.newRequestQueue(this);
        JSONObject jsonBody=new JSONObject();
        try {
            jsonBody.put("name",name);
            /*jsonBody.put("address",address);
            jsonBody.put("district",district);*/
            jsonBody.put("username",ID);
            jsonBody.put("password",password);
            jsonBody.put("usertype",2);
        } catch (JSONException e) {
            e.printStackTrace();
        }

        JsonObjectRequest postRequest = new JsonObjectRequest(Request.Method.POST,url_signup, jsonBody,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            //Process os success response
                            Integer usertype = response.getInt("usertype");
                            Integer userid = response.getInt("id");
                            Log.e("userid",String.valueOf(userid));
                            Log.e("usertype",String.valueOf(usertype));

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



        /*new android.os.Handler().postDelayed(
                new Runnable() {
                    public void run() {
                        // On complete call either onSignupSuccess or onSignupFailed
                        // depending on success
                        onSignupSuccess();
                        // onSignupFailed();
                        progressDialog.dismiss();
                    }
                }, 3000);
    }
*/

    public void onSignupSuccess() {
        _signupButton.setEnabled(true);
        setResult(RESULT_OK, null);
        finish();
    }

    public void onSignupFailed() {
        Toast.makeText(getBaseContext(), "Login failed", Toast.LENGTH_LONG).show();

        _signupButton.setEnabled(true);
    }

    public boolean validate() {
        boolean valid = true;

        String name = _nameText.getText().toString();
        /*String address = _addressText.getText().toString();*/
        //String email = _emailText.getText().toString();
        String ID = _IDText.getText().toString();
        String password = _passwordText.getText().toString();
        String reEnterPassword = _reEnterPasswordText.getText().toString();
        //String district = _districtText.getText().toString();

        if (name.isEmpty() || name.length() < 3) {
            _nameText.setError("at least 3 characters");
            valid = false;
        } else {
            _nameText.setError(null);
        }

        /*if (address.isEmpty()) {
            _addressText.setError("Enter Valid Address");
            valid = false;
        } else {
            _addressText.setError(null);
        }

        if(district.isEmpty() || !district_list.contains(district))
        {
            _districtText.setError("Enter a valid district");
            valid=false;
        }else {
            _districtText.setError(null);
        }*/


//        if (email.isEmpty() || !android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
//            _emailText.setError("enter a valid email address");
//            valid = false;
//        } else {
//            _emailText.setError(null);
//        }

        if (ID.isEmpty() || ID.length()!=5) {
            _IDText.setError("Enter Valid Mobile Number");
            valid = false;
        } else {
            _IDText.setError(null);
        }

        if (password.isEmpty() || password.length() < 4 || password.length() > 10) {
            _passwordText.setError("between 4 and 10 alphanumeric characters");
            valid = false;
        } else {
            _passwordText.setError(null);
        }

        if (reEnterPassword.isEmpty() || reEnterPassword.length() < 4 || reEnterPassword.length() > 10 || !(reEnterPassword.equals(password))) {
            _reEnterPasswordText.setError("Password Do not match");
            valid = false;
        } else {
            _reEnterPasswordText.setError(null);
        }

        return valid;
    }
}
