package com.example.wilki.clashoncampus;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ProgressBar;

public class QuestFightActivity extends AppCompatActivity {

    private ProgressBar userHP;
    private ProgressBar monsterHP;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quest_fight);
        userHP = (ProgressBar) findViewById(R.id.userHP);
        monsterHP = (ProgressBar) findViewById(R.id.monsterHP);
        monsterHP.setRotation(180);
        monsterHP.setProgress(100);
        userHP.setProgress(100);
    }

    public void triggerAttack1(View view){
        int monsterProg = monsterHP.getProgress();
        monsterHP.setProgress(monsterProg-10);
        if(monsterProg <= 0){
            winFight();
        }
    }

    public void triggerAttack2(View view){
        monsterHP.setProgress(monsterHP.getProgress()-20);
    }

    public void triggerAttack3(View view){

    }

    public void triggerMagic1(View view){

    }

    public void triggerDefend(View view){

    }

    public void triggerRun(View view){
        startActivity(new Intent(this, QuestMapActivity.class));
    }

    public void winFight(){
        startActivity(new Intent(this, QuestCompleteActivity.class));
    }
}
