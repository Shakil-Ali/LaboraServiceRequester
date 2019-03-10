package com.labora.laboraservicerequester;

// Import statement
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

// Register Activity
public class RegisterActivity2 extends AppCompatActivity implements View.OnClickListener {

    // Create and initialising ui features
    private EditText editTextFullName;
    private EditText editTextPhoneNumber;
    // private EditText editTextOccupancy;
    //private EditText editTextPostalAddress;
    private Button buttonRegister;
    private static final String TAG = "RegisterActivity2";

    // Creating database variables
    //private DatabaseReference databaseReference;
    private FirebaseAuth firebaseAuth;
    private FirebaseFirestore mFirestore;


    // On create method
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register2);

        // For the back button
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        // Instance of database
        mFirestore = FirebaseFirestore.getInstance();

        // Variables to store the data
        editTextFullName = (EditText) findViewById(R.id.editTextFullName);
        editTextPhoneNumber = (EditText) findViewById(R.id.editTextPhoneNumber);
        buttonRegister = (Button) findViewById(R.id.buttonRegister);
        //editTextOccupancy = (EditText) findViewById(R.id.editTextOccupancy);
        //editTextPostalAddress = (EditText) findViewById(R.id.editTextPostalAddress);

        // Initialise fire base
        firebaseAuth = FirebaseAuth.getInstance();

        if(firebaseAuth.getCurrentUser() != null)
        {
            //profile activity
        }

        // Check if register button clicked
        buttonRegister.setOnClickListener(this);
    }


    // Register user function
    public void registerUser()
    {
        // Variables initialisation and assignment
        String name = editTextFullName.getText().toString().trim();
        String phone = editTextPhoneNumber.getText().toString().trim();
        String userId = FirebaseAuth.getInstance().getCurrentUser().getUid();
        String email = FirebaseAuth.getInstance().getCurrentUser().getEmail();
        //String address = editTextPostalAddress.getText().toString().trim();
        //String occupation = editTextOccupancy.getText().toString().trim();

        // Conditional if to check if name and phone field is not empty
        if(!TextUtils.isEmpty(name) && !TextUtils.isEmpty(phone))
        {
            // Create a map and store the details of the users
            Map<String, Object> user_service = new HashMap<>();
            user_service.put("fullname", name);
            //user_service.put("address", address);
            user_service.put("phone", phone);
            //user_service.put("occupation", occupation);
            user_service.put("userid", userId);
            user_service.put("email", email);

            // Store the map values in the database
            mFirestore.collection("Users-ServiceRequester")
                    .add(user_service)
                    .addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                        @Override
                        public void onSuccess(DocumentReference documentReference) {
                            Log.d(TAG, "DocumentSnapshot added with ID: " + documentReference.getId());
                            //startActivity(new Intent(getApplicationContext(), ProfileActivity.class));
                        }
                    })


                    .addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            Log.w(TAG, "Error adding document", e);
                        }
                    });

            // If successful inform user and take them to profile page
            Toast.makeText(RegisterActivity2.this, "Registered successfully", Toast.LENGTH_SHORT).show();
            finish();
            startActivity(new Intent(getApplicationContext(),  ProfileActivity.class));
        }
        // Else statement
        else{
            // Inform the user of missing fields
            Toast.makeText(this, "Please fill in the missing fields", Toast.LENGTH_SHORT).show();
        }
    }

    // On click method
    public void onClick(View view)
    {
        // Conditional if statement to check if register button is working
        if(view == buttonRegister)
        {
            // Call register user
            registerUser();
        }
    }

}

