package com.labora.laboraservicerequester;

// Import statements
import android.app.Service;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

// Public class Profile Activity
public class ProfileActivity extends AppCompatActivity implements View.OnClickListener{

    // Initialise variables
    private FirebaseAuth firebaseAuth;
    private TextView textViewUserEmail;
    private Button buttonLogout;
    private Button buttonSearch;

    // On create method
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        // Assignment operations
        firebaseAuth = FirebaseAuth.getInstance();

        // Check if current user exists
        if(firebaseAuth.getCurrentUser() == null)
        {
            // Finish the current activity
            finish();
            // Start the new activity
            startActivity(new Intent(this, LoginActivity.class));
        }

        // Get current user
        FirebaseUser user = firebaseAuth.getCurrentUser();

        // Get and display their username on profile screen
        textViewUserEmail = (TextView) findViewById(R.id.textViewUserEmail);
        textViewUserEmail.setText("Welcome " + user.getEmail());

        // Buttons on page
        buttonLogout = (Button) findViewById(R.id.buttonLogout);
        buttonSearch = (Button) findViewById(R.id.buttonSearch);

        // Setting onclick listeners for buttons on page
        buttonLogout.setOnClickListener(this);
        buttonSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                openServices();
            }
        });

    }


    // Method to check if buttons are clicked
    @Override
    public void onClick(View view)
    {
        // Conditional to check if logout button clicked
        if(view == buttonLogout)
        {
            // Sign out method from fire base database
            firebaseAuth.signOut();
            // Finish the current activity
            finish();
            // Start the LoginActivity
            startActivity(new Intent(this, LoginActivity.class));
        }
    }

    // Open services method
    public void openServices()
    {
        // Initialise the new activity
        Intent intent = new Intent(this, Services.class);
        // Start the new activity
        startActivity(intent);
    }

}
