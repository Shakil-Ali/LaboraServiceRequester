package com.labora.laboraservicerequester;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class RegisterActivity extends AppCompatActivity implements View.OnClickListener {

    private Button buttonRegister;
    private EditText editTextEmail;
    private EditText editTextPassword;


    private ProgressDialog ProgressDialog;

    private FirebaseAuth firebaseAuth;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        // Initialise firebaseauth
        firebaseAuth = FirebaseAuth.getInstance();

        // Initialise progressDialog object
        ProgressDialog = new ProgressDialog(this);

        // Initialise register button
        buttonRegister = (Button) findViewById(R.id.buttonRegister);

        // Set variable values
        editTextEmail = (EditText) findViewById(R.id.editTextEmail);
        editTextPassword = (EditText) findViewById(R.id.editTextPassword);

        // Set onClick listeners
        buttonRegister.setOnClickListener(this);
    }

    private void registerUser() {
        // Create strings for email and password
        String email = editTextEmail.getText().toString().trim();
        String password = editTextPassword.getText().toString().trim();

        // Check if email entered
        if(TextUtils.isEmpty(email)){
            //email is empty
            Toast.makeText(this, "Please enter a valid email address", Toast.LENGTH_SHORT).show();
            return;

        }

        // Check if password is entered
        if(TextUtils.isEmpty(password)){
            //password is empty
            Toast.makeText(this, "Please enter a valid password", Toast.LENGTH_SHORT).show();
            return;

        }

        ProgressDialog.setMessage("Registering email and password...");
        ProgressDialog.show();

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

                            Toast.makeText(RegisterActivity.this, "Registered successfully", Toast.LENGTH_SHORT).show();
                            finish();
                            startActivity(new Intent(getApplicationContext(),  RegisterActivity2.class));


                        }
                        else {
                            Toast.makeText(RegisterActivity.this, "Failed to register. Please try again", Toast.LENGTH_SHORT).show();
                            ProgressDialog.dismiss();

                        }
                    }
                });


    }

    @Override
    public void onClick (View view){

        if(view == buttonRegister) {
            registerUser();

        }




    }



}
