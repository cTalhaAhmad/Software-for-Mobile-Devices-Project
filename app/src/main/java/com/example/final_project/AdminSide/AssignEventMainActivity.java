package com.example.final_project.AdminSide;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.Adapter;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.final_project.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Set;

public class AssignEventMainActivity extends AppCompatActivity {
    FirebaseDatabase database;
    DatabaseReference reference;
    ArrayAdapter adapter;
    Context context;
    ArrayList<String> eventList;
    ArrayList<String> mentorList;
    EditText editTextMentor,editTextEvent;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        setContentView(R.layout.activity_assign_event_main);
        editTextEvent=findViewById(R.id.edtevent);
        editTextMentor=findViewById(R.id.edtmentro);
        eventList=new ArrayList<String>();
        mentorList=new ArrayList<String>();

        context=getApplicationContext();
        database= FirebaseDatabase.
                getInstance("https://final-project-3c36c-default-rtdb.firebaseio.com/");
        reference= database.getReference("");
        
        

    }

    @Override
    protected void onStart() {
        super.onStart();
        

    }

    public void assign_event(View view) {
        String eventTxt=editTextEvent.getText().toString();
        String mentorTxt=editTextMentor.getText().toString();
        if (eventTxt.isEmpty() || mentorTxt.isEmpty()){
            Toast.makeText(this, "Enter User Name And Password", Toast.LENGTH_SHORT).show();
        }
        else {
            reference = database.getReference("/event");

            reference.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot datasnapshot) {
                    for (DataSnapshot snapshot : datasnapshot.getChildren()) {
                        String event = snapshot.getKey();
                        eventList.add(event);

                    }
                }

                @Override
                public void onCancelled(@NonNull DatabaseError error) {

                }
            });
            reference = database.getReference("/mentor");
            reference.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot datasnapshot) {
                    for (DataSnapshot snapshot : datasnapshot.getChildren()) {
                        String mentor = snapshot.getKey();
                        mentorList.add(mentor);

                    }
                }

                @Override
                public void onCancelled(@NonNull DatabaseError error) {

                }
            });
            if (eventList.contains(eventTxt)
                    && mentorList.contains(mentorTxt)){
                reference = database.getReference("/mentor");
                reference.child(mentorTxt).child("event").setValue(eventTxt);
            }
            else {
                Toast.makeText(this, "Event or Mentor Does Not Exist", Toast.LENGTH_SHORT).show();

            }
        }
        
    }
}