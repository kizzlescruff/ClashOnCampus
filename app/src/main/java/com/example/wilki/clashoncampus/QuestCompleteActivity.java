package com.example.wilki.clashoncampus;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class QuestCompleteActivity extends AppCompatActivity {

    private Button rtnToMainMenu;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quest_complete);
        rtnToMainMenu = (Button) findViewById(R.id.backMainMenuBtn);
    }

    public void questComplete(View view){
        rtnToMainMenu.setBackgroundResource(R.drawable.clicked_button);
        startActivity(new Intent(this, MainActivity.class));
    }

    public void onRestart(){
        rtnToMainMenu.setBackgroundResource(R.drawable.button);
    }
}
