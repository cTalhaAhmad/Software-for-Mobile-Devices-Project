package com.example.final_project.OCsideSide;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.final_project.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;

public class OCportalMainActivity extends AppCompatActivity {
    TextView textViewMentor;
    String username;
    Intent intent;
    FirebaseDatabase database;
    DatabaseReference reference;
    //    Context context;
    String event, subevent;
    Button buttonaddVenue, buttonScore, buttonEventUpdate, buttonShowReg;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ocportal_main);

        database = FirebaseDatabase.getInstance("https://final-project-3c36c-default-rtdb.firebaseio.com/");
        reference = database.getReference("oc");
        buttonaddVenue = findViewById(R.id.btnsetvenue);
        buttonEventUpdate = findViewById(R.id.btneventupdt);
        buttonScore = findViewById(R.id.btnsetscore);
        buttonShowReg = findViewById(R.id.btnallregoc);
        intent = getIntent();
        username = intent.getStringExtra("oc");
//        textViewMentor = findViewById(R.id.txtmentor);
//        textViewMentor.setText(username);

        reference.child(username).get().addOnCompleteListener(new OnCompleteListener<DataSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DataSnapshot> task) {
                if (task.getResult().getValue() != null) {
                    HashMap<String, String> passHash = (HashMap<String, String>) task.getResult().getValue();
                    event = passHash.get("event");
                    subevent = passHash.get("subevent");
                    if (event != null) {
//                        textViewMentor.setText("Hi " + username + " your are OC of Sub-Event: " + event);
                    } else {
                        buttonaddVenue.setEnabled(false);
                        buttonEventUpdate.setEnabled(false);
                        buttonShowReg.setEnabled(false);
                        buttonScore.setEnabled(false);
//                        textViewMentor.setText("Hi " + username + " your are not mentor of any event ");

                    }

                }
            }
        });
    }


    public void add_sub_event_venue(View view) {
        Toast.makeText(this, "venues of " + event, Toast.LENGTH_SHORT).show();
        Intent intent1 = new Intent(this, SetVenueSubEventMainActivity.class);
        intent1.putExtra("event", event);
        intent1.putExtra("subevent", subevent);
        startActivity(intent1);


    }

    public void click_set_winner(View view) {
        Toast.makeText(this, "winner of " + event, Toast.LENGTH_SHORT).show();
        Intent intent1 = new Intent(this, SetScoreMainActivity.class);
        intent1.putExtra("event", event);
        intent1.putExtra("subevent", subevent);
        startActivity(intent1);

    }

    public void click_update_event(View view) {
        Toast.makeText(this, "winner of " + event, Toast.LENGTH_SHORT).show();
        Intent intent1 = new Intent(this, setEventUpdateMainActivity.class);
        intent1.putExtra("event", event);
        intent1.putExtra("subevent", subevent);
        startActivity(intent1);
    }

    public void show_reg_oc(View view) {
        Intent intent1 = new Intent(this, ShowRegMainActivity.class);
        startActivity(intent1);
    }
}