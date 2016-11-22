package com.example.wilki.clashoncampus;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class CreateQuestSetMonsterActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_quest_set_monster);
    }

    public void returnToMap(View view){
        // reset screen?
        startActivity(new Intent(this, CreateQuestMapActivity.class));
    }

    public void setMonset(View view){
        //check a monster is selected
        // add monster to quest
        startActivity(new Intent(this, CreateQuestMapActivity.class));
    }
}
