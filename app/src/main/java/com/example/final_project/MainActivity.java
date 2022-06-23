package com.example.final_project;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.final_project.AdminSide.AdminSigninMainActivity;
import com.example.final_project.MentorSide.SigninMentorMainActivity;
import com.example.final_project.OCsideSide.SigninOCMainActivity;
import com.example.final_project.ParticipantSide.PraticipantSignInMainActivity;
import com.example.final_project.ParticipantSide.RegisterParticipantMainActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void goto_admin_sigin(View view) {
        Intent intent=new Intent(this, AdminSigninMainActivity.class);
        startActivity(intent);
    }

    public void goto_mentor_signin(View view) {
        Intent intent=new Intent(this, SigninMentorMainActivity.class);
        startActivity(intent);
    }

    public void goto_oc_signin(View view) {
        Intent intent=new Intent(this, SigninOCMainActivity.class);
        startActivity(intent);
    }

    public void goto_user_signUP(View view) {
        Intent intent=new Intent(this, RegisterParticipantMainActivity.class);
        startActivity(intent);
    }

    public void goto_user_signin(View view) {
        Intent intent=new Intent(this, PraticipantSignInMainActivity.class);
        startActivity(intent);
    }
}