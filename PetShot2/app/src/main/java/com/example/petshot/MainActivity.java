package com.example.petshot;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private Intent signInActivity;
    private Intent signUpActivity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ImageView headerimage = findViewById(R.id.headerImage);
        Button signInButton = findViewById(R.id.connectionButton);
        Button signUpButton = findViewById(R.id.inscriptionButton);

        signInActivity = new Intent(this, SignInActivity.class);
        signUpActivity = new Intent(this, SignUpActivity.class);
        headerimage.setImageResource(R.drawable.petshot);
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
}
