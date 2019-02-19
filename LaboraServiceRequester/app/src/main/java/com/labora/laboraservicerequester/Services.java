package com.labora.laboraservicerequester;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class Services extends AppCompatActivity
{
    // Initialise variables
    private EditText name, postC, phone, email, job, keyW;
    private Button request;

    DatabaseReference databaseReference;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_services);

        // Assignment operations
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
                addRequests();
            }
        });

    }

    public void addRequests()
    {
        String requesterName = name.getText().toString();
        String requesterPostCode = postC.getText().toString();
        String requesterPhone = phone.getText().toString();
        String requesterEmail = email.getText().toString();
        String requesterJobDesc = job.getText().toString();
        String requesterKeyWords = keyW.getText().toString();

        if(!TextUtils.isEmpty(requesterName) && !TextUtils.isEmpty(requesterPostCode) && !TextUtils.isEmpty(requesterPhone) && !TextUtils.isEmpty(requesterEmail) && !TextUtils.isEmpty(requesterJobDesc) && !TextUtils.isEmpty(requesterKeyWords))
        {
            // Create id
            String id = databaseReference.push().getKey();

            // Sending the data
            Requests requests = new Requests(id, requesterName, requesterPostCode, requesterPhone, requesterEmail, requesterJobDesc, requesterKeyWords);

            // Store
            databaseReference.child(id).setValue(requests);

            // Clear the fields
            name.setText("");
            postC.setText("");
            phone.setText("");
            email.setText("");
            job.setText("");
            keyW.setText("");

            // Opens summary page
            Intent intent = new Intent(this, Summary.class);
            startActivity(intent);

        }
        else {
            Toast.makeText(Services.this, "Please fill in the required fields", Toast.LENGTH_LONG).show();
        }

    }

}
