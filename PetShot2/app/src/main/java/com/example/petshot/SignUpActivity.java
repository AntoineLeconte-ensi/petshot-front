package com.example.petshot;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class SignUpActivity extends AppCompatActivity {

    private Intent mainActivity;
    private Intent flowActivity;
    private EditText email;
    private EditText firstName;
    private EditText lastName;
    private EditText userName;
    private EditText password;
    private EditText confirmPassword;
    private EditText nationality;

    private String url ="http://intensif06.ensicaen.fr:8080/users";
    private Request request;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        mainActivity = new Intent(this, MainActivity.class);
        flowActivity = new Intent(this, FlowActivity.class);
        email = findViewById(R.id.emailInput);
        firstName = findViewById(R.id.firstNameInput);
        lastName = findViewById(R.id.lastNameInput);
        userName = findViewById(R.id.userNameInput);
        password = findViewById(R.id.passwordInput);
        confirmPassword = findViewById(R.id.confirmPasswordInput);
        nationality = findViewById(R.id.userNationality);

        Button back = findViewById(R.id.backButton);
        Button signUp = findViewById(R.id.signUpButton);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(mainActivity);
            }
        });

        signUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                if (email.getText().toString().equals("") || firstName.getText().toString().equals("") || lastName.getText().toString().equals("") || userName.getText().toString().equals("") || password.getText().toString().equals("") || confirmPassword.getText().toString().equals("") || nationality.getText().toString().equals("")) {
                    Toast.makeText(getApplicationContext(), "You must fill in all the fields", Toast.LENGTH_LONG).show();
                } else if (!confirmPassword.getText().toString().equals(password.getText().toString())) {
                    password.setText("");
                    confirmPassword.setText("");
                    Toast.makeText(getApplicationContext(), "Invalid Password", Toast.LENGTH_LONG).show();
                } else {
                    final RequestQueue MyRequestQueue = Volley.newRequestQueue(getApplicationContext());

                    StringRequest postRequest = new StringRequest(Request.Method.POST, url,
                            new Response.Listener<String>() {
                                @Override
                                public void onResponse(String response) {
                                    // response
                                    Log.d("Response", response);
                                }
                            },
                            new Response.ErrorListener() {
                                @Override
                                public void onErrorResponse(VolleyError error) {
                                    // error
                                    Log.d("Error.Response", error.getMessage());
                                }
                            }
                    ) {
                        @Override
                        protected Map<String, String> getParams() {
                            Map<String, String> params = new HashMap<String, String>();
                            params.put("name", String.valueOf(firstName.getText()));
                            params.put("password", String.valueOf(password.getText()));
                            params.put("lastName", String.valueOf(lastName.getText()));
                            params.put("pseudo", String.valueOf(userName.getText()));
                            params.put("email", String.valueOf(email.getText()));
                            params.put("nationality", String.valueOf(nationality.getText()));
                            return params;
                        }
                    };

                    MyRequestQueue.add(postRequest);
                    startActivity(flowActivity);
                    Toast.makeText(getApplicationContext(), "Data Sent", Toast.LENGTH_LONG).show();
                }
            }
        });
    }
}
