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
                final RequestQueue MyRequestQueue = Volley.newRequestQueue(getApplicationContext());

                Map<String, String> userData = new HashMap<>();
                userData.put("name", String.valueOf(firstName.getText()));
                Log.v("name", String.valueOf(firstName.getText()));
                userData.put("password", String.valueOf(password.getText()));
                userData.put("lastName", String.valueOf(lastName.getText()));
                userData.put("pseudo", String.valueOf(userName.getText()));
                userData.put("email", String.valueOf(email.getText()));
                userData.put("nationality", String.valueOf(nationality.getText()));

                JSONObject queryParams = new JSONObject(userData);

                request = new JsonObjectRequest(Request.Method.GET, url, queryParams, new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        Log.v("INFO", response.toString());
                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Log.v("ERROR", "ERROR");
                        if (error.getMessage() != null) {
                            Log.v("ERROR", error.getMessage());
                        }
                    }
                }) /*{
                    protected Map<String, String> getParams() {
                        Log.v("test", " inside");
                        Map<String, String> userData = new HashMap<String, String>();
                        userData.put("name", String.valueOf(firstName.getText()));
                        Log.v("name", String.valueOf(firstName.getText()));
                        userData.put("password", String.valueOf(password.getText()));
                        userData.put("lastName", String.valueOf(lastName.getText()));
                        userData.put("pseudo", String.valueOf(userName.getText()));
                        userData.put("email", String.valueOf(email.getText()));
                        userData.put("nationality", String.valueOf(nationality.getText()));
                        Log.v("nom",userData.get("name"));
                        Log.v("nom",userData.get("pseudo"));
                        return userData;
                    }
                }*/;
                // request.set

                Log.v("request", request.toString());

                MyRequestQueue.add(request);
                Log.v("test", "sent");
                Toast.makeText(getApplicationContext(), "Data Sent", Toast.LENGTH_LONG).show();
            }
        });
    }
}
