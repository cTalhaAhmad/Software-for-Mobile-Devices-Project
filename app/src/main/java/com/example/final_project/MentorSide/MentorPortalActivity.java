package com.example.final_project.MentorSide;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.final_project.AdminSide.AddMentorActivity;
import com.example.final_project.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;

public class MentorPortalActivity extends AppCompatActivity {
    TextView textViewMentor;
    String username;
    Intent intent;
    FirebaseDatabase database;
    DatabaseReference reference;
    Context context;
    String event;
    Button buttonAddSubEvent,buttonRegOC,buttonAssignEvent,buttonEventShow;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mentor_portal);
        database=FirebaseDatabase.getInstance("https://final-project-3c36c-default-rtdb.firebaseio.com/");
        reference= database.getReference("mentor");
        buttonAddSubEvent=findViewById(R.id.addeventbtn);
        buttonAssignEvent=findViewById(R.id.assigneventbtn);
        buttonRegOC=findViewById(R.id.regocbtn);
        buttonEventShow=findViewById(R.id.showeventregbtn);
        intent= getIntent();
        username= intent.getStringExtra("mentor").toString();
        textViewMentor=findViewById(R.id.txtmentor);
        textViewMentor.setText(username);
    }

    @Override
    protected void onStart() {
        super.onStart();
        reference.child(username).get().addOnCompleteListener(new OnCompleteListener<DataSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DataSnapshot> task) {
                if (task.getResult().getValue()!=null){
                    HashMap<String,String> passHash = (HashMap<String, String>) task.getResult().getValue();
                     event=passHash.get("event");
                    if (event!=null) {
                        textViewMentor.setText("Hi " + username + " your are mentor of " + event);
                    }
                    else{
                        buttonAddSubEvent.setEnabled(false);
                        buttonAssignEvent.setEnabled(false);
                        buttonEventShow.setEnabled(false);
                        buttonRegOC.setEnabled(false);
                        textViewMentor.setText("Hi " + username + " your are not mentor of any event ");

                    }

                }
        }});
    }

    public void add_sub_event(View view) {
        Intent intent= new Intent(this, AddSubEventMainActivity.class);
        intent.putExtra("event",event);
        startActivity(intent);
    }

    public void reg_oc(View view) {
        Intent intent= new Intent(this, AddOCMainActivity.class);
        intent.putExtra("event",event);
        startActivity(intent);
    }

    public void goto_assign_subevent(View view) {
        Intent intent= new Intent(this, AssginSubEventMainActivity.class);
        intent.putExtra("event",event);
        startActivity(intent);
    }
}