package com.labora.laboraservicerequester;

import android.content.Intent;
import android.location.Address;
import android.location.Geocoder;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthInvalidUserException;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.GeoPoint;
import com.google.firestore.v1.FirestoreGrpc;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class Services extends AppCompatActivity implements AdapterView.OnItemSelectedListener
{
    // Initialise variables
    private EditText name, postC, phone, email, job, keyW;
    private Button request;
    private FirebaseFirestore nFirestore;
    private FirebaseAuth firebaseAuth;

    DatabaseReference databaseReference;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_services);

        // Assignment operations
        nFirestore = FirebaseFirestore.getInstance();

        //
        firebaseAuth = FirebaseAuth.getInstance();


        final Spinner spinner = findViewById(R.id.spinner1);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.services, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
        spinner.setOnItemSelectedListener(this);

        name = (EditText) findViewById(R.id.name);
        postC = (EditText) findViewById(R.id.postC);
        phone = (EditText) findViewById(R.id.phone);
        email = (EditText) findViewById(R.id.email);
        job = (EditText) findViewById(R.id.job);
        keyW = (EditText) findViewById(R.id.keyW);

        request = (Button) findViewById(R.id.request);

        databaseReference = FirebaseDatabase.getInstance().getReference("Requests");

        // Set onclick listener
        request.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //addRequests();
                String requesterService = spinner.getSelectedItem().toString();
                String requesterName = name.getText().toString();
                String requesterPostCode = postC.getText().toString();
                String requesterPhone = phone.getText().toString();
                String requesterEmail = email.getText().toString();
                String requesterJob = job.getText().toString();
                String requesterKeyWord = keyW.getText().toString();
                String userId = FirebaseAuth.getInstance().getCurrentUser().getUid();



                request.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        openSummary();
                    }
                });

                if(!TextUtils.isEmpty(requesterName) && !TextUtils.isEmpty(requesterPostCode) && !TextUtils.isEmpty(requesterPhone) && !TextUtils.isEmpty(requesterEmail) && !TextUtils.isEmpty(requesterJob) && !TextUtils.isEmpty(requesterKeyWord) && !TextUtils.isEmpty(requesterService)) {
                    // Initialise Hash Map
                    Map<String, String> userMap = new HashMap<>();
                    userMap.put("Service", requesterService);
                    userMap.put("Name", requesterName);
                    userMap.put("Post Code", requesterPostCode);
                    userMap.put("Phone", requesterPhone);
                    userMap.put("Email", requesterEmail);
                    userMap.put("Job Description", requesterJob);
                    userMap.put("Keywords", requesterKeyWord);
                    userMap.put("userid", userId);



                    nFirestore.collection("Users-Requester").add(userMap).addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                        @Override
                        public void onSuccess(DocumentReference documentReference) {
                            Toast.makeText(Services.this, "Request Completed", Toast.LENGTH_SHORT).show();
                        }
                    }).addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            String error = e.getMessage();
                            Toast.makeText(Services.this, "Error" + error, Toast.LENGTH_SHORT).show();
                        }
                    });
                }
                else {
                    Toast.makeText(Services.this, "Please fill in the required fields", Toast.LENGTH_LONG).show();
                }
            }
        });

    }

    // Open summary page when the request is successful
    public void openSummary(){
        Intent intent = new Intent(this, Summary.class);
        startActivity(intent);
    }


    // For the spinner (This is required by Android Studio)
    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        String text = parent.getItemAtPosition(position).toString();
        //Toast.makeText(parent.getContext(), text, Toast.LENGTH_SHORT).show();
    }

    // For the spinner (This is required by Android Studio)
    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }

    /*
    // To turn post code into address
    public GeoPoint getLocationFromAddress(String strAddress)
    {

        Geocoder coder = new Geocoder(this);
        List<Address> address;
        GeoPoint p1 = null;

        try
        {
            address = coder.getFromLocationName(strAddress,5);
            if (address==null) {
                return null;
            }
            Address location=address.get(0);
            location.getLatitude();
            location.getLongitude();

            p1 = new GeoPoint((double) (location.getLatitude() * 1E6),
                    (double) (location.getLongitude() * 1E6));

            return p1;
        }
        catch (IOException e)
        {
            e.printStackTrace();
            return null;
        }
    }
    */

}

