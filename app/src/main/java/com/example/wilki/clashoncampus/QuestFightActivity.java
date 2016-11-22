package com.example.wilki.clashoncampus;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;

public class QuestFightActivity extends AppCompatActivity {

    private ProgressBar userHP;
    private ProgressBar monsterHP;
    private int monsterHealth;
    private int userHealth;
    private Button attack1Btn;
    private Button attack2Btn;
    private Button attack3Btn;
    private Button magic1Btn;
    private Button defendBtn;
    private Button runBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quest_fight);


        monsterHealth = 100;
        userHealth = 100;
        userHP = (ProgressBar) findViewById(R.id.userHP);
        monsterHP = (ProgressBar) findViewById(R.id.monsterHP);
        monsterHP.setRotation(180);
        monsterHP.setProgress(100);
        userHP.setProgress(100);
        attack1Btn = (Button) findViewById(R.id.attack1Btn);
        attack2Btn = (Button) findViewById(R.id.attack2Btn);
        attack3Btn = (Button) findViewById(R.id.attack3Btn);
        magic1Btn = (Button) findViewById(R.id.magic1Btn);
        defendBtn = (Button) findViewById(R.id.defendBtn);
        runBtn = (Button) findViewById(R.id.runBtn);
    }

    public void triggerAttack1(View view){
        decreaseMonsterHealth(10, 1000);
    }

    public void triggerAttack2(View view){
        decreaseMonsterHealth(20, 2000);
    }

    public void triggerAttack3(View view){
        decreaseMonsterHealth(15, 1500);
    }

    public void triggerMagic1(View view){
        decreaseMonsterHealth(0, 500);
    }

    public void triggerDefend(View view){
        decreaseMonsterHealth(0, 0);
    }

    public void decreaseMonsterHealth(int amount, int duration) {
        monsterHealth -= amount;
        monsterHP.setProgress(monsterHealth);
        if (monsterHealth <= 0) {
            winFight();
        }

        deactivateButtons();
        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                activateButtons();
            }
        }, duration);
    }

    public void triggerRun(View view){
        startActivity(new Intent(this, QuestMapActivity.class));
    }

    private void winFight(){
        startActivity(new Intent(this, QuestCompleteActivity.class));
    }


    private void deactivateButtons() {
        attack1Btn.setBackgroundResource(R.drawable.grey_button);
        attack2Btn.setBackgroundResource(R.drawable.grey_button);
        attack3Btn.setBackgroundResource(R.drawable.grey_button);
        magic1Btn.setBackgroundResource(R.drawable.grey_button);
        defendBtn.setBackgroundResource(R.drawable.grey_button);
        runBtn.setBackgroundResource(R.drawable.grey_button);
        attack1Btn.setEnabled(false);
        attack2Btn.setEnabled(false);
        attack3Btn.setEnabled(false);
        magic1Btn.setEnabled(false);
        defendBtn.setEnabled(false);
        runBtn.setEnabled(false);
    }

    private void activateButtons(){
        attack1Btn.setBackgroundResource(R.drawable.button);
        attack2Btn.setBackgroundResource(R.drawable.button);
        attack3Btn.setBackgroundResource(R.drawable.button);
        magic1Btn.setBackgroundResource(R.drawable.button);
        defendBtn.setBackgroundResource(R.drawable.button);
        runBtn.setBackgroundResource(R.drawable.button);
        attack1Btn.setEnabled(true);
        attack2Btn.setEnabled(true);
        attack3Btn.setEnabled(true);
        magic1Btn.setEnabled(true);
        defendBtn.setEnabled(true);
        runBtn.setEnabled(true);
    }
}
