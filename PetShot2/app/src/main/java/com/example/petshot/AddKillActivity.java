package com.example.petshot;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.util.Base64;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.android.volley.Header;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.io.ByteArrayOutputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class AddKillActivity extends AppCompatActivity {
    private Bitmap killImage;

    private ImageView killView;
    private Button sendButton;

    private List<String> animals;

    private String animalUrl = "http://intensif06.ensicaen.fr:8080/animals";
    private String url = "http://intensif06.ensicaen.fr:8080/kills";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_add_kill);

        killView = findViewById(R.id.kill_image);
        sendButton = findViewById(R.id.validate);

        /*
        // TODO : Add animal selector
        final RequestQueue MyRequestQueue = Volley.newRequestQueue(getApplicationContext());

        // prepare the Request
        StringRequest getRequest = new StringRequest(Request.Method.GET, animalUrl,
                new Response.Listener<String>()
                {
                    @Override
                    public void onResponse(String response) {
                        Log.v("INFO", response);
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
         */

        sendButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                ByteArrayOutputStream outputImage = new ByteArrayOutputStream();
                killImage.compress(Bitmap.CompressFormat.PNG, 100, outputImage);
                byte[] killImageByteArray = outputImage.toByteArray();

                final String killImage = Base64.encodeToString(killImageByteArray, Base64.DEFAULT);

                SharedPreferences sharedpreferences = getSharedPreferences(MainActivity.MyPREFERENCES, Context.MODE_PRIVATE);
                final String userId = sharedpreferences.getString("connectedId", "");

                final RequestQueue MyRequestQueue = Volley.newRequestQueue(getApplicationContext());


                // prepare the Request
                StringRequest getRequest = new StringRequest(Request.Method.POST, url,
                        new Response.Listener<String>()
                        {
                            @Override
                            public void onResponse(String response) {
                                Toast.makeText(getApplicationContext(), "Photo envoyée", Toast.LENGTH_LONG).show();
                                finish();
                            }
                        },
                        new Response.ErrorListener()
                        {
                            @Override
                            public void onErrorResponse(VolleyError error) {
                                for(Header header : error.networkResponse.allHeaders) {
                                    Log.v("ERROR", header.toString());
                                }
                            }
                        }
                ) {
                    @Override
                    protected Map<String, String> getParams() {
                        Map<String, String> params = new HashMap<String, String>();
                        params.put("user", userId);
                        params.put("animal", "12");
                        params.put("image", killImage);
                        // TODO : Utiliser GPS
                        // params.put("lat", );
                        // params.put("lng", );

                        return params;
                    }
                };

                // add it to the RequestQueue
                MyRequestQueue.add(getRequest);
            }
        });

        Intent i = getIntent();
        Bundle extras = i.getExtras();

        killImage = (Bitmap) extras.get("image");

        killView.setImageBitmap(killImage);
    }

    //gère le click sur une action de l'ActionBar
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        Intent mainActivity = new Intent(this, MainActivity.class);

        switch (item.getItemId()){
            case R.id.action_news:
                Intent flowActivity = new Intent(this, FlowActivity.class);
                startActivity(flowActivity);
                return true;
            case R.id.action_search:
                return true;
            case R.id.action_profil:
                Intent profileActivity = new Intent(this, ProfileActivity.class);
                startActivity(profileActivity);
                return true;
            case R.id.action_logout:
                startActivity(mainActivity);
                return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
