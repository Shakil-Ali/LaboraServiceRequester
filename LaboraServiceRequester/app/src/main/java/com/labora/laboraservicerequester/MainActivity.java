package com.labora.laboraservicerequester;

import android.app.ProgressDialog;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    // Create variables
    private Button buttonRegister;
    private EditText editTextEmail;
    private EditText editTextPassword;
    private TextView textViewSignin;

    private ProgressDialog progressDialog;

    private FirebaseAuth firebaseAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Initialise firebaseauth
        firebaseAuth = FirebaseAuth.getInstance();

        // Initialise progressBar object
        progressDialog = new ProgressDialog(this);

        // Initialise register button
        buttonRegister = (Button) findViewById(R.id.buttonRegister);

        // Set variable values
        editTextEmail = (EditText) findViewById(R.id.editTextEmail);
        editTextPassword = (EditText) findViewById(R.id.editTextPassword);

        textViewSignin = (TextView) findViewById(R.id.textViewSignin);

        // Set onclick listeners to the buttons
        buttonRegister.setOnClickListener(this);
        textViewSignin.setOnClickListener(this);

    }


    private void registerUser()
    {
        // Create strings for email and password
        String email = editTextEmail.getText().toString().trim();
        String password = editTextPassword.getText().toString().trim();

        // Check if email enetered
        if(TextUtils.isEmpty(email))
        {
            // Email is empty
            Toast.makeText(this, "Please enter email", Toast.LENGTH_SHORT).show();
            // Stop function executing further
            return;
        }

        // Check if password is entered
        if(TextUtils.isEmpty(password))
        {
            // Password is empty
            Toast.makeText(this, "Please enter password", Toast.LENGTH_SHORT).show();
            // Stop function executing further
            return;
        }

        // If validation is satisfactory
        // We will show a progress bar
        progressDialog.setMessage("Registering user");
        progressDialog.show();

        // Registering user to database
        firebaseAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task)
                    {
                        if(task.isSuccessful())
                        {
                            // User succesfully registers and logged in
                            // will start profile activity here
                            // Temp toast
                            Toast.makeText(MainActivity.this, "Registered successfully", Toast.LENGTH_SHORT).show();
                        }
                        else {
                            Toast.makeText(MainActivity.this, "Failed to register. Please try again", Toast.LENGTH_SHORT).show();
                        }
                    }
                });

    }

    @Override
    public void onClick(View view)
    {
        // Conditional to check if its a new user
        if(view == buttonRegister)
        {
            // Method to register new user
            registerUser();
        }

        // Check if user has account
        if(view == textViewSignin)
        {
            // Will open login activity
        }
    }


}

