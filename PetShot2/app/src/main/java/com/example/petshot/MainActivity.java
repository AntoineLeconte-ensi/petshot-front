package com.example.petshot;

import android.view.Menu;
import android.view.MenuItem;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;
import androidx.appcompat.view.menu.MenuBuilder;

public class MainActivity extends AppCompatActivity {

    public static final String MyPREFERENCES = "MyPrefs" ;
    private Intent signInActivity;
    private Intent signUpActivity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //ImageView headerimage = findViewById(R.id.headerImage);
        Button signInButton = findViewById(R.id.connectionButton);
        Button signUpButton = findViewById(R.id.inscriptionButton);

        signInActivity = new Intent(this, SignInActivity.class);
        signUpActivity = new Intent(this, SignUpActivity.class);
        //headerimage.setImageResource(R.drawable.petshot);
        signInButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(signInActivity);
                Toast.makeText(getApplicationContext(), "Sign In", Toast.LENGTH_LONG).show();
            }
        });

        signUpButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(signUpActivity);
                Toast.makeText(getApplicationContext(), "Sign Up", Toast.LENGTH_LONG).show();
            }
        });
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
