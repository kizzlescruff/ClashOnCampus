package com.example.wilki.clashoncampus;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class QuestDescriptionActivity extends AppCompatActivity {

    private QuestHolder qHolder;
    private Button playQstBtn;
    private Button rtnToQLBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quest_description);
        playQstBtn = (Button) findViewById(R.id.publishQuestBtn);
        rtnToQLBtn = (Button) findViewById(R.id.backToQuestListBtn);
        qHolder = (QuestHolder) getIntent().getSerializableExtra("QUESTS");
    }

    public void onRestart(){
        playQstBtn.setBackgroundResource(R.drawable.button);
        rtnToQLBtn.setBackgroundResource(R.drawable.button);
    }

    public void playQuest(View view){
        playQstBtn.setBackgroundResource(R.drawable.clicked_button);
        startActivity(new Intent(this, QuestMapActivity.class));
    }

    public void returnToQuestList(View view){
        rtnToQLBtn.setBackgroundResource(R.drawable.clicked_button);
        Intent intent = new Intent(getApplicationContext(), QuestListActivity.class);
        intent.putExtra("QUESTS", qHolder);
        startActivity(intent);
    }
}
