package com.labora.laboraservicerequester;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;


public class ProfileActivity extends AppCompatActivity implements View.OnClickListener{

    // Initialise variables
    private FirebaseAuth firebaseAuth;
    private TextView textViewUserEmail;
    private Button buttonLogout;
    private Button buttonSearch;
    private Button buttonMessages;
    private Button buttonSettings;


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
            finish();
            startActivity(new Intent(this, LoginActivity.class));
        }

        FirebaseUser user = firebaseAuth.getCurrentUser();

        textViewUserEmail = (TextView) findViewById(R.id.textViewUserEmail);

        textViewUserEmail.setText("Welcome " + user.getEmail());

        // Buttons on page
        buttonLogout = (Button) findViewById(R.id.buttonLogout);
        buttonSearch = (Button) findViewById(R.id.buttonSearch);
        buttonMessages = (Button) findViewById(R.id.buttonMessages);
        buttonSettings = (Button) findViewById(R.id.buttonSettings);

        // Setting onclick listeners for buttons on page
        buttonLogout.setOnClickListener(this);
        buttonSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                openSearch();
            }
        });


    }

    @Override
    public void onClick(View view)
    {
        if(view == buttonLogout)
        {
            firebaseAuth.signOut();
            finish();
            startActivity(new Intent(this, LoginActivity.class));
        }
    }

    public void openSearch()
    {
        Intent intent = new Intent(this, Services.class);
        startActivity(intent);
    }

}
