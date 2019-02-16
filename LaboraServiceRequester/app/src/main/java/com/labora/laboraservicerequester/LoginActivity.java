package com.labora.laboraservicerequester;

import android.app.ProgressDialog;
import android.content.Intent;
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

import org.w3c.dom.Text;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener{

    // Create variables
    private Button buttonSignIn;
    private EditText editTextEmail;
    private EditText editTextPassword;
    private TextView textViewSignup;

    private FirebaseAuth firebaseAuth;
    private ProgressDialog progressDialog;


    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        // Initialising variables
        firebaseAuth = FirebaseAuth.getInstance();

        if(firebaseAuth.getCurrentUser() != null)
        {
            // Profile activity here
            finish();
            startActivity(new Intent(getApplicationContext(), ProfileActivity.class));
        }

        editTextEmail = (EditText) findViewById(R.id.editTextEmail);
        editTextPassword = (EditText) findViewById(R.id.editTextPassword);
        buttonSignIn = (Button) findViewById(R.id.buttonSignin);
        textViewSignup = (TextView) findViewById(R.id.textViewSignup);

        progressDialog = new ProgressDialog(this);

        buttonSignIn.setOnClickListener(this);
        textViewSignup.setOnClickListener(this);

    }

    // Method for user logging in
    private void userLogin()
    {
        // Set and initialise a string
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
        progressDialog.setMessage("Registering user. Please wait");
        progressDialog.show();

        firebaseAuth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task)
                    {
                        progressDialog.dismiss();

                        if(task.isSuccessful())
                        {
                            // Start the profile activity
                            finish();
                            startActivity(new Intent(getApplicationContext(), ProfileActivity.class));
                        }
                    }
                });

    }


    @Override
    public void onClick(View view)
    {
        // Check if sign up button clicked
        if(view == buttonSignIn)
        {
            // Call login function
            userLogin();
        }

        // Check if sign up is clicked
        if(view == textViewSignup)
        {
            finish();
            startActivity(new Intent(this, MainActivity.class));
        }

    }

}
