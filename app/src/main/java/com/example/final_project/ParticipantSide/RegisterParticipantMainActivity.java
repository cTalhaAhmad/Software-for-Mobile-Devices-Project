package com.example.final_project.ParticipantSide;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.final_project.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class RegisterParticipantMainActivity extends AppCompatActivity {
    FirebaseDatabase database;
    DatabaseReference reference;
    Context context;
    Spinner spinner, subSpinner;
    ArrayList<String> subEventList;
    EditText editTextName, editTextPass;
    ArrayList<String> eventList;
    ArrayAdapter adapter, subadapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_participant_main);
        context = getApplicationContext();
        spinner = findViewById(R.id.dropevents);
        subSpinner = findViewById(R.id.dropsubevent);
        eventList = new ArrayList<String>();
        subEventList = new ArrayList<String>();
        database = FirebaseDatabase.getInstance("https://final-project-3c36c-default-rtdb.firebaseio.com/");
        reference = database.getReference("");
        editTextName = findViewById(R.id.edtevent);
        context = getBaseContext();
        editTextPass = findViewById(R.id.edtoc);
        getdata();
    }

    private void getdata() {
        reference = database.getReference("/event");

        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot datasnapshot) {
                for (DataSnapshot snapshot : datasnapshot.getChildren()) {
                    String event = snapshot.getKey();
                    if (event != null) {
                        eventList.add(event);
                        adapter = new ArrayAdapter<String>(context, android.R.layout.simple_spinner_dropdown_item, eventList);
                        spinner.setAdapter(adapter);
                    }
                }
                adapter.notifyDataSetChanged();
                //////SubEvent
                int pos = spinner.getSelectedItemPosition();


            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }


    @Override
    protected void onStart() {
        super.onStart();
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                subEventList.clear();
                int pos = spinner.getSelectedItemPosition();
                String event = eventList.get(pos);
                reference = database.getReference("/event");

                reference.child(event).addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot datasnapshot) {
                        for (DataSnapshot snapshot : datasnapshot.getChildren()) {
                            String subEvent = snapshot.getKey();
                            if (!subEvent.equals("test")) {
                                subEventList.add(subEvent);
                                subadapter = new ArrayAdapter<String>(context, android.R.layout.simple_spinner_dropdown_item, subEventList);
                                subSpinner.setAdapter(subadapter);
                            }

                        }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

    }

    public void signUp(View view) {
        reference = database.getReference("/user");
        int subpos = subSpinner.getSelectedItemPosition();
        String subevnt = subEventList.get(subpos);
        int pos = spinner.getSelectedItemPosition();
        String user = editTextName.getText().toString();
        String pass = editTextPass.getText().toString();
        String evnt = eventList.get(pos);
//        reference.child("password").setValue()
        if ((!evnt.isEmpty()) && (!subevnt.isEmpty()) && (!user.isEmpty()) && (!pass.isEmpty())) {
            reference.child(user).child("password").setValue(pass);
            reference.child(user).child("event").setValue(evnt);
            reference.child(user).child("subevent").setValue(subevnt);
        }
    }
}