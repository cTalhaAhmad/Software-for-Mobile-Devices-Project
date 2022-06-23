package com.example.final_project.OCsideSide;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.os.Bundle;

import com.example.final_project.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.HashMap;

public class ShowRegMainActivity extends AppCompatActivity {
    ArrayList regList;
    RegAdapter adapter;
    DatabaseReference reference;
    FirebaseDatabase database;
    Context context;
    RecyclerView recyclerView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_reg_main);
        regList=new ArrayList<Reg>();
        recyclerView=findViewById(R.id.recview);
        database= FirebaseDatabase.getInstance("https://final-project-3c36c-default-rtdb.firebaseio.com/");
        reference= database.getReference("mentor");

        RecyclerView.LayoutManager layoutManager=new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(adapter);
    }

    @Override
    protected void onStart() {
        super.onStart();
        reference = database.getReference("/user");

        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot datasnapshot) {
                for (DataSnapshot snapshot : datasnapshot.getChildren()) {
                    HashMap<String,String> data= (HashMap<String,String>)snapshot.getValue();
                    Reg reg=new Reg();
                    if(!data.toString().equals("")&&snapshot.getKey()!=null){
                    reg.setUser(snapshot.getKey());
                    reg.setSubEvent(data.get("subevent"));
                    reg.setEvent(data.get("event"));
                    regList.add(reg);


                    }
                    adapter= new RegAdapter(regList);
                    recyclerView.setAdapter(adapter);
                    adapter.notifyDataSetChanged();

                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

    }
}