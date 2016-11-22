package com.example.wilki.clashoncampus;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;

import java.util.List;

public class QuestListActivity extends AppCompatActivity {

    private Button rtnMainMenu;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quest_list);

        rtnMainMenu = (Button) findViewById(R.id.returnToMenu);
        ListView lv = (ListView) findViewById(R.id.questList);
        final QuestHolder qHolder = (QuestHolder)getIntent().getSerializableExtra("QUESTS");
        final List<Quest> quests = qHolder.getQuestList();
        QuestAdapter adapter = new QuestAdapter(this, 0, quests);
        lv.setAdapter(adapter);
        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(getApplicationContext(), QuestDescriptionActivity.class);
                intent.putExtra("QUESTS", qHolder);
                startActivity(intent);
            }
        });
    }

    public void onRestart(){
        rtnMainMenu.setBackgroundResource(R.drawable.button);
    }

    public void returnToMenu(View view){
        rtnMainMenu.setBackgroundResource(R.drawable.clicked_button);
        startActivity(new Intent(this, MainActivity.class));
    }
}
