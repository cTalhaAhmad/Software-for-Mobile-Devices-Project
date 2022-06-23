package com.example.final_project.MentorSide;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Toast;

import com.example.final_project.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class AssginSubEventMainActivity extends AppCompatActivity {
    FirebaseDatabase database;
    DatabaseReference reference;
    Intent intent;
    String event;
    ArrayAdapter adapter;
    Context context;
    ArrayList<String> subEventList;
    ArrayList<String> ocList;
    EditText editTextMentor,editTextEvent;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_assgin_sub_event_main);

        editTextEvent=findViewById(R.id.edtevent);
        editTextMentor=findViewById(R.id.edtoc);
        subEventList =new ArrayList<String>();
        ocList =new ArrayList<String>();

        intent= getIntent();
        event = intent.getStringExtra("event").toString();

        context=getApplicationContext();
        database= FirebaseDatabase.
                getInstance("https://final-project-3c36c-default-rtdb.firebaseio.com/");
        reference= database.getReference("");
    }

    public void assign_subevent(View view) {
        String eventTxt=editTextEvent.getText().toString();
        String mentorTxt=editTextMentor.getText().toString();
        if (eventTxt.isEmpty() || mentorTxt.isEmpty()){
            Toast.makeText(this, "Enter User Name And Password", Toast.LENGTH_SHORT).show();
        }
        else {
            reference = database.getReference("/event");

            reference.child(event).addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot datasnapshot) {
                    for (DataSnapshot snapshot : datasnapshot.getChildren()) {
                        String subEvent = snapshot.getKey();
                        if (!subEvent.equals("test")) {
                            subEventList.add(subEvent);
                        }

                    }
                }

                @Override
                public void onCancelled(@NonNull DatabaseError error) {

                }
            });
            reference = database.getReference("/oc");
            reference.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot datasnapshot) {
                    for (DataSnapshot snapshot : datasnapshot.getChildren()) {
                        String oc = snapshot.getKey();
                        ocList.add(oc);

                    }
                }

                @Override
                public void onCancelled(@NonNull DatabaseError error) {

                }
            });
            if (subEventList.contains(eventTxt)
                    && ocList.contains(mentorTxt)){
                reference = database.getReference("/oc");
                reference.child(mentorTxt).child("event").setValue(event);

                reference.child(mentorTxt).child("subevent").setValue(eventTxt);
            }
            else {
                Toast.makeText(this, "Event or Mentor Does Not Exist", Toast.LENGTH_SHORT).show();

            }
        }

    }
}