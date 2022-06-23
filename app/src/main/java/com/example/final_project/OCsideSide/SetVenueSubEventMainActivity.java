package com.example.final_project.OCsideSide;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.final_project.R;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class SetVenueSubEventMainActivity extends AppCompatActivity {
    TextView textView;
    EditText editTextSubEvent;
    String event,subevent;
    Intent intent;
    FirebaseDatabase database;
    DatabaseReference reference;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_set_venue_sub_event_main);


        database= FirebaseDatabase.getInstance("https://final-project-3c36c-default-rtdb.firebaseio.com/");
        reference= database.getReference("event");

        intent= getIntent();
        event = intent.getStringExtra("event").toString();
        subevent = intent.getStringExtra("subevent").toString();

        editTextSubEvent=findViewById(R.id.edtevent);

        textView=findViewById(R.id.textmainevent);
        textView.setText("Add Venue of Sub-Events: "+subevent);
    }

    public void insert_sub_event_venue(View view) {
        if (!editTextSubEvent.getText().toString().isEmpty()) {
            reference.child(event).child(subevent).child("venue")
                    .setValue(editTextSubEvent.getText().toString());
        }
        else{
            Toast.makeText(this, "Enter The Venue", Toast.LENGTH_SHORT).show();
        }
    }


}