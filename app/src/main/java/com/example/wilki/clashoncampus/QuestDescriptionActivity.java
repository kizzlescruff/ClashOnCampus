package com.example.wilki.clashoncampus;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class QuestDescriptionActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quest_description);
    }

    public void playQuest(View view){
        startActivity(new Intent(this, QuestMapActivity.class));
    }

    public void returnToQuestList(View view){
        startActivity(new Intent(this, QuestListActivity.class));
    }
}
