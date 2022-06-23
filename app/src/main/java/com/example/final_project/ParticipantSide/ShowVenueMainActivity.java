package com.example.final_project.ParticipantSide;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.example.final_project.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.HashMap;

public class ShowVenueMainActivity extends AppCompatActivity {
    Intent intent;
    String name,event,subevent;
    FirebaseDatabase database;
    ListView listView;
    ArrayAdapter adapter;
    ArrayList subEventList;
    DatabaseReference reference;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_venue_main);
        listView=findViewById(R.id.venuelist);
        subEventList= new ArrayList<String>();
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
        reference = database.getReference("/event");

        reference.child(event).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot datasnapshot) {
                for (DataSnapshot snapshot : datasnapshot.getChildren()) {
                    String subEvent = snapshot.getKey();
                    if (!snapshot.getValue().toString().equals("")) {
                        HashMap<String, String> sub = (HashMap<String, String>) snapshot.getValue();

                        String venue = sub.get("venue");
                        if (!subEvent.equals("test")) {
                            if (venue != null) {
                                subEventList.add(subEvent + "'s Venue : " + venue);
                            } else {
                                subEventList.add(subEvent + "'s Venue : NotSet");
                            }
                        }
                    }

                }
                adapter= new ArrayAdapter(getApplicationContext(), android.R.layout.simple_list_item_1,subEventList);
                listView.setAdapter(adapter);
                adapter.notifyDataSetChanged();

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }
}
