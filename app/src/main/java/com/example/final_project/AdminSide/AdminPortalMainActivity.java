package com.example.final_project.AdminSide;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.final_project.OCsideSide.ShowRegMainActivity;
import com.example.final_project.R;

public class AdminPortalMainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_portal_main);
    }

    public void show_reg_admin(View view) {
        Intent intent1=new Intent(this, ShowRegMainActivity.class);
        startActivity(intent1);
    }

    public void goto_assign_event(View view) {
        Intent intent1=new Intent(this,AssignEventMainActivity.class);
        startActivity(intent1);
    }

    public void reg_mentor(View view) {
        Intent intent1=new Intent(this,AddMentorActivity.class);
        startActivity(intent1);
    }

    public void add_event(View view) {
        Intent intent1=new Intent(this,AddEventMainActivity.class);
        startActivity(intent1);
    }
}