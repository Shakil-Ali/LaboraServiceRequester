package com.labora.laboraservicerequester;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
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

public class RegisterActivity2 extends AppCompatActivity implements View.OnClickListener {

    private EditText editTextFullName;
    private EditText editTextPhoneNumber;
    // private EditText editTextOccupancy;
//    private EditText editTextPostalAddress;
    private Button buttonRegister;
    private static final String TAG = "RegisterActivity2";

    private DatabaseReference databaseReference;
    private FirebaseAuth firebaseAuth;
    private FirebaseFirestore mFirestore;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register2);

        mFirestore = FirebaseFirestore.getInstance();

        editTextFullName = (EditText) findViewById(R.id.editTextFullName);
        //editTextOccupancy = (EditText) findViewById(R.id.editTextOccupancy);
        editTextPhoneNumber = (EditText) findViewById(R.id.editTextPhoneNumber);
//        editTextPostalAddress = (EditText) findViewById(R.id.editTextPostalAddress);
        buttonRegister = (Button) findViewById(R.id.buttonRegister);

        //Initalise firebase
        firebaseAuth = FirebaseAuth.getInstance();


        if(firebaseAuth.getCurrentUser() != null){
            //profile activity

        }
        buttonRegister.setOnClickListener(this);

    }

    public void registerUser() {


        String name = editTextFullName.getText().toString().trim();
//        String address = editTextPostalAddress.getText().toString().trim();
        String phone = editTextPhoneNumber.getText().toString().trim();
        //String occupation = editTextOccupancy.getText().toString().trim();
        String userId = FirebaseAuth.getInstance().getCurrentUser().getUid();
        String email = FirebaseAuth.getInstance().getCurrentUser().getEmail();

        Map<String, Object> user_service = new HashMap<>();
        user_service.put("fullname", name);
//        user_service.put("address", address);
        user_service.put("phone", phone);
        //user_service.put("occupation", occupation);
        user_service.put("userid", userId);
        user_service.put("email", email);

        mFirestore.collection("Users-ServiceRequester")
                .add(user_service)
                .addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                    @Override
                    public void onSuccess(DocumentReference documentReference) {
                        Log.d(TAG, "DocumentSnapshot added with ID: " + documentReference.getId());
                        startActivity(new Intent(getApplicationContext(),  ProfileActivity.class));

                    }
                })


                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Log.w(TAG, "Error adding document", e);
                    }
                });
    }

    public void onClick(View view){
        if(view == buttonRegister){

            registerUser();

        }
    }



}

