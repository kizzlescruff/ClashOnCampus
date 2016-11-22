package com.example.wilki.clashoncampus;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    private QuestHolder quests;
    private Button startQuestBtn;
    private Button createQuestBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        quests = new QuestHolder();
        startQuestBtn = (Button) findViewById(R.id.startQuestBtn);
        createQuestBtn = (Button) findViewById(R.id.createQuestBtn);
    }

    public void onRestart(){
        startQuestBtn.setBackgroundResource(R.drawable.button);
        createQuestBtn.setBackgroundResource(R.drawable.button);
    }

    public void startQuest(View view){
        startQuestBtn.setBackgroundResource(R.drawable.clicked_button);
        Intent intent = new Intent(this, QuestListActivity.class);
        intent.putExtra("QUESTS", quests);
        startActivity(intent);
    }

    public void createQuest(View view){
        createQuestBtn.setBackgroundResource(R.drawable.clicked_button);
        Intent intent = new Intent(this, CreateQuestActivity.class);
        intent.putExtra("QUESTS", quests);
        startActivity(intent);
    }
}
