package com.example.petshot;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import androidx.appcompat.view.menu.MenuBuilder;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import org.json.JSONException;
import org.json.JSONObject;

public class FlowActivity extends AppCompatActivity {

    private TextView animalOfTheWeek;
    private TextView hunterOfTheWeek;
    private ImageView picAnimalOfTheWeek;
    private ImageView picHunterOfTheWeek;
    private ImageView imageProfil;
    private TextView pseudo;
    private ImageView imagePublication;
    private TextView textPublication;
    private String url = "http://intensif06.ensicaen.fr:8080/newfeed";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_flow);

        animalOfTheWeek = findViewById(R.id.animal_of_the_week);
        hunterOfTheWeek = findViewById(R.id.hunter_of_the_week);
        picAnimalOfTheWeek = findViewById(R.id.pic_animal_of_the_week);
        picHunterOfTheWeek = findViewById(R.id.pic_hunter_of_the_week);
        imageProfil = findViewById(R.id.image_profil);
        pseudo = findViewById(R.id.pseudo);
        imagePublication = findViewById(R.id.image_publication);
        textPublication = findViewById(R.id.text_publication);

        Button likeButton = findViewById(R.id.like_button);
        Button commentButton = findViewById(R.id.signInButton);

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

                        JSONObject jsonobject = null;
                        try {
                            jsonobject = new JSONObject(response.toString());

                            for (int i=0; i< jsonobject.length(); i++) {


                            }


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

        */
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
