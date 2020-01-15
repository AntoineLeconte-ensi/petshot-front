package com.example.petshot;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class SignInActivity extends AppCompatActivity {

    private Intent mainActivity;
    private Intent profileActivity;
    private EditText userName;
    private EditText password;

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
                startActivity(profileActivity);
            }
        });
    }
}
