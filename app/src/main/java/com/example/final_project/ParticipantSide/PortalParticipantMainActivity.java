package com.example.final_project.ParticipantSide;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.final_project.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;

public class PortalParticipantMainActivity extends AppCompatActivity {
    TextView textViewMain, textViewUpdate, textViewScore;
    Intent intent;
    String name, event, subevent;
    FirebaseDatabase database;
    DatabaseReference reference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_portal_participant_main);
        textViewMain = findViewById(R.id.maintxt);
        textViewScore = findViewById(R.id.scoretxt);
        textViewUpdate = findViewById(R.id.updatetxt);
        intent = getIntent();
        name = intent.getStringExtra("user");
        event = intent.getStringExtra("event");
        subevent = intent.getStringExtra("subevent");


        database = FirebaseDatabase.getInstance("https://final-project-3c36c-default-rtdb.firebaseio.com/");
        reference = database.getReference("event");


    }


    @Override
    protected void onStart() {
        super.onStart();
        textViewMain.setText("Hi " + name + " Your Participating in " + subevent + " (" + event + ")");
        reference.child(event).child(subevent)
                .get()
                .addOnCompleteListener(new OnCompleteListener<DataSnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<DataSnapshot> task) {
                        if (task.getResult().getValue() != null) {
                            HashMap<String, String> passHash = (HashMap<String, String>) task.getResult().getValue();
                            String winner = passHash.get("winner");
                            String update = passHash.get("update");
                            if (winner != null) {
                                textViewScore.setText("Winner of Event is " + winner);
                            } else {
                                textViewScore.setText("Winner of Event is to be decided");

                            }
                            if (update != null) {
                                textViewUpdate.setText(update);
                            } else {
                                textViewUpdate.setText("No Updates yet");

                            }
                        }
                    }


                });

    }

    public void Show_Venue(View view) {
        Intent intent = new Intent(this, ShowVenueMainActivity.class);
        intent.putExtra("user", name);
        intent.putExtra("event", event);
        intent.putExtra("subevent", subevent);
        startActivity(intent);
    }
}