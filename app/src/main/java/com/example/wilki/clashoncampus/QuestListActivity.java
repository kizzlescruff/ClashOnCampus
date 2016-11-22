package com.example.wilki.clashoncampus;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.List;

public class QuestListActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quest_list);

        ListView lv = (ListView) findViewById(R.id.questList);
        QuestHolder qHolder = (QuestHolder)getIntent().getSerializableExtra("QUESTS");
        List<Quest> quests = qHolder.getQuestList();
        QuestAdapter adapter = new QuestAdapter(this, 0, quests);
        lv.setAdapter(adapter);
        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                startActivity(new Intent(getApplicationContext(), QuestDescriptionActivity.class));
            }
        });
    }

    public void returnToMenu(View view){
        startActivity(new Intent(this, MainActivity.class));
    }
}
