package com.example.final_project.MentorSide;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.final_project.R;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class AddSubEventMainActivity extends AppCompatActivity {

    TextView textView;
    EditText editTextSubEvent;
    String event;
    Intent intent;
    FirebaseDatabase database;
    DatabaseReference reference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_sub_event_main2);

        database = FirebaseDatabase.getInstance("https://final-project-3c36c-default-rtdb.firebaseio.com/");
        reference = database.getReference("event");

        intent = getIntent();
        event = intent.getStringExtra("event");

        editTextSubEvent = findViewById(R.id.edtevent);

        textView = findViewById(R.id.textmainevent);
        textView.setText("Add Sub-Events in " + event);


    }

    @Override
    protected void onStart() {
        super.onStart();


    }

    public void add_sub_event(View view) {
        reference.child(event).child(editTextSubEvent.getText().toString()).setValue("");
    }
}