package com.example.wilki.clashoncampus;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class QuestMapActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quest_map);
    }

    public void startCombat(View view){
        startActivity(new Intent(this, QuestFightActivity.class));
    }

    public void openInventory(View view){
        startActivity(new Intent(this, QuestInventoryActivity.class));
    }
}
