package com.example.petshot;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.util.HashMap;
import java.util.Map;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import org.json.JSONObject;

public class SignInActivity extends AppCompatActivity {

    private Intent mainActivity;
    private Intent profileActivity;
    private EditText userName;
    private EditText password;
    private String url = "http://intensif06.ensicaen.fr:8080/users/login";
    private StringRequest request;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in);

        userName = findViewById(R.id.userNameInput);
        password = findViewById(R.id.passwordInput);

        Button back = findViewById(R.id.backButton);
        Button signIn = findViewById(R.id.signInButton);

        mainActivity = new Intent(this, MainActivity.class);
        profileActivity = new Intent(this, ProfileActivity.class);

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(mainActivity);
            }
        });

        signIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String connectedPseudo = String.valueOf(userName.getText());
                final String connectedPassword = String.valueOf(password.getText());
                final RequestQueue MyRequestQueue = Volley.newRequestQueue(getApplicationContext());
                url = url +"?pseudo="+connectedPseudo+"&password="+connectedPassword;
                // prepare the Request
                StringRequest getRequest = new StringRequest(Request.Method.GET, url,
                        new Response.Listener<String>()
                        {
                            @Override
                            public void onResponse(String response) {
                                // display response
                                Log.d("Response", response.toString());
                                SharedPreferences sharedpreferences = getSharedPreferences(MainActivity.MyPREFERENCES, Context.MODE_PRIVATE);

                                SharedPreferences.Editor editor = sharedpreferences.edit();
                                editor.putString("connectedPseudo", connectedPseudo);
                                editor.putString("connectedPassword", connectedPassword);
                                editor.putString("connectedId", response.toString());
                                editor.apply();
                                Toast.makeText(getApplicationContext(), "Connected", Toast.LENGTH_LONG).show();
                                startActivity(profileActivity);

                            }
                        },
                        new Response.ErrorListener()
                        {
                            @Override
                            public void onErrorResponse(VolleyError error) {
                                Log.d("Error.Response", error.getMessage());
                            }
                        }
                );

                // add it to the RequestQueue
                MyRequestQueue.add(getRequest);
            }
        });

    }
}
