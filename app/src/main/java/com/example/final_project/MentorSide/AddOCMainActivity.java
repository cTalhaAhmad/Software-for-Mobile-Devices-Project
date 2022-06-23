package com.example.final_project.MentorSide;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.final_project.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class AddOCMainActivity extends AppCompatActivity {
    EditText editTextName, editTextPass;
    FirebaseDatabase database;
    DatabaseReference reference;
    Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_ocmain);
        editTextName = findViewById(R.id.edtevent);
        context = getBaseContext();
        editTextPass = findViewById(R.id.edtoc);
        database = FirebaseDatabase.getInstance("https://final-project-3c36c-default-rtdb.firebaseio.com/");
        reference = database.getReference("oc");
    }

    public void add_oc(View view) {
        if (editTextName.getText().toString().isEmpty() || editTextPass.getText().toString().isEmpty()) {
            Toast.makeText(this, "Enter User Name And Password", Toast.LENGTH_SHORT).show();
        } else {
            final String[] getValue = new String[1];
            reference.child(editTextName.getText().toString()).get().addOnCompleteListener(new OnCompleteListener<DataSnapshot>() {
                @Override
                public void onComplete(@NonNull Task<DataSnapshot> task) {
                    if (task.getResult().getValue() == null) {

                        reference.child(editTextName.getText()
                                        .toString())
                                .child("password")
                                .setValue(editTextPass.getText().toString());
                    } else {
                        Toast.makeText(context, "OC Already Exist", Toast.LENGTH_SHORT).show();
                    }
                }

            });

        }
    }

}