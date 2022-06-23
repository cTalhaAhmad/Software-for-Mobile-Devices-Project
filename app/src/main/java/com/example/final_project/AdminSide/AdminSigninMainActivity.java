package com.example.final_project.AdminSide;

import android.content.Context;
import android.content.Intent;
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

import java.util.HashMap;

public class AdminSigninMainActivity extends AppCompatActivity {
    EditText editTextName, editTextPass;
    FirebaseDatabase database;
    DatabaseReference reference;
    Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_signin_main);

        editTextName = findViewById(R.id.edtevent);
        context = getBaseContext();
        editTextPass = findViewById(R.id.edtoc);
        database = FirebaseDatabase.getInstance("https://final-project-3c36c-default-rtdb.firebaseio.com/");
        reference = database.getReference("admin");
    }

    public void admin_signin(View view) {
        if (editTextName.getText().toString().isEmpty() || editTextPass.getText().toString().isEmpty()) {
            Toast.makeText(this, "Enter User Name And Password", Toast.LENGTH_SHORT).show();
        } else {
            reference.child(editTextName.getText().toString())
                    .get()
                    .addOnCompleteListener(new OnCompleteListener<DataSnapshot>() {
                        @Override
                        public void onComplete(@NonNull Task<DataSnapshot> task) {
                            if (task.getResult().getValue() != null) {
                                HashMap<String, String> passHash = (HashMap<String, String>) task.getResult().getValue();
                                String pass = passHash.get("password");
                                if (pass.equals(editTextPass.getText().toString())) {
                                    Toast.makeText(context, "login", Toast.LENGTH_SHORT).show();
                                    Intent intent = new Intent(context, AdminPortalMainActivity.class);
                                    intent.putExtra("admin", editTextName.getText().toString());
                                    startActivity(intent);
                                } else {
                                    Toast.makeText(context, "Incorrect Password", Toast.LENGTH_SHORT).show();
                                }
                            } else {
                                Toast.makeText(context, "Incorrect Username", Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
        }
    }
}