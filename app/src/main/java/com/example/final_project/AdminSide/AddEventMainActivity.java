package com.example.final_project.AdminSide;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.final_project.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class AddEventMainActivity extends AppCompatActivity {
    EditText editTextEvent;
    FirebaseDatabase database;
    DatabaseReference reference;
    Context context;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_event_main);
        context= getBaseContext();
        editTextEvent =findViewById(R.id.edtevent);
        database= FirebaseDatabase.getInstance("https://final-project-3c36c-default-rtdb.firebaseio.com/");
        reference= database.getReference("event");
    }

    public void add_event(View view) {
        if (editTextEvent.getText().toString().isEmpty()){
            Toast.makeText(this, "Enter Event Name", Toast.LENGTH_SHORT).show();
        }
        else {
            final String[] getValue = new String[1];
            reference.child(editTextEvent.getText().toString()).get().addOnCompleteListener(new OnCompleteListener<DataSnapshot>() {
                @Override
                public void onComplete(@NonNull Task<DataSnapshot> task) {
                    if (task.getResult().getValue()==null){

                        reference.child(editTextEvent.getText().toString()).child("test").setValue("");
                    } else {
                        Toast.makeText(context, "Event Already Exist", Toast.LENGTH_SHORT).show();
                    }
                }

            });


        }
    }
    }
