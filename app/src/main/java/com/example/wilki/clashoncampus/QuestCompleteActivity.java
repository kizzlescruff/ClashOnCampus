package com.example.wilki.clashoncampus;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class QuestCompleteActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quest_complete);
    }

    public void questComplete(View view){
        startActivity(new Intent(this, MainActivity.class));
    }
}