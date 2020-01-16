package com.example.petshot;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Build;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.*;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import androidx.appcompat.view.menu.MenuBuilder;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

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
    private GridLayout imagesGrid;
    private String url = "http://intensif06.ensicaen.fr:8080/users";



    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        pseudo = findViewById(R.id.pseudo_text);
        title = findViewById(R.id.title_text);
        firstName = findViewById(R.id.name);
        lastName = findViewById(R.id.lastName);
        description = findViewById(R.id.description);
        followers = findViewById(R.id.followers_nbr);
        follow = findViewById(R.id.follows_nbr);
        kills = findViewById(R.id.score);
        userStatistics = findViewById(R.id.user_statistics);
        followButton = findViewById(R.id.follow_button);
        messageButton = findViewById(R.id.message_button);
        profilePicture = findViewById(R.id.user_profil_pic);

        imagesGrid = findViewById(R.id.pictures);

        Intent intent = getIntent();
        Toast.makeText(getApplicationContext(), intent.getStringExtra("id"), Toast.LENGTH_LONG).show();
        for (int i = 0; i < 5; i++) {
            ImageView img = new ImageView(getApplicationContext());
            img.setImageDrawable(getDrawable(R.drawable.petshot));
            img.setLayoutParams(new Gallery.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT));
            imagesGrid.addView(img);
        }
        SharedPreferences sharedpreferences = getSharedPreferences(MainActivity.MyPREFERENCES, Context.MODE_PRIVATE);

        url = url +"/"+ sharedpreferences.getString("connectedId","");


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

                        JSONObject jsonobject = null;
                        try {
                            jsonobject = new JSONObject(response.toString());

                            Log.v("PSEUDO JSON", jsonobject.getString("pseudo"));
                            pseudo.setText(jsonobject.getString("pseudo"));
                            firstName.setText(jsonobject.getString("name"));
                            lastName.setText(jsonobject.getString("lastName"));
                            description.setText(jsonobject.getString("description"));
                            //title.setText(jsonobject.getString("title"));
                            followers.setText(jsonobject.getString("followers"));
                            //follow.setText(jsonobject.getString("follow"));
                            kills.setText(jsonobject.getString("kills"));


                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                        SharedPreferences sharedpreferences = getSharedPreferences(MainActivity.MyPREFERENCES, Context.MODE_PRIVATE);

                        SharedPreferences.Editor editor = sharedpreferences.edit();
                        editor.putString("connectedName", firstName.toString());
                        editor.putString("connectedLastName", lastName.toString());
                        editor.putString("connectedDescription", description.toString());
                        editor.putString("connectedFollowers", followers.toString());
                        editor.putString("connectedFollow", follow.toString());
                        editor.putString("connectedKills", kills.toString());

                        editor.apply();
                     //   Toast.makeText(getApplicationContext(), "Connected", Toast.LENGTH_LONG).show();
                        //startActivity(profileActivity);



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
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        //ajoute les entrées de menu_test à l'ActionBar
        getMenuInflater().inflate(R.menu.menu, menu);
        if(menu instanceof MenuBuilder){
            MenuBuilder m = (MenuBuilder) menu;
            m.setOptionalIconsVisible(true);

        }

        return true;
    }

    //gère le click sur une action de l'ActionBar
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        Intent mainActivity = new Intent(this, MainActivity.class);

        switch (item.getItemId()){
            case R.id.action_home:
                startActivity(mainActivity);
                return true;
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
