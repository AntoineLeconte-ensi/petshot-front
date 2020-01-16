package com.example.petshot;

import android.annotation.SuppressLint;
import android.app.ActionBar;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.SearchView;
import android.widget.TextView;
import android.widget.Toast;
import android.view.*;
import android.widget.*;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.view.menu.MenuBuilder;
import androidx.core.view.MenuItemCompat;

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
    private String url = "http://intensif06.ensicaen.fr:8080/newfeed";
    private Intent searchActivity;
    private LinearLayout flowLayout;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_flow);

        searchActivity = new Intent(this, SearchActivity.class);
        Intent intent = getIntent();
        Toast.makeText(getApplicationContext(), intent.getStringExtra("id"), Toast.LENGTH_LONG).show();
      
        animalOfTheWeek = findViewById(R.id.animal_of_the_week);
        hunterOfTheWeek = findViewById(R.id.hunter_of_the_week);
        picAnimalOfTheWeek = findViewById(R.id.pic_animal_of_the_week);
        picHunterOfTheWeek = findViewById(R.id.pic_hunter_of_the_week);
        flowLayout = findViewById(R.id.flow_layout);

        ScrollView flow_scroll = findViewById(R.id.flow_scroll);


        //Button likeButton = findViewById(R.id.like_button);
        //Button commentButton = findViewById(R.id.signInButton);


        //GET REQUEST
        final RequestQueue MyRequestQueue = Volley.newRequestQueue(getApplicationContext());
        SharedPreferences sharedpreferences = getSharedPreferences(MainActivity.MyPREFERENCES, Context.MODE_PRIVATE);

        url = url + "/"+ sharedpreferences.getString("connectedId","");
        // prepare the Request
        StringRequest getRequest = new StringRequest(Request.Method.GET, url,
                new Response.Listener<String>()
                {
                    @Override
                    public void onResponse(String response) {
                        // display response
                        Log.d("Response", response.toString());

                        LayoutInflater inflater =  (LayoutInflater)getSystemService(Context.LAYOUT_INFLATER_SERVICE);

                        JSONObject jsonobject = null;

                        try {

                            jsonobject = new JSONObject(response.toString().substring(1,response.length()-1));
                            Log.v("LEEEEEENNGGGHHTT", String.valueOf(jsonobject.length()));

                            for (int i=0; i< jsonobject.length(); i++) {
                                LinearLayout linearLayout = new LinearLayout(getApplicationContext());
                                linearLayout.setLayoutParams(new LinearLayout.LayoutParams(ActionBar.LayoutParams.MATCH_PARENT, ActionBar.LayoutParams.WRAP_CONTENT));
                                linearLayout.setOrientation(LinearLayout.VERTICAL);

                                LinearLayout linearLayoutTop = new LinearLayout(getApplicationContext());
                                linearLayoutTop.setLayoutParams(new Gallery.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT));

                                linearLayoutTop.setOrientation(LinearLayout.HORIZONTAL);

                                ImageView imageViewProfile = new ImageView(getApplicationContext());
                                imageViewProfile.setLayoutParams(new Gallery.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT));
                                imageViewProfile.setImageDrawable(getResources().getDrawable(R.drawable.newfeedimage));

                                TextView textViewPseudo = new TextView(getApplicationContext());
                                textViewPseudo.setLayoutParams(new Gallery.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT));
                                textViewPseudo.setText("Pseudo");

                                linearLayoutTop.addView(imageViewProfile);
                                linearLayoutTop.addView(textViewPseudo);

                                LinearLayout linearLayoutMid = new LinearLayout(getApplicationContext());
                                linearLayoutMid.setLayoutParams(new Gallery.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT));
                                linearLayoutMid.setOrientation(LinearLayout.HORIZONTAL);

                                ImageView imageViewPublication = new ImageView(getApplicationContext());
                                imageViewPublication.setLayoutParams(new Gallery.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT));
                                imageViewPublication.setImageDrawable(getResources().getDrawable(R.drawable.newfeedimage));

                                linearLayoutTop.addView(linearLayoutMid);

                                LinearLayout linearLayoutBottom = new LinearLayout(getApplicationContext());
                                linearLayoutBottom.setLayoutParams(new Gallery.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT));
                                linearLayoutBottom.setOrientation(LinearLayout.HORIZONTAL);

                                Button buttonLike = new Button(getApplicationContext());
                                buttonLike.setLayoutParams(new Gallery.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT));
                                buttonLike.setText("Like");

                                Button buttonComment = new Button(getApplicationContext());
                                buttonComment.setLayoutParams(new Gallery.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT));
                                buttonComment.setText("Comment");


                                TextView textViewDescription = new TextView(getApplicationContext());
                                textViewDescription.setLayoutParams(new Gallery.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT));
                                textViewDescription.setText("Description");

                                linearLayoutBottom.addView(buttonLike);
                                linearLayoutBottom.addView(buttonComment);
                                linearLayoutBottom.addView(textViewDescription);

                                flowLayout.addView(linearLayout);
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


    }


    @SuppressLint("RestrictedApi")
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
                startActivity(searchActivity);
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
