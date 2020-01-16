package com.example.petshot;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

public class FlowActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_flow);

        Intent intent = getIntent();
        Toast.makeText(getApplicationContext(), intent.getStringExtra("id"), Toast.LENGTH_LONG).show();
    }
}
