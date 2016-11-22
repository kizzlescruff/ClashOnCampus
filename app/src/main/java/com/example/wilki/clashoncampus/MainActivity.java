package com.example.wilki.clashoncampus;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    private QuestHolder quests;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        quests = new QuestHolder();
    }

    public void startQuest(View view){
        Intent intent = new Intent(this, QuestListActivity.class);
        intent.putExtra("QUESTS", quests);
        startActivity(intent);
    }

    public void createQuest(View view){
        Intent intent = new Intent(this, CreateQuestActivity.class);
        intent.putExtra("QUESTS", quests);
        startActivity(intent);
    }
}
