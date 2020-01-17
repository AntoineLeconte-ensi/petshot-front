package com.example.petshot;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class SearchActivity extends AppCompatActivity {

    private Intent profileActivity;
    private ImageView searchButton;
    private EditText searchField;
    private String username;
    private String id;
    private String url = "http://intensif06.ensicaen.fr:8080/users/find/";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search2);

        profileActivity = new Intent(this, ProfileActivity.class);
        searchButton = findViewById(R.id.searchButton);
        searchField = findViewById(R.id.searchField);
        searchButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                username = searchField.getText().toString();
                /* get ID if exists*/
                final RequestQueue MyRequestQueue = Volley.newRequestQueue(getApplicationContext());
                StringRequest getRequest = new StringRequest(Request.Method.GET, url + username ,new Response.Listener<String>() {

                    @Override
                    public void onResponse(String response) {
                        JSONArray jsonobject = null;
                        //String res = response.substring(1,response.length() - 1);
                        try {
                            jsonobject = new JSONArray(response);
                            id = jsonobject.getJSONObject(0).getString("id");
                            Toast.makeText(getApplicationContext(), id, Toast.LENGTH_LONG).show();
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }


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
                MyRequestQueue.add(getRequest);
                /* send id to profile activity*/
                profileActivity.putExtra("id", id);
                startActivity(profileActivity);
            }
        });

    }
}
