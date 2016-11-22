package com.example.wilki.clashoncampus;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class CreateQuestActivity extends AppCompatActivity {

    private EditText questName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_quest);
        questName = (EditText)findViewById(R.id.questNameEntry);
    }

    public void returnToMainMenu(View view){
        startActivity(new Intent(this, MainActivity.class));
    }

    public void createQuest(View view){
        String questNameString = questName.getText().toString();
        if(questNameString.length() > 0){
            // create new quest with name and desc
            // pass quest to next screen
            startActivity(new Intent(this, CreateQuestMapActivity.class));
        } else {
            Toast noQuestNameToast = Toast.makeText(getApplicationContext(), "To create a quest " +
                    "you must specify a quest name", Toast.LENGTH_LONG);
            noQuestNameToast.show();
        }
    }
}
