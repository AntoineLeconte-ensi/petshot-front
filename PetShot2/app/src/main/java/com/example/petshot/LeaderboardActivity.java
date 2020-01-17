package com.example.petshot;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
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

import java.util.ArrayList;
import java.util.List;

public class LeaderboardActivity extends AppCompatActivity {

    private String pseudo;
    private String kills;
    private LeaderboardActivity context;

    private List<String> lines = new ArrayList<String>();

    public String url = "http://intensif06.ensicaen.fr:8080/leaderboard/users";
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        context = this;
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_leaderboard);

        final Spinner zone = findViewById(R.id.zone);
        final ListView leaderboard = findViewById(R.id.leaderboard);

        final ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>
                (context,android.R.layout.simple_list_item_1,lines);

        leaderboard.setAdapter(arrayAdapter);


        final RequestQueue MyRequestQueue = Volley.newRequestQueue(getApplicationContext());
        // prepare the Request
        StringRequest getRequest = new StringRequest(Request.Method.GET, url,
                new Response.Listener<String>()
                {
                    @Override
                    public void onResponse(String response) {
                        // display response
                        JSONArray json = null;
                        try {

                            json = new JSONArray(response);


                            for (int i = 0; i < json.length(); i++) {

                                JSONObject jsonobject = (JSONObject)json.get(i);

                                pseudo = jsonobject.getString("pseudo");
                                kills = jsonobject.getString("countKills");

                                lines.add(pseudo + " : " + kills);


                            }

                            arrayAdapter.notifyDataSetChanged();

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

        // add it to the RequestQueue
        MyRequestQueue.add(getRequest);


        zone.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

            public void onItemSelected(AdapterView<?> arg0, View view, int position, long id) {
                String item = (String)zone.getSelectedItem();

                lines.clear();

                final RequestQueue MyRequestQueue = Volley.newRequestQueue(getApplicationContext());

                String modifiedURL = url;

                if (!item.equals("MONDE"))
                {
                    modifiedURL += "?zone="+item;
                }

                Log.v("tamer1", modifiedURL);

                StringRequest getRequest = new StringRequest(Request.Method.GET, modifiedURL,
                        new Response.Listener<String>()
                        {
                            @Override
                            public void onResponse(String response) {
                                // display response

                                Log.v("tamer2", "AAAAAAAAAAAAAAAAAAAAAH");
                                JSONArray json = null;
                                try {

                                    json = new JSONArray(response);


                                    leaderboard.setAdapter(arrayAdapter);

                                    for (int i = 0; i < json.length(); i++) {

                                        JSONObject jsonobject = (JSONObject)json.get(i);

                                        pseudo = jsonobject.getString("pseudo");
                                        kills = jsonobject.getString("countKills");

                                        lines.add(pseudo + " : " + kills);


                                    }

                                    arrayAdapter.notifyDataSetChanged();

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
                        });

                MyRequestQueue.add(getRequest);

            }
            public void onNothingSelected(AdapterView<?> arg0) { }
        });

    }
}

