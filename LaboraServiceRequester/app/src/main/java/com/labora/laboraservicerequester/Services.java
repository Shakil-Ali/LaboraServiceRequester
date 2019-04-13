package com.labora.laboraservicerequester;

// Import statements
import android.content.Intent;
import android.graphics.Color;
import android.location.Address;
import android.location.Geocoder;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthInvalidUserException;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.GeoPoint;
//import com.google.firestore.v1.FirestoreGrpc;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

// Public class Services
public class Services extends AppCompatActivity implements AdapterView.OnItemSelectedListener
{
    // Initialise variables
    private EditText postC, job, keyW;
    private Button request;
    private FirebaseFirestore nFirestore;
    //private FirebaseAuth firebaseAuth;
    //DatabaseReference databaseReference;

    // On create method
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_services);

        // For the back button
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        // Assignment operations
        nFirestore = FirebaseFirestore.getInstance();
        //firebaseAuth = FirebaseAuth.getInstance();

        // Creation and assignment of the spinner object
        final Spinner spinner = findViewById(R.id.spinner1);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.services, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
        spinner.setOnItemSelectedListener(this);

        // Find all the objectts by id and assign them to a variable
        postC = (EditText) findViewById(R.id.postC);
        job = (EditText) findViewById(R.id.job);
        keyW = (EditText) findViewById(R.id.keyW);
        request = (Button) findViewById(R.id.request);
        //name = (EditText) findViewById(R.id.name);
        //phone = (EditText) findViewById(R.id.phone);
        //email = (EditText) findViewById(R.id.email);


        // Set onclick listener
        request.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //addRequests();

                // Turn the inputs into strings
                String requesterService = spinner.getSelectedItem().toString();
                String requesterPostCode = postC.getText().toString();
                String requesterJob = job.getText().toString();
                String requesterKeyWord = keyW.getText().toString();
                //String requesterName = name.getText().toString();
                //String requesterPhone = phone.getText().toString();

                // Get the id of the user
                String userId = FirebaseAuth.getInstance().getCurrentUser().getUid();
                //String requesterEmail = FirebaseAuth.getInstance().getCurrentUser().getEmail();


                // Conditional to check for empty fields
                if(TextUtils.isEmpty(requesterService) || requesterService.equals("Choose a service"))
                {
                    Toast.makeText(Services.this, "Please choose a service", Toast.LENGTH_LONG).show();
                }
                else if(TextUtils.isEmpty(requesterPostCode))
                {
                    Toast.makeText(Services.this, "Please fill in a post code", Toast.LENGTH_LONG).show();
                }
                else if(TextUtils.isEmpty(requesterJob))
                {
                    Toast.makeText(Services.this, "Please fill in a job description", Toast.LENGTH_LONG).show();
                }
                else if(TextUtils.isEmpty(requesterKeyWord))
                {
                    Toast.makeText(Services.this, "Please fill in keywords", Toast.LENGTH_LONG).show();
                }
                else if(!TextUtils.isEmpty(requesterPostCode) && !TextUtils.isEmpty(requesterJob) && !TextUtils.isEmpty(requesterKeyWord) && !TextUtils.isEmpty(requesterService)) {
                    // Initialise Hash Map
                    Map<String, String> userMap = new HashMap<>();
                    userMap.put("Service", requesterService);
//                    userMap.put("Name", requesterName);
                    userMap.put("Post Code", requesterPostCode);
//                    userMap.put("Phone", requesterPhone);
//                    userMap.put("Email", requesterEmail);
                    userMap.put("Job Description", requesterJob);
                    userMap.put("Keywords", requesterKeyWord);
                    userMap.put("userid", userId);


                    nFirestore.collection("Summary-ServiceRequester").add(userMap).addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                        @Override
                        public void onSuccess(DocumentReference documentReference) {
                            Toast.makeText(Services.this, "Request Completed", Toast.LENGTH_SHORT).show();
                            startActivity(new Intent(getApplicationContext(), Summary.class));
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
    public void openSummary()
    {
        // Instantiate new activity
        Intent intent = new Intent(this, Summary.class);
        // Start the new activity
        startActivity(intent);
    }


    // For the spinner (This is required by Android Studio)
    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id)
    {
        // Conditional if statement
        if(parent.getItemAtPosition(position).equals("Choose a service"))
        {
            // do nothing
        }
        // Conditional else statement
        else {
            String text = parent.getItemAtPosition(position).toString();
            //Toast.makeText(parent.getContext(), text, Toast.LENGTH_SHORT).show();
        }
    }

    // For the spinner (This is required by Android Studio)
    @Override
    public void onNothingSelected(AdapterView<?> parent)
    {
        // do nothing
    }


}

