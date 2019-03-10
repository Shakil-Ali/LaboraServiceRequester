package com.labora.laboraservicerequester;

// Import statement
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

// Public class Summary
public class Summary extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_summary);

        // For the back button
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

    }



}
