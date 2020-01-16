package com.example.petshot;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;
import android.view.View;
import android.widget.*;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

public class ProfileActivity extends AppCompatActivity {

    private TextView pseudo;
    private TextView title;
    private TextView firstName;
    private TextView lastName;
    private TextView description;
    private TextView followers;
    private TextView follow;
    private TextView kills;
    private ImageView profilePicture;
    private ImageView userStatistics;
    private Button followButton;
    private Button messageButton;
    private String url = "http://intensif06.ensicaen.fr:8080/users";



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        pseudo = findViewById(R.id.pseudo_text);
        title = findViewById(R.id.title_text);
        firstName = findViewById(R.id.name);
        lastName = findViewById(R.id.userNameInput);
        description = findViewById(R.id.description);
        followers = findViewById(R.id.followers_nbr);
        follow = findViewById(R.id.follows_nbr);
        kills = findViewById(R.id.score);
        userStatistics = findViewById(R.id.user_statistics);
        followButton = findViewById(R.id.follow_button);
        messageButton = findViewById(R.id.message_button);
        profilePicture = findViewById(R.id.user_profil_pic);

        SharedPreferences sharedpreferences = getSharedPreferences(MainActivity.MyPREFERENCES, Context.MODE_PRIVATE);

        url = url +"?id="+ sharedpreferences.getString("connectedId","");

        /*
        //GET REQUEST
        final RequestQueue MyRequestQueue = Volley.newRequestQueue(getApplicationContext());
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
            */
    }
}
