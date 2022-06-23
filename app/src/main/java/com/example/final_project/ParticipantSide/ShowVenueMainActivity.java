package com.example.final_project.ParticipantSide;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

import com.example.final_project.R;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class ShowVenueMainActivity extends AppCompatActivity {
    Intent intent;
    String name,event,subevent;
    FirebaseDatabase database;
    DatabaseReference reference;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_venue_main);

        intent=getIntent();
        name=intent.getStringExtra("user").toString();
        event=intent.getStringExtra("event").toString();
        subevent=intent.getStringExtra("subevent").toString();


        database = FirebaseDatabase.getInstance("https://final-project-3c36c-default-rtdb.firebaseio.com/");
        reference = database.getReference("event");
    }

    @Override
    protected void onStart() {
        super.onStart();

    }
}
